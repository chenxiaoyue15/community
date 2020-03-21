package student.community.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import student.community.community.dto.PaginationDTO;
import student.community.community.mapper.UserMapper;
import student.community.community.model.Notification;
import student.community.community.model.User;
import student.community.community.services.NotificationService;
import student.community.community.services.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")//他是一个get方法，地址是/profile，就是希望访问profile的时候来调用这个地址
    public String profile(@PathVariable(name = "action") String action,
                          Model model, HttpServletRequest request,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {//定义一个方法，String的意思就是返回他对应的页面，
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
            model.addAttribute("pagination", paginationDTO);

        } else if ("replies".equals(action)) {
            PaginationDTO paginationDTO=notificationService.list(user.getId(), page, size);
            model.addAttribute("pagination", paginationDTO);
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");

        }

        return "profile";

    }
}
