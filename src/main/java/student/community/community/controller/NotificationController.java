package student.community.community.controller;

import com.sun.corba.se.spi.ior.IdentifiableFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import student.community.community.dto.NotificationDTO;
import student.community.community.dto.PaginationDTO;
import student.community.community.enums.NotificationTypeEnum;
import student.community.community.mapper.NotificationMapper;
import student.community.community.model.User;
import student.community.community.services.NotificationService;

import javax.servlet.http.HttpServletRequest;


@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(@PathVariable(name = "id") Integer id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.read(id, user);
        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType()||NotificationTypeEnum.REPLY_QUESTION.getType()==notificationDTO.getType()) {

            return "redirect:/question/" + notificationDTO.getOuterId();
        }else {
            return "redirect:/";
        }
    }
}
