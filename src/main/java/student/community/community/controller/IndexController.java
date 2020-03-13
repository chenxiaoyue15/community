package student.community.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import student.community.community.dto.PaginationDTO;
import student.community.community.dto.QuestionDTO;
import student.community.community.mapper.QuestionMapper;
import student.community.community.mapper.UserMapper;
import student.community.community.model.Question;
import student.community.community.model.User;
import student.community.community.services.QuestionService;

import javax.naming.Name;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size){
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies){
            if (cookie.getName().equals("token")){
                String token = cookie.getValue();
                System.out.println(token);
                User user = userMapper.findByToken(token);
                if (user !=null){

                    request.getSession().setAttribute("user",user );
                }
                break;
            }
        }
        PaginationDTO pagination = questionService.list(page,size);
        model.addAttribute("pagination",pagination);


        return "index"; }

}
