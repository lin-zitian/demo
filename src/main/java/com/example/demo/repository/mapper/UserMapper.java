package com.example.demo.repository.mapper;

import com.example.demo.repository.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author linzitian
* @description 针对表【user】的数据库操作Mapper
* @createDate 2025-03-17 14:44:56
* @Entity com.example.demo.repository.model.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




