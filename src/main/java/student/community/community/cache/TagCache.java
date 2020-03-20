package student.community.community.cache;

import org.apache.commons.lang3.StringUtils;
import student.community.community.dto.TagDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> get(){
       List<TagDTO> tagDTOS= new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setClassName("开发语言");
        program.setTags(Arrays.asList("js","php","java","python"));
        tagDTOS.add(program);

        TagDTO framework = new TagDTO();
        framework.setClassName("平台框架");
        framework.setTags(Arrays.asList("spring","flask","koa"));
        tagDTOS.add(framework);


        TagDTO server = new TagDTO();
        framework.setClassName("服务器");
        framework.setTags(Arrays.asList("linux","nginx","docker","ubuntu"));
        tagDTOS.add(server);

        return tagDTOS;
    }

//    public static String filterInvalid(String tags){
//        String[] split = StringUtils.split(tags, ",");
//        List<TagDTO> tagDTOS = get();
//        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
//        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
//        return invalid;
//    }
}
