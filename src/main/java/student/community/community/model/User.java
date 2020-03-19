package student.community.community.model;


import lombok.Data;

@Data
public class User {


    private Integer id;
    private String name;
    private Integer age;
    private String pwd;
    private String token;
    private String avatarUrl;
    private String accountId;

}


