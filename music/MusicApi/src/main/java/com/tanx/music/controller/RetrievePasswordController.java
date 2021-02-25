package com.tanx.music.controller;

import com.tanx.music.service.mail.MailSendComplexImpl;
import com.tanx.music.service.user.UserServiceImpl;
import com.tanx.music.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/28 上午11:50
 */
@RestController
@RequestMapping("/retrieve")
public class RetrievePasswordController {


    @Value("${spring.profiles.active}")
    private String active;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/send")
    public String sendEmail(String userEmail){
        if(!userEmail.matches(ConstantUtil.EMAIL_REGEX)){
            return ReturnUtil.getInstance().error("邮箱格式错误！");
        }

        if(userService.findByUserEmail(userEmail) == null){
            return ReturnUtil.getInstance().error("不存在此用户！");
        }

        MapTimerUtil instance = MapTimerUtil.getInstance(300000);
        try {
            String check = UuidUtil.uuidComplete(128);
            instance.put(userEmail,check);
            String data = PropertiesUtil.getInstance().get("web."+active+".index")+"retrievePassword?email="+userEmail+"&check="+check;
            String href = "<a href="+data+">"+data+"</a>";
            String send = MailSendComplexImpl.getInstance(userEmail, href, "邮件发送成功，5分钟内有效！","修改密码").send();
            return ReturnUtil.getInstance().success(send);
        } catch (Exception e) {
            return ReturnUtil.getInstance().error("邮件发送失败！");
        }
    }

    @PutMapping("/newPassword")
    public String changePassword(String username,String check,String password){
        if(!username.matches(ConstantUtil.EMAIL_REGEX)){
            return ReturnUtil.getInstance().error("邮件格式错误！");
        }

        if(!password.matches(ConstantUtil.PASSWORD_REGEX)){
            return ReturnUtil.getInstance().error("密码需在8到16个字符之间,且必须包含数字、字母,区分大小写！");
        }

        MapTimerUtil mapTimerUtil = MapTimerUtil.getInstance();
        String data  = (String) mapTimerUtil.get(username);
        if(data == null){
            return ReturnUtil.getInstance().error("验证码为空，请重新发送邮件！");
        }
        if(!data.equals(check)){
            return ReturnUtil.getInstance().error("验证码错误！");
        }
        // 修改密码
        if(userService.updatePassword(username, new BCryptPasswordEncoder().encode(password)) == 0){
            return ReturnUtil.getInstance().error("修改失败!");
        }
        // 密码修改成功，删除验证码
        mapTimerUtil.remove(username);
        return ReturnUtil.getInstance().success("修改成功!");
    }
}
