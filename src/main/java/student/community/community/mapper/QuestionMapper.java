package student.community.community.mapper;

import org.apache.ibatis.annotations.*;
import student.community.community.model.Question;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag,comment_count) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag},#{commentCount})")
    void create(Question question);
    @Select("select * from question ORDER BY gmt_create DESC limit #{offset},#{size} ")
//    ORDER BY gmt_create DESC 按时间最新排序
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question  where creator = #{userId}  limit #{offset},#{size} ")
    List<Question> listByUserId(@Param("userId") int userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);
    @Select("select count(1) from question where creator = #{userId}")
    Integer countByUserId(@Param("userId") int userId);

    @Select("select * from question  where id = #{id}  ")
    Question getById(@Param("id")Integer id);

    @Update("update question set title = #{title},description =#{description},gmt_modified = #{gmtModified},tag =#{tag} where id = #{id}")
    void update(Question question);

    @Update("update question set view_count=#{viewCount} where id = #{id}")//只更新浏览数
    void updateViewCount(Question updateQuestion);

    @Update("update question set comment_count=comment_count where id = #{id}")
    void updateCommentCount(Question updateQuestion);




    @Select("select * from question  where id != #{id} and tag regexp #{tag}")
    List<Question> selectRelated(Question question);

}
