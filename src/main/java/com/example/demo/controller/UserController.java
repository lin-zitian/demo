package com.example.demo.controller;

import com.example.demo.common.BaseResult;
import com.example.demo.common.ResultCode;
import com.example.demo.constant.UserRole;
import com.example.demo.repository.model.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {

    @Autowired
    private UserService userService;

    // GET: 获取所有用户
    @GetMapping
    @ApiOperation("获取所有用户")
    public BaseResult<List<User>> getAllUsers() {
        List<User> users = userService.list();
        return BaseResult.ok(users);
    }

    // GET: 根据ID获取用户
    @GetMapping("/{id}")
    @ApiOperation("根据ID获取用户")
    public BaseResult<User> getUserById(@PathVariable Integer id) {
        User user = userService.getById(id);
        if (user != null) {
            return BaseResult.ok(user);
        } else {
            return BaseResult.fail(ResultCode.NOT_FOUND);
        }
    }

    // POST: 创建用户（JSON 格式）
    @PostMapping("/json")
    @ApiOperation("创建用户(JSON格式)")
    public BaseResult<String> createUserWithJson(@RequestBody User user) {
        userService.save(user);
        return BaseResult.ok("User created with JSON");
    }

    // POST: 创建用户（form-data 格式）
    @PostMapping("/form")
    @ApiOperation("创建用户(form-data格式)")
    public BaseResult<String> createUserWithFormData(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam(required = false) String phone) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setPhone(phone);
        newUser.setRole(UserRole.ADMIN.getValue());
        userService.save(newUser);
        return BaseResult.ok("User created with form-data");
    }

    // PUT: 更新用户（JSON 格式）
    @PutMapping("/{id}/json")
    @ApiOperation("更新用户(JSON格式)")
    public BaseResult<String> updateUserWithJson(@PathVariable Integer id, @RequestBody User user) {
        User existingUser = userService.getById(id);
        if (existingUser == null) {
            return BaseResult.fail(ResultCode.NOT_FOUND);
        }
        user.setId(id);
        userService.updateById(user);
        return BaseResult.ok("User updated with JSON");
    }

    // PUT: 更新用户（form-urlencoded 格式）
    @PutMapping("/{id}/form")
    @ApiOperation("更新用户(form-urlencoded格式)")
    public BaseResult<String> updateUserWithFormUrlencoded(@PathVariable Integer id,
                                                           @RequestParam(required = false) String username,
                                                           @RequestParam(required = false) String password,
                                                           @RequestParam(required = false) String phone) {
        User existingUser = userService.getById(id);
        if (existingUser == null) {
            return BaseResult.fail(ResultCode.NOT_FOUND);
        }
        if (username != null) {
            existingUser.setUsername(username);
        }
        if (password != null) {
            existingUser.setPassword(password);
        }
        if (phone != null) {
            existingUser.setPhone(phone);
        }
        userService.updateById(existingUser);
        return BaseResult.ok("User updated with form-urlencoded");
    }

    // DELETE: 删除用户
    @DeleteMapping("/{id}")
    @ApiOperation("删除用户")
    public BaseResult<String> deleteUser(@PathVariable Integer id) {
        if (userService.removeById(id)) {
            return BaseResult.ok("User deleted");
        } else {
            return BaseResult.fail(ResultCode.NOT_FOUND);
        }
    }
}