package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.repository.model.User;
import com.example.demo.service.UserService;
import com.example.demo.repository.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author linzitian
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-03-17 14:44:56
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




