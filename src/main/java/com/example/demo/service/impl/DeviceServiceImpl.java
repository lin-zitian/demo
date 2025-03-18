package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.repository.mapper.DeviceMapper;
import com.example.demo.repository.model.Device;
import com.example.demo.service.DeviceService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        // 先从缓存中获取
        Device device = redisTemplate.opsForValue().get(CACHE_PREFIX + id);
        if (device != null) {
            return device;
        }

        // 缓存中没有，从数据库获取
        device = super.getById(id);
        if (device != null) {
            // 存入缓存，设置过期时间
            redisTemplate.opsForValue().set(CACHE_PREFIX + id, device, 1, TimeUnit.HOURS);
        }

        return device;
    }

    @Override
    public boolean save(Device device) {
        // 保存到数据库
        boolean result = super.save(device);
        if (result) {
            // 存入缓存
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


}




