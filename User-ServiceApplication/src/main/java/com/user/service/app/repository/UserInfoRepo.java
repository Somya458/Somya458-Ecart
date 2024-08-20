package com.user.service.app.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.user.service.app.model.UserInfo;

public interface UserInfoRepo extends CrudRepository<UserInfo, Integer> {
    public Optional<UserInfo> findByPhonenumber(String phonenumber);
}
