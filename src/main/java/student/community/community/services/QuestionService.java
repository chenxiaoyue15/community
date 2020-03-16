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

    public PaginationDTO list(int userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount=questionMapper.countByUserId(userId);
        paginationDTO.setPagination(totalCount,page,size);
        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.listByUserId(userId,offset,size);
        List<QuestionDTO>questionDTOList=new ArrayList<>();

        for (Question question : questions){
            User  user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//把第一个的所有属性拷贝到第二个
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);


        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) { // getByaId把两张表联系起来
        Question question=questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User  user = userMapper.findById(question.getCreator());//通过作者的值找到User表作者的信息
        questionDTO.setUser(user);//把user信息传给DTO
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId()==null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }else {

            question.setGmtModified(question.getGmtCreate());
            questionMapper.update(question);
        }
    }

    public void incView(Integer id) {
        Question question = questionMapper.getById(id);//把question里面id等于id的数据传过来
        Question updateQuestion = new Question();//新建一个方法
        updateQuestion.setId(question.getId());//把question里的id赋给updateQuestion
        updateQuestion.setViewCount(question.getViewCount()+1);//把question里的浏览数加1赋给updateQuestion
        questionMapper.updateViewCount(updateQuestion);//用updateViewCount方法更新数据库

    }
}
