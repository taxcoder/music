package com.tanx.music.controller.manager;

import com.tanx.music.pojo.model.User;
import com.tanx.music.service.user.UserServiceImpl;
import com.tanx.music.utils.ConstantUtil;
import com.tanx.music.utils.ObtainHeadUtil;
import com.tanx.music.utils.ReturnUtil;
import com.tanx.music.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/28 下午7:07
 */
@RestController
@RequestMapping("/admin/administrator")
public class UserManagerController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/allUser")
    public String allUser(){
        return ReturnUtil.getInstance().success(userService.findAll());
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(String userId){
        if(userService.deleteUser(userId) == 0){
            return ReturnUtil.getInstance().error("删除失败！");
        }
        return ReturnUtil.getInstance().success("删除成功！");
    }

    @PutMapping("/updateUser")
    public String updateUser(String userId,String username,String password,String sex,String phone,String  birth,String desc){
        if(userId == null || userId.length() == 0){
            return ReturnUtil.getInstance().success("用户ID为空！");
        }
        User user = new User();
        user.setUserId(userId);
        if(username != null && username.length() != 0){
            user.setUserName(username);
        }
        if(username != null && username.length() != 0 && password.matches(ConstantUtil.PASSWORD_REGEX)){
            user.setUserPassword(new BCryptPasswordEncoder().encode(password));
        }
        if(sex.length() != 0 && sex.matches(ConstantUtil.MATCH)){
            user.setUserSex(Integer.parseInt(sex));
        }
        if(phone.length() == 11 && phone.matches(ConstantUtil.MATCH)){
            user.setUserPhone(phone);
        }
        if(birth != null && birth.matches(ConstantUtil.MATCH)){
            Timestamp date = new Timestamp(Long.parseLong(birth));
            user.setUserBornTime(date);
        }
        if(desc != null && desc.length() != 0){
            user.setUserDesc(desc);
        }
        if(userService.updateUser(user) == 0){
            return ReturnUtil.getInstance().error("修改失败！");
        }
        return ReturnUtil.getInstance().success("修改成功！");
    }

    @PostMapping("/addUser")
    public String addUser(String email,String password,String sex,String phone,String username,String birth,String desc){
        String formatSuccess = isFormatSuccess(email, password);
        if(formatSuccess != null){
            return ReturnUtil.getInstance().error(formatSuccess);
        }
        User user = new User();
        user.setUserId(UuidUtil.uuidComplete());
        user.setUserEmail(email);
        user.setUserPassword(new BCryptPasswordEncoder().encode(password));
        if(!"0".equals(sex) && !"1".equals(sex) && !"2".equals(sex)){
            return ReturnUtil.getInstance().error("性别输入错误！");
        }
        user.setUserSex(Integer.parseInt(sex));
        if(phone.length() == 0){
            user.setUserPhone(null);
        }else if(phone.length() == 11 && phone.matches(ConstantUtil.MATCH)){
            user.setUserPhone(phone);
        }else{
            return ReturnUtil.getInstance().error("手机号应为11位数字！");
        }
        if(username.length() == 0){
            user.setUserName(UuidUtil.uuidComplete());
        }else{
            user.setUserName(username);
        }

        if(birth.length() != 0 && birth.matches(ConstantUtil.MATCH)){
            user.setUserBornTime(new Timestamp(Long.parseLong(birth)));
        }
        user.setUserDesc(desc == null || desc.length() == 0 ? null : desc);
        try {
            user.setUserHeadAddress(new ObtainHeadUtil().headImage("head.image"));

        } catch (IOException e) {
            return ReturnUtil.getInstance().error("增加失败！");
        }
        return userService.insertUser(user) == 0?ReturnUtil.getInstance().error("增加失败！"):ReturnUtil.getInstance().success("增加成功！");
    }

    public String isFormatSuccess(String userEmail,String userPassword){
        if(!userEmail.matches(ConstantUtil.EMAIL_REGEX)){
            return ReturnUtil.getInstance().error("邮箱格式错误！");
        }

        if(!userPassword.matches(ConstantUtil.PASSWORD_REGEX)){
            return ReturnUtil.getInstance().error("密码需在8到16个字符之间,且必须包含数字、字母,区分大小写！");
        }
        return null;
    }
}
