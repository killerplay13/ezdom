package tw.com.cha102.coach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.coach.model.dao.CoachMessageRepository;
import tw.com.cha102.coach.model.dto.ByCoachMessage;
import tw.com.cha102.coach.model.entity.CoachMessageVO;

import java.util.List;
import java.util.Optional;

@Service
public class CoachMessageService {

    @Autowired
    private CoachMessageRepository coachMessageRepository;

    // ==================== 教練留言板 ==================== //

    // 新增留言
    public CoachMessageVO addMessage(CoachMessageVO message){
        message.setMessage("新增留言成功");
        message.setSuccessful(true);
        return coachMessageRepository.save(message);
    }

    // 刪除留言
    public String deleteMessage(Integer id){
        coachMessageRepository.deleteById(id);
        return "刪除留言成功";
    }

    // 修改留言
    public CoachMessageVO updateMessage(Integer id, String content){
        Optional<CoachMessageVO> check = coachMessageRepository.findById(id);

        if(check.isPresent()){
            CoachMessageVO message = check.get();
            message.setContent(content);
            message.setMessage("修改留言成功");
            message.setSuccessful(true);
            return coachMessageRepository.save(message);
        }else {
            return null;
        }

    }

    // 留言板
    public List<ByCoachMessage> getCoachMessage(Integer coachId){
        return coachMessageRepository.getCoachMessage(coachId);
    }
}
