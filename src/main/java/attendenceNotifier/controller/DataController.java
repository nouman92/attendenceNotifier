package attendenceNotifier.controller;

import java.text.SimpleDateFormat;
import java.util.List;

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
@RequestMapping("/GetData")
public class DataController {
	@Autowired
	ClassRepository classRepository;
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	AttendenceRepository attendenceRepo;
	
	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping(value="/AttendenceByDateAndAttendenceAndClass",method = RequestMethod.POST)
	public String getAttendenceDetails(String date,String sessionId,boolean attendence ,String classId){
		JSONArray array = new JSONArray();
		if(sessionId.equals("secretKey")){
			List<Attendence> attendence1 = attendenceRepo.findByAttendenceClasAndDate(attendence,classId, date);
			JSONArray studentsArray = new JSONArray();
			for( Attendence atendence : attendence1 ){
				studentsArray.add(atendence.getStudent());
			}
			JSONObject data = new JSONObject();
			data.put("students", studentsArray);
			array.add(data);
		}else{
			JSONObject error = new JSONObject();
			error.put("code", "401");
			error.put("message", "permission Denied");
			array.add(error);
		}
		return array.toJSONString();
	}
	@RequestMapping(value="/AttendenceByDateAndClass",method = RequestMethod.POST)
	public String getAttendenceDetails(String date,String sessionId,String classId){
		JSONArray array = new JSONArray();
		if(sessionId.equals("secretKey")){
			List<Attendence> attendence1 = attendenceRepo.findByClasAndDate(classId, date);
			JSONArray studentsArray = new JSONArray();
			for( Attendence atendence : attendence1 ){
				studentsArray.add(atendence.getStudent());
			}
			JSONObject data = new JSONObject();
			data.put("students", studentsArray);
			array.add(data);
		}else{
			JSONObject error = new JSONObject();
			error.put("code", "401");
			error.put("message", "permission Denied");
			array.add(error);
		}
		return array.toJSONString();
	}
	@RequestMapping(value="/StatsToday",method = RequestMethod.POST)
	public String getStats(String date,String sessionId){
		JSONArray array = new JSONArray();
		if(sessionId.equals("secretKey")){
			List<ClassObj> classes = classRepository.findAll();
			for(ClassObj obj :classes ){
				int total = attendenceRepo.findTotal(obj.getId(),date);
				int absent = attendenceRepo.getAttendenceCount(false,obj.getId(),date);
				int present = attendenceRepo.getAttendenceCount(true,obj.getId(),date);
				
				JSONObject atendence = new JSONObject();
				atendence.put("total", total);
				atendence.put("present", present);
				atendence.put("absent", absent);
				
				JSONObject classobjects = new JSONObject();
				classobjects.put("class", obj);
				classobjects.put("attendence", atendence);
				array.add(classobjects);
			}
		}else{
			JSONObject error = new JSONObject();
			error.put("code", "401");
			error.put("message", "permission Denied");
			array.add(error);
		}
		return array.toJSONString();
	}
	@RequestMapping(value="/ClassAndStudentData",method = RequestMethod.POST)
	public String classStudentsData(String sessionId){
		JSONArray array = new JSONArray();
		if(sessionId.equals("secretKey")){
			List<ClassObj> classes = classRepository.findAll();
			for(ClassObj obj :classes ){
				JSONArray studentsArray = new JSONArray();
				for(Student student : obj.getStudents()){
					studentsArray.add(student);
				}
				JSONObject classobjects = new JSONObject();
				classobjects.put("class", obj);
				classobjects.put("students", studentsArray);
				array.add(classobjects);
			}
		}else{
			JSONObject error = new JSONObject();
			error.put("code", "401");
			error.put("message", "permission Denied");
			array.add(error);
		}
		return array.toJSONString();
	}
}
