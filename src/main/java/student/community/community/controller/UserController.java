package student.community.community.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import student.community.community.mapper.UserMapper;
import student.community.community.model.User;
import student.community.community.services.UserServices;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {
    @Autowired
    private UserServices userServices;
    @Autowired
    private  UserMapper userMapper;
    @RequestMapping("/login")
    public String login() {
        return "login.html";
    }
    @RequestMapping(value = "/loginsuc" , method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> loginsuc(String name, String password, HttpServletRequest request, HttpServletResponse response, Model model) {


        int i = userServices.query(name,password).size();
        Map<String, Object> map = new HashMap< >();
        if(i<=0)
        { map.put("success",false);

        }
        else{map.put("success",true);
            //request.getSession().setAttribute("user",name);
            User user=new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(name);
            user.setPwd(password);
            userMapper.insert(user);

            // 创建cookie并将成功登陆的用户保存在里面
            response.addCookie(new Cookie("token",token));// 服务器返回给浏览器cookie以便下次判断
            System.out.println(token);
        }


        return map;
    }


    @RequestMapping("/register")
    public String regist() {
        return "register.html";
    }

    @RequestMapping("/registsuc")
    @ResponseBody
    public String registsuc(String name, String password) {
        System.out.println(name);
        System.out.println(userServices.add(name, password));
        System.out.println("login controller");
        Map<String, String> map = new HashMap<String, String>();
        map.put("success", "success");
        return "success";
    }
}