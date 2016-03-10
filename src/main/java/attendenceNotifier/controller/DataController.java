package attendenceNotifier.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import attendenceNotifier.dao.AttendenceRepository;
import attendenceNotifier.dao.ClassRepository;
import attendenceNotifier.dao.StudentRepository;
import attendenceNotifier.model.ClassObj;
import attendenceNotifier.model.User;

@Controller
@RequestMapping("/Data")
public class DataController {
	@Autowired
	ClassRepository classRepository;
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	AttendenceRepository attendenceRepo;

	@RequestMapping(value="/getData",method = RequestMethod.POST)
	public String login(String Date,String sessionId){
		JSONObject classobjects = new JSONObject();
		if(sessionId.equals("secretKey")){
			List<ClassObj> classes = classRepository.findAll();
			for(ClassObj obj :classes ){
				classobjects.put("class", obj.getName());
				//attendenceRepo.findByClasAndDate();
			}
		}else{
			
		}
		return "";
	}
}
