package com.example.demo.controller;

import com.example.demo.common.BaseResult;
import com.example.demo.repository.model.Device;
import com.example.demo.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/device")
@Api(tags = "设备接口")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private HttpServletRequest request;

    // GET: 获取所有设备
    @GetMapping
    @ApiOperation("获取所有设备")
    public BaseResult<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.list();
        return BaseResult.ok(devices);
    }

    // GET: 根据ID获取设备
    @GetMapping("/{id}")
    @ApiOperation("根据ID获取设备")
    public BaseResult<Device> getDeviceById(@PathVariable String id) {
        Device device = deviceService.getById(id);
        return BaseResult.ok(device);
    }

    // POST: 添加设备
    @PostMapping
    @ApiOperation("添加设备")
    public BaseResult<String> addDevice(@RequestBody Device device) {
        deviceService.save(device);
        return BaseResult.ok("Device added successfully");
    }

    // PUT: 更新设备
    @PutMapping("/{id}")
    @ApiOperation("更新设备")
    public BaseResult<String> updateDevice(@PathVariable String id, @RequestBody Device device) {
        device.setId(id);
        deviceService.updateById(device);
        return BaseResult.ok("Device updated successfully");
    }

    // DELETE: 删除设备
    @DeleteMapping("/{id}")
    @ApiOperation("删除设备")
    public BaseResult<String> deleteDevice(@PathVariable String id) {
        deviceService.removeById(id);
        return BaseResult.ok("Device deleted successfully");
    }

    // POST: 注册设备
    @PostMapping("/register")
    @ApiOperation("注册设备")
    public BaseResult<String> registerDevice(@RequestBody Device device) {
        String clientIp = deviceService.getClientIpAddress(request);
        device.setIpAddress(clientIp);
        deviceService.save(device);
        return BaseResult.ok("Device registered successfully");
    }
}