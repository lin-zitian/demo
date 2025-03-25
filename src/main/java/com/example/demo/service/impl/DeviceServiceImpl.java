package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.repository.mapper.DeviceMapper;
import com.example.demo.repository.model.Device;
import com.example.demo.service.DeviceService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
* @author linzitian
* @description 针对表【device(设备信息表)】的数据库操作Service实现
* @createDate 2025-03-17 14:46:54
*/
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device>
    implements DeviceService{

    @Resource
    private RedisTemplate<String, Device> redisTemplate;

    private static final String CACHE_PREFIX = "device:";

    @Override
    public Device getById(String id) {
        Device device = redisTemplate.opsForValue().get(CACHE_PREFIX + id);
        if (device != null) {
            System.out.println("从缓存中获取设备: " + device);
            return device;
        }

        device = super.getById(id);
        if (device != null) {
            System.out.println("从数据库中获取设备: " + device);
            redisTemplate.opsForValue().set(CACHE_PREFIX + id, device, 1, TimeUnit.HOURS);
        }

        return device;
    }

    @Override
    public boolean save(Device device) {
        boolean result = super.save(device);
        if (result) {
            // 确保存储到 Redis 的是完整的 Device 对象
            redisTemplate.opsForValue().set(CACHE_PREFIX + device.getId(), device, 1, TimeUnit.HOURS);
        }
        return result;
    }

    @Override
    public boolean updateById(Device device) {
        // 更新数据库
        boolean result = super.updateById(device);
        if (result) {
            // 更新缓存
            redisTemplate.opsForValue().set(CACHE_PREFIX + device.getId(), device, 1, TimeUnit.HOURS);
        }
        return result;
    }

    @Override
    public boolean removeById(String id) {
        // 删除数据库记录
        boolean result = super.removeById(id);
        if (result) {
            // 删除缓存
            redisTemplate.delete(CACHE_PREFIX + id);
        }
        return result;
    }

    @Override
    public String getClientIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("X-Real-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        // 如果使用了多个代理，X-Forwarded-For 可能包含多个 IP 地址
        if (ipAddress != null && ipAddress.contains(",")) {
            ipAddress = ipAddress.split(",")[0].trim();
        }

        return ipAddress;
    }

}




