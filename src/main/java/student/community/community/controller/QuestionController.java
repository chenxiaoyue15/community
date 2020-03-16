package student.community.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import student.community.community.dto.QuestionDTO;
import student.community.community.services.QuestionService;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id, Model model){
        QuestionDTO questionDTO = questionService.getById(id);//把数据库里id等于id的数据传给questionDTO
        questionService.incView(id);//累加阅读数，用incView方法更新数据库里的数据
        model.addAttribute("question",questionDTO);//回显数据到页面
        return "question";
    }

}
