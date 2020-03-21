package student.community.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import student.community.community.model.Notification;


@Mapper
public interface NotificationMapper {

    @Insert("insert into notification(id,type,gmt_create,notifier,receiver,outerId,status) values (#{id},#{type},#{gmtCreate},#{notifier},#{receiver},#{outerId},#{status})")
    void insert(Notification notification);
}
