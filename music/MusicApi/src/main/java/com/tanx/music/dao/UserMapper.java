package com.tanx.music.dao;

import com.tanx.music.pojo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/6 下午3:21
 */

@Mapper
@Repository
public interface UserMapper {

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
    int deleteUser(@Param("userId")String userId);

    /**
     * 用户修改信息
     *
     * @param user 用户信息的信息
     * @return 返回修改的结果
     */
    int updateUser(User user);

    /**
     *
     * 查询所有的用户
     * @return 返回所有用户
     */
    List<User> findAll();

    /**
     * 查询用户
     * @param userId 用户ID
     * @return 返回用户的信息
     */
    User findUser(@Param("userId")String userId);

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

    User findByUserEmail(@Param("userEmail") String userEmail);

    /**
     * 修改密码
     * @param map map集合
     * @return 返回用户信息
     */

    int updatePassword(Map<String,Object> map);

    /**
     * 返回用户个数
     * @return 返回用户个数
     */
    Integer findUserLength();
}
