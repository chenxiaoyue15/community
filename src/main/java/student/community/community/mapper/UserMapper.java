package student.community.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
    public List<Map<String,Object>> query(@Param("uname") String name, @Param("password") String password);

    @Select("insert into T_USER(name,password) values (#{uname},#{password})")
    public List<Map<String,Object>> add(@Param("uname") String name, @Param("password") String password);

}
