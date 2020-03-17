package student.community.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import student.community.community.model.Comment;

@Mapper
public interface CommentMapper {
    @Insert("insert into comment(parent_id,type,gmt_create,gmt_modified,commentator,content) values (#{parentId},#{type},#{gmtCreate},#{gmtModified},#{commentator},#{content})")
     void insert(Comment comment);


}
