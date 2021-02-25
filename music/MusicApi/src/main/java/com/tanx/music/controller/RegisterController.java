package com.tanx.music.controller;

import com.tanx.music.pojo.model.User;
import com.tanx.music.service.mail.MailSendSimpleImpl;
import com.tanx.music.service.user.UserServiceImpl;
import com.tanx.music.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/27 下午8:40
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/emptyUsername")
    public String isEmptyUsername(String username){
        if(!username.matches(ConstantUtil.EMAIL_REGEX)){
            return ReturnUtil.getInstance().error("邮箱格式错误！");
        }

        User byUserEmail = userService.findByUserEmail(username);
        System.out.println(byUserEmail);
        if(byUserEmail != null){
            return ReturnUtil.getInstance().error("此用户已存在！");
        }
        return ReturnUtil.getInstance().success("ok！");
    }

    @PostMapping("/userRegister")
    public String userRegister(String username,String password,String code){
        this.isFormatSuccess(username,password);
        MapTimerUtil mapTimerUtil = MapTimerUtil.getInstance();
        String data;
        try {
            data = (String) mapTimerUtil.get(username);
            if(data == null){
                return ReturnUtil.getInstance().error("验证码已过期，请重新获取！");
            }
            if(!data.equals(code)){
                return ReturnUtil.getInstance().error("验证码错误！");
            }
            // 用户注册
            User user = new User();
            user.setUserId(UuidUtil.uuidComplete());
            user.setUserName(UuidUtil.uuidComplete());
            user.setUserPassword(passwordEncoder.encode(password));
            user.setUserEmail(username);
            user.setUserSex(0);
            user.setUserBornTime(null);
            user.setUserPhone(null);
            user.setUserHeadAddress(new ObtainHeadUtil().headImage("head.image"));
            if(userService.insertUser(user) != 0){
                MapTimerUtil.getInstance().remove(username);
                return ReturnUtil.getInstance().success("注册成功！");
            }
            return ReturnUtil.getInstance().error("注册失败，请重试！");
        } catch (Exception e) {
            return ReturnUtil.getInstance().error("注册失败，请重试！");
        }
    }

    @PostMapping("/code")
    public String addCode(String userEmail){
        if(!userEmail.matches(ConstantUtil.EMAIL_REGEX)){
            throw new RuntimeException("邮箱格式不符合要求！");
        }

        MapTimerUtil instance = MapTimerUtil.getInstance();
        String str = (String) instance.get(userEmail);
        if(str != null){
            return ReturnUtil.getInstance().success("验证码30分钟内有效！");
        }
        String code = CreateRandomUtil.random(6).toUpperCase();
        try {
            instance.put(userEmail,code);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("验证码获取异常！");
        }
        String href = "您本次的验证码为:"+code;
        MailSendSimpleImpl simple = MailSendSimpleImpl.getInstance(userEmail,href,"注册验证码");
        try {
            return ReturnUtil.getInstance().success(simple.send());
        } catch (Exception e) {
            throw new RuntimeException("邮件发送失败！");
        }
    }

    public void isFormatSuccess(String userEmail,String userPassword){
        if(!userEmail.matches(ConstantUtil.EMAIL_REGEX)){
            throw new RuntimeException("邮箱格式错误！");
        }

        if(!userPassword.matches(ConstantUtil.PASSWORD_REGEX)){
            throw new RuntimeException("密码需在8到16个字符之间,且必须包含数字、字母,区分大小写！");
        }
    }
}
