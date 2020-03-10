package student.community.community.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import student.community.community.model.User;
import student.community.community.services.UserServices;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserServices userServices;

    @RequestMapping("/login")
    public String login() {
        return "login.html";
    }

    @RequestMapping(value = "/loginsuc" , method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> loginsuc(String name, String password) {
        System.out.println(name);
        System.out.println(userServices.query(name, password));
        System.out.println("login controller");
        int i = userServices.query(name,password).size();
        System.out.println(i);
        Map<String, Object> map = new HashMap< >();
        if(i<=0)
        { map.put("success",false);}
        else{map.put("success",true);}

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