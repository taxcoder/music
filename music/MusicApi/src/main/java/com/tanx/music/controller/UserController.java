package com.tanx.music.controller;

import com.tanx.music.pojo.Result;
import com.tanx.music.pojo.model.CollectionSong;
import com.tanx.music.pojo.model.User;
import com.tanx.music.service.collectionSong.ICollectionSong;
import com.tanx.music.service.user.UserServiceImpl;
import com.tanx.music.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/18 下午9:20
 */
@RestController
@RequestMapping("/user")
@SuppressWarnings("all")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ICollectionSong collectionSong;

    @GetMapping("/v1/get/code")
    public void code(HttpServletRequest request,HttpServletResponse response) throws IOException {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/png");
            // 生成随机大写字串
            String verifyCode = CodeUtil.generateVerifyCode(4);
            request.getSession().setAttribute("code",verifyCode);
            // 生成图片
            int w = 130, h = 50;
            OutputStream out = response.getOutputStream();
            CodeUtil.outputImage(w, h, out, verifyCode);
    }

    public void isFormatSuccess(String userEmail,String userPassword){
        if(!userEmail.matches(ConstantUtil.EMAIL_REGEX)){
            throw new RuntimeException("邮箱格式错误！");
        }

        if(!userPassword.matches(ConstantUtil.PASSWORD_REGEX)){
            throw new RuntimeException("密码需在8到16个字符之间,且必须包含数字、字母,区分大小写！");
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        session.invalidate();
        return ReturnUtil.getInstance().success("注销成功！");
    }

    @GetMapping("/likeMusic")
    public Result<?> likeMusic(String username,HttpSession session){
        String user = (String) session.getAttribute("user");
        if(username == null){
            return new Result<>(0,"logout",new Exception("用户信息错误,请退出重新登录！"));
        }

        if(user == null || !user.equals(username)){
            return new Result<>(0,"logout",new Exception("用户信息错误,请退出重新登录！"));
        }

        User userData = userService.findByUserEmail(username);
        List<CollectionSong> collectionSongs = collectionSong.likeMusic(userData.getUserId());
        return new Result<>(1,collectionSongs,null);
    }

    @PutMapping("/addLikeMusic")
    public Result<String> addLikeMusic(String songId,String username,HttpSession session){
        String user = (String) session.getAttribute("user");
        if(username == null || songId == null){
            return new Result<>(0,null,new Exception("错误的参数！"));
        }

        if(user == null || !user.equals(username)){
            return new Result<>(0,"logout",new Exception("用户信息错误,请退出重新登录！"));
        }

        User userData = userService.findByUserEmail(username);
        if(collectionSong.selectCollectionSong(songId,userData.getUserId()) != null){
            return new Result<>(0,null,new Exception("此歌曲已在我喜欢之中！"));
        }

        if(collectionSong.addLikeMusic(UuidUtil.uuidComplete(),songId,userData.getUserId()) == 0){
            return new Result<>(0,null,new Exception("加入失败！"));
        }
        return new Result<>(1,"已加入我喜欢!",null);
    }

    @DeleteMapping("/deleteLikeMusic")
    public Result<String> deleteLikeMusic(String songId,String username,HttpSession session){
        String user = (String) session.getAttribute("user");
        if(username == null || songId == null){
            return new Result<>(0,null,new Exception("错误的参数！"));
        }

        if(user == null || !user.equals(username)){
            return new Result<>(0,"logout",new Exception("用户信息错误,请退出重新登录！"));
        }

        User userData = userService.findByUserEmail(username);

        if(collectionSong.selectCollectionSong(songId,userData.getUserId()) == null){
            return new Result<>(0,null,new Exception("此歌曲未在我喜欢之中！"));
        }

        if(collectionSong.deleteLikeMusic(songId,userData.getUserId()) == 0){
            return new Result<>(0,null,new Exception("移除失败！"));
        }
        return new Result<>(1,"已移除我喜欢!",null);
    }

    @GetMapping("/playIsLike")
    public Result<Boolean> playIsLike(String songId,String username,HttpSession session){
        String user = (String) session.getAttribute("user");
        if(username == null || songId == null){
            return new Result<>(0,null,new Exception("错误的参数！"));
        }

        if(user == null || !user.equals(username)){
            return new Result<>(0,null,new Exception("用户信息错误,请退出重新登录！"));
        }
        User userData = userService.findByUserEmail(username);
        CollectionSong collectionSong = this.collectionSong.selectCollectionSong(songId, userData.getUserId());
        return collectionSong == null ? new Result<>(1,false,null) : new Result<>(1,true,null);
    }
}
