package student.community.community.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.community.community.dto.CommentDTO;
import student.community.community.enums.CommentTypeEnum;
import student.community.community.enums.NotificationStatusEnum;
import student.community.community.enums.NotificationTypeEnum;
import student.community.community.mapper.CommentMapper;
import student.community.community.mapper.NotificationMapper;
import student.community.community.mapper.QuestionMapper;
import student.community.community.mapper.UserMapper;
import student.community.community.model.Comment;
import student.community.community.model.Notification;
import student.community.community.model.Question;
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
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public void insert(Comment comment, User commentator) {
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //插入评论
            Comment dbComment = commentMapper.selectById(comment.getParentId());
            Question question = questionMapper.getById(dbComment.getParentId());
            commentMapper.insert(comment);


            //增加评论数
            Comment updateCommentCount = new Comment();
            updateCommentCount.setId(comment.getParentId());
            //updateCommentCount.setCommentCount(comment.getCommentCount()+1);
            commentMapper.incCommentCount(updateCommentCount);
            //创建通知
            createNotify(comment, dbComment.getCommentator(), commentator.getAccountId(), question.getTitle(), NotificationTypeEnum.REPLY_COMMENT, question.getId());
        } else {
            //回复问题
            Question question = questionMapper.getById(comment.getParentId());
//            comment.setCommentCount(0);
            commentMapper.insert(comment);
            question.setId(comment.getParentId());
            questionMapper.updateCommentCount(question);
            //创建通知
            createNotify(comment, question.getCreator(), commentator.getAccountId(), question.getTitle(),NotificationTypeEnum.REPLY_QUESTION, question.getId());
        }

    }

    //        创建通知的方法
    private void createNotify(Comment comment, Integer receiver, String notifierName, String outerTitle, NotificationTypeEnum notificationType, Integer outerId) {
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationType.getType());
        notification.setOuterId(outerId);
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insert(notification);
    }

    public List<CommentDTO> listByTargetId(Integer id, CommentTypeEnum type) {
        //回显评论
        List<Comment> comments = commentMapper.getById(id, type.getType());
        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        Set<Integer> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Integer> userIds = new ArrayList<>();
        userIds.addAll(commentators);
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < commentators.size(); i++) {
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
