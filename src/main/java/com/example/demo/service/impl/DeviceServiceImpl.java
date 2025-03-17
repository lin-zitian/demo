package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.repository.model.Device;
import com.example.demo.service.DeviceService;
import com.example.demo.repository.mapper.DeviceMapper;
import org.springframework.stereotype.Service;

/**
* @author linzitian
* @description 针对表【device(设备信息表)】的数据库操作Service实现
* @createDate 2025-03-17 14:46:54
*/
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device>
    implements DeviceService{

}




