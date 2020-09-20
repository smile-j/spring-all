package com.dong.demo.service.impl;

import com.dong.demo.apimodel.model.User;
import com.dong.demo.service.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {


    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("---save-success-,{}",objectMapper.writeValueAsString(user));
        return user;
    }

    @Override
    public List<User> findAlls(User user) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("---请求参数-,{}",objectMapper.writeValueAsString(user));
        User user1 = new User();
        User user2 = new User();
        user1.setName("lili");
        user1.setBorn(new Date());
        user2.setName("lucy");
        user2.setBorn(new Date());
        return Arrays.asList(user1,user2);
    }
}
