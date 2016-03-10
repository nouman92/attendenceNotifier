package attendenceNotifier.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import attendenceNotifier.dao.UserRepository;
import attendenceNotifier.model.User;

@Controller
@RequestMapping("/Login")
public class LoginController {
	@Autowired
	UserRepository userRepo;

	@RequestMapping(value="/authenticate",method = RequestMethod.POST)
	public String login(String user,String pass){
		User userObject =userRepo.findByNameAndPassword(user,pass);
		JSONObject obj = new JSONObject();
		if(userObject != null){
			obj.put("user", userObject.getName());
			obj.put("sessionId", "secretKey");//temp
		}
		return obj.toJSONString();
	}
}
