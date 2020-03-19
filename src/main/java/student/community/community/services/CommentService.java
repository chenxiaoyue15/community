package student.community.community.services;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.community.community.dto.CommentDTO;
import student.community.community.dto.QuestionDTO;
import student.community.community.mapper.CommentMapper;
import student.community.community.mapper.QuestionMapper;
import student.community.community.mapper.UserMapper;
import student.community.community.model.Comment;
import student.community.community.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;

    public void insert(Comment comment) {
        commentMapper.insert(comment);
    }

    public List<CommentDTO> listByQuestionId(Integer id) {
        List<Comment> comments = commentMapper.getById(id);
        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        Set<Integer> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Integer> userIds = new ArrayList<>();
        userIds.addAll(commentators);
       ArrayList<User> users = new ArrayList<>();
        for (int i = 0;i<commentators.size();i++)
        {
            User user = userMapper.ById(userIds.get(i));
            users.add(user);


        }



       // List<User> users = userMapper.ById(userIds);

        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
                CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;

        }).collect(Collectors.toList());
        return commentDTOS;
    }
}
