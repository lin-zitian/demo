package com.example.demo.repository.mapper;

import com.example.demo.repository.model.Device;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author linzitian
* @description 针对表【device(设备信息表)】的数据库操作Mapper
* @createDate 2025-03-17 14:46:54
* @Entity com.example.demo.repository.model.Device
*/
@Mapper
public interface DeviceMapper extends BaseMapper<Device> {

}




