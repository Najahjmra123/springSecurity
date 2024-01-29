package com.elitech.secure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elitech.secure.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByEmail(String email);
    


}
