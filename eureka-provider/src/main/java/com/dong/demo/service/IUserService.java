package com.dong.demo.service;


import com.dong.demo.apimodel.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IUserService {

    public User saveUser(User user) throws JsonProcessingException;
    public List<User> findAlls(User user) throws JsonProcessingException;

}
