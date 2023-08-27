package tw.com.cha102.coach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.coach.model.dto.ByCoachMessage;
import tw.com.cha102.coach.model.entity.CoachMessageVO;
import tw.com.cha102.coach.service.CoachMessageService;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/coach/message")
public class CoachMessageController{

		@Autowired
		private CoachMessageService coachMessageService;

		@PostMapping("/add")
		public CoachMessageVO add(@Valid @RequestBody CoachMessageVO coachMessageVO){
			return coachMessageService.addMessage(coachMessageVO);
		}

		@DeleteMapping("/delete/{messageId}")
		public String delete(@Valid @PathVariable Integer messageId){
			return coachMessageService.deleteMessage(messageId);
		}

		@PutMapping("/update/{messageId}")
		public CoachMessageVO update(@PathVariable Integer messageId,
									 @RequestBody CoachMessageVO updateMessage){
			return coachMessageService.updateMessage(messageId, updateMessage);
		}

		@GetMapping("/list/{coachId}")
		public List<ByCoachMessage> coachMessageList(@PathVariable Integer coachId){
			return coachMessageService.getCoachMessage(coachId);
		}


}
