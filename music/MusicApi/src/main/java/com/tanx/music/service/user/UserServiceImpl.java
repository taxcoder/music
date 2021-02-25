package com.tanx.music.service.user;

import com.tanx.music.dao.UserMapper;
import com.tanx.music.pojo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/18 下午9:18
 */
@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int deleteUser(String userId) {
        return userMapper.deleteUser(userId);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findUser(String userId) {
        return userMapper.findUser(userId);
    }

    @Override
    public User findLogin(User user) { return userMapper.findLogin(user); }

    @Override
    public User findByUserEmail(String userEmail) {
        return userMapper.findByUserEmail(userEmail);
    }

    @Override
    public int updatePassword(String userEmail, String password) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("userEmail",userEmail);
        map.put("password",password);
        return userMapper.updatePassword(map);
    }

    @Override
    public int findUserLength() {
        Integer userLength = userMapper.findUserLength();
        return userLength == null ? 0 : userLength;
    }
}
