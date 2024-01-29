package com.elitech.secure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elitech.secure.entity.AuthRequest;
import com.elitech.secure.entity.UserInfo;
import com.elitech.secure.service.JwtService;
import com.elitech.secure.service.UserInfoService;

@RestController
@RequestMapping("/auth")

public class UserController {
	 @Autowired
	    private UserInfoService userInfoService;
	    @Autowired
	    private AuthenticationManager authenticationManager;
	    @Autowired

	    private JwtService jwtService;
	    @GetMapping("/welcome")
	    public ResponseEntity<String> welcome(){
	        return ResponseEntity.ok("Welcome to Spring Security tutorials !!");
	    }
	    @PostMapping("/add")
	    //@PreAuthorize("hasAuthority('ADMIN_ROLES')")
	    public ResponseEntity<UserInfo> addUser(@RequestBody UserInfo userInfo){
	        
	        return ResponseEntity.ok(userInfoService.addUser(userInfo));
	    }
	    @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest){
	        try {
	            Authentication authenticate = (Authentication) authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
	            if(authenticate.isAuthenticated()){
	                return ResponseEntity.ok(jwtService.generateToken(authRequest.getUserName()));
	            } else {
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	            }
	        } catch (UsernameNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user request");
	        }
	    }
	  




}
