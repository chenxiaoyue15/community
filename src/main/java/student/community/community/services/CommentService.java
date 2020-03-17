package student.community.community.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.community.community.mapper.CommentMapper;
import student.community.community.mapper.QuestionMapper;
import student.community.community.model.Comment;
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    public void insert(Comment comment) {
        commentMapper.insert(comment);
    }
}
