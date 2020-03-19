package student.community.community.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.stereotype.Repository;
import student.community.community.model.User;

import javax.naming.Name;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface  UserMapper {
    @Select("select * from T_USER where name=#{uname} and pwd=#{password}")
     List<Map<String,Object>> query(@Param("uname") String name, @Param("password") String password);

    @Select("insert into T_USER(name,password) values (#{uname},#{password})")
     List<Map<String,Object>> add(@Param("uname") String name, @Param("password") String password);

    @Select("select * from T_USER where token = #{token}")
    User findByToken(@Param("token") String token);
    @Insert("insert into T_USER(token,name,pwd,avatar_url) values (#{token},#{name},#{password},#{avatarUrl})")
    public void insert(User user);

    @Select("select * from T_USER where id = #{id}")
    User findById(@Param("id") Integer id);

    @Update( "UPDATE T_USER SET token=#{token} WHERE name=#{name}")
    void update(User user);



    @Select("select * from T_USER where id = #{integer}")
    User ById(@Param("integer")Integer integer);


    //@Select("select * from T_USER where id  =#{userIds}")
    //List<User> ById(@Param("userIds")List<Integer> userIds);




}
