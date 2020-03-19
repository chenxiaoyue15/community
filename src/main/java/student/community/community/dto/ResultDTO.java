package student.community.community.dto;

import lombok.Data;

@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;

    }
    public static ResultDTO okOf(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("成功");
        return resultDTO;
    }
    public static ResultDTO errorOf1(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(2002);
        resultDTO.setMessage("不能空");
        return resultDTO;

    }
}
