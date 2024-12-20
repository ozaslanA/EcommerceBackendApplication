package com.ozaslan.e_commerce_backend.service.abstracts;

import com.ozaslan.e_commerce_backend.exceptions.UserException;
import com.ozaslan.e_commerce_backend.model.User;

public interface UserService {

    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;


}
