package student.community.community.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.community.community.dto.PaginationDTO;
import student.community.community.dto.QuestionDTO;
import student.community.community.mapper.QuestionMapper;
import student.community.community.mapper.UserMapper;
import student.community.community.model.Question;
import student.community.community.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
        @Autowired
        private QuestionMapper questionMapper;
        @Autowired
        private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO>questionDTOList=new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions){
            User  user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//把第一个的所有属性拷贝到第二个
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
            paginationDTO.setQuestions(questionDTOList);


            Integer totalCount=questionMapper.count();
            paginationDTO.setPagination(totalCount,page,size);

            return paginationDTO;
    }
}
