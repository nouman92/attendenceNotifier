package attendenceNotifier.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import attendenceNotifier.dao.AttendenceRepository;
import attendenceNotifier.dao.ClassRepository;
import attendenceNotifier.dao.StudentRepository;
import attendenceNotifier.model.Attendence;
import attendenceNotifier.model.ClassObj;
import attendenceNotifier.model.Student;

@Controller
@RequestMapping("/SetData")
public class updateController {
	
	@Autowired
	AttendenceRepository attendenceRepo;
	@Autowired
	ClassRepository classRepository;
	@Autowired
	StudentRepository studentRepo;
	
	@RequestMapping(value="/AttendenceByDateAndClassAndStudent",method = RequestMethod.POST)
	public String getAttendenceDetails(String sessionId,String date,String classId,String studentId){
		JSONArray array = new JSONArray();
		if(sessionId.equals("secretKey")){
			Attendence attendence1 = attendenceRepo.findByClasAndDateAndStudent(classId, date,  studentId);
			if( attendence1 == null){
				attendence1 = new Attendence();
				attendence1.setAttendance(true);
				ClassObj classObj = classRepository.findOne(Integer.parseInt(classId));
				attendence1.setClas(classObj);
				attendence1.setDate(date);
				Student student = studentRepo.findOne(Integer.parseInt(studentId));
				attendence1.setStudent(student);
			}
			else{
				attendence1.setAttendance(true);
			}
			attendenceRepo.save(attendence1);
			JSONObject data = new JSONObject();
			data.put("code", "1");
			data.put("message", "Attendence marked");
			array.add(data);
		}else{
			JSONObject error = new JSONObject();
			error.put("code", "401");
			error.put("message", "permission Denied");
			array.add(error);
		}
		return array.toJSONString();
	}
	

}
