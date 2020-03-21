package student.community.community.mapper;

import org.apache.ibatis.annotations.*;
import student.community.community.model.Notification;

import java.util.List;


@Mapper
public interface NotificationMapper {

    @Insert("insert into notification(id,type,gmt_create,notifier,receiver,outerId,status,notifier_name,outer_title) values (#{id},#{type},#{gmtCreate},#{notifier},#{receiver},#{outerId},#{status},#{notifierName},#{outerTitle})")
    void insert(Notification notification);
    @Select("select count(1) from  notification where receiver = #{userId}")
    Integer countByUserId(Integer userId);
    @Select("select * from notification  where receiver = #{userId} ORDER BY gmt_create DESC limit #{offset},#{size} ")
    List<Notification> listByUserId(@Param("userId")Integer userId, @Param("offset")Integer offset, @Param("size")Integer size);
    @Select("select * from notification  where id = #{id} ")
    Notification selectById(Integer id);

    @Update("update notification set status=#{status} where id = #{id}")
    void updateById(Notification notification);

    @Select("select count(1) from  notification where receiver = #{userId} and status = #{status}")
    Integer countById(@Param("userId")Integer userId, @Param("status")int status);
}
