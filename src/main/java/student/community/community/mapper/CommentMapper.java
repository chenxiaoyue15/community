package student.community.community.mapper;

import org.apache.ibatis.annotations.*;
import student.community.community.model.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("insert into comment(parent_id,type,gmt_create,gmt_modified,commentator,content,comment_count) values (#{parentId},#{type},#{gmtCreate},#{gmtModified},#{commentator},#{content},#{commentCount})")
     void insert(Comment comment);

    @Select("select * from comment where parent_id=#{id} ORDER BY gmt_create DESC")
    List<Comment> getById(@Param("id")Integer id,@Param("type")Integer type);
    //评论数加1
    @Update("update comment set comment_count=#{commentCount} where id = #{id}")
    void incCommentCount(Comment updateCommentCount);
}
