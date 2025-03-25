package com.example.demo.service;

import com.example.demo.repository.model.Device;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author linzitian
* @description 针对表【device(设备信息表)】的数据库操作Service
* @createDate 2025-03-17 14:46:54
*/
public interface DeviceService extends IService<Device> {

    Device getById(String id);

    boolean removeById(String id);

    String getClientIpAddress(HttpServletRequest request);
}
