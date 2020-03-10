package student.community.community.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import student.community.community.mapper.UserMapper;
import student.community.community.model.User;

import java.util.List;
import java.util.Map;
@Component
public class UserServices {
    @Autowired
    UserMapper userMapper;

    public List<Map<String,Object>> query(String uname, String password){

        return  userMapper.query(uname,password);
    }

    public List<Map<String,Object>> add(String uname, String password){
        return  userMapper.add(uname,password);
    }
}
