package student.community.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import student.community.community.dto.CommentCreateDTO;
import student.community.community.dto.ResultDTO;
import student.community.community.mapper.QuestionMapper;
import student.community.community.model.Comment;
import student.community.community.model.Question;
import student.community.community.model.User;
import student.community.community.services.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private QuestionMapper questionMapper;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");



        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        commentService.insert(comment);
        Question question = questionMapper.getById(comment.getParentId());//把question里面id等于id的数据传过来
        Question updateQuestion = new Question();//新建一个方法
        updateQuestion.setId(question.getId());//把question里的id赋给updateQuestion
        updateQuestion.setCommentCount(question.getCommentCount() + 1);//把question里的浏览数加1赋给updateQuestion
        questionMapper.updateCommentCount(updateQuestion);
        Map<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("massage", "成功");
        System.out.println(comment);
        return ResultDTO.okOf();

    }
}