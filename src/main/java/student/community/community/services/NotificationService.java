package student.community.community.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.community.community.dto.NotificationDTO;
import student.community.community.dto.PaginationDTO;
import student.community.community.dto.QuestionDTO;
import student.community.community.enums.NotificationStatusEnum;
import student.community.community.enums.NotificationTypeEnum;
import student.community.community.mapper.NotificationMapper;
import student.community.community.mapper.UserMapper;
import student.community.community.model.Notification;
import student.community.community.model.Question;
import student.community.community.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private UserMapper userMapper;
    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO();
        Integer totalCount=notificationMapper.countByUserId(userId);
        paginationDTO.setPagination(totalCount,page,size);
        Integer offset = size * (page - 1);

        List<Notification> notifications = notificationMapper.listByUserId(userId,offset,size);

        if (notifications.size()==0){
            return paginationDTO;
        }
        List<NotificationDTO>notificationDTOS=new ArrayList<>();
        for (Notification notification:notifications){
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification,notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOS);
        return paginationDTO;



    }

    public Integer unreadCount(Integer userId) {

        return notificationMapper.countById(userId,NotificationStatusEnum.UNREAD.getStatus());
    }

    public NotificationDTO read(Integer id, User user) {

        Notification notification = notificationMapper.selectById(id);
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateById(notification);
        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification,notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }
}
