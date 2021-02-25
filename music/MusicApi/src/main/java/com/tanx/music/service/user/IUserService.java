package com.tanx.music.service.user;

import com.tanx.music.pojo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/18 下午9:16
 */
@Service
public interface IUserService {

    /**
     * 增加用户
     *
     * @param user 用户的信息
     * @return 影响的行数
     */
    int insertUser(User user);

    /**
     * 删除用户
     * @param userId 用户ID
     * @return 返回删除的结果
     */
    int deleteUser(String userId);

    /**
     * 用户修改信息
     *
     * @param user 用户信息的信息
     * @return 返回修改的结果
     */
    int updateUser(User user);

    /**
     * 查询所有的用户
     * @return 返回所有用户
     */
    List<User> findAll();

    /**
     * 查询用户
     * @param userId 用户ID
     * @return 返回用户的信息
     */
    User findUser(String userId);

    /**
     * 登录
     * @param user 邮箱和密码
     * @return 返回用户信息
     */
    User findLogin(User user);

    /**
     * 查询邮箱
     * @param userEmail 邮箱
     * @return 返回用户信息
     */

    User findByUserEmail(String userEmail);

    /**
     * 修改密码
     * @param userEmail 用户邮箱
     * @param password 密码
     * @return 返回用户信息
     */

    int updatePassword(String userEmail,String password);

    /**
     * 返回所有用户的个数
     * @return 返回个数
     */
    int findUserLength();
}
