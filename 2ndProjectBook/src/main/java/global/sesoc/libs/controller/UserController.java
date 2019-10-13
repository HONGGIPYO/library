package global.sesoc.libs.controller;

import java.io.File;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import global.sesoc.libs.dto.Users;
import global.sesoc.libs.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService uService;
	
	
	final static String UPLOADPATH="C:/SPRINGHOME/2ndProjectBook/src/main/webapp/resources/saveimg/";
	
	@RequestMapping(value = "signUp", method = RequestMethod.POST)
	public String signUp(Users user, MultipartFile uploadFile) {
		boolean signupResult;
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
		    int rIndex = rnd.nextInt(3);
		    switch (rIndex) {
		    case 0:
		        temp.append((char) ((int) (rnd.nextInt(26)) + 97));
		        break;
		    case 1:
		        temp.append((char) ((int) (rnd.nextInt(26)) + 65));
		        break;
		    case 2:
		        temp.append((rnd.nextInt(10)));
		        break;
		    }
		}
		user.setUsernum(temp.toString());
		
		if(!(uploadFile.isEmpty()||uploadFile==null||uploadFile.getSize()==0)){
			String orgName = uploadFile.getOriginalFilename();
			String savName;
			Date date = new Date();
			savName = date.getTime()+orgName;
			try {
				uploadFile.transferTo(new File(UPLOADPATH+savName));
	
			user.setImage(savName);
				signupResult = uService.signUp(user);
				if(!signupResult){
					return "redirect:/selectBook";
				}else{
					return "redirect:/selectBook";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:/selectBook";
		} else {
			return "redirect:/selectBook";
		}
	}
	
	
	@RequestMapping(value = "moveSignup", method = RequestMethod.GET)
	public String moveSignup(){
		
		return "signup";
	}
	
	
	@RequestMapping(value = "selectOneUser", method = RequestMethod.GET)
	public @ResponseBody String selectOneUser(Users user,HttpSession session){
		Users u = new Users();
		u = uService.selectOneUser(user);
		if(u==null){
			return "no";
		}else{
			return "yes";
		}
	}
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/selectBook";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(Users user, HttpSession session){
		Users u = new Users();
		u = uService.selectOneUser(user);
		if(u==null){
			return "redirect:/selectBook";
			
		}else if(u.getAdmin()==1){
			session.setAttribute("loginId", u.getUserid());
			session.setAttribute("userNum", u.getUsernum());
			session.setAttribute("loginImage", u.getImage());
			session.setAttribute("adminValue", u.getAdmin());
			return "Admin";
			
		}else{
			session.setAttribute("loginId", u.getUserid());
			session.setAttribute("userNum", u.getUsernum());
			session.setAttribute("userName", u.getUsername());
			session.setAttribute("birthDate", u.getBirthdate());
			session.setAttribute("tel1",u.getTel1());
			session.setAttribute("tel2",u.getTel2());
			session.setAttribute("tel3",u.getTel3());
			session.setAttribute("loginImage", u.getImage());
			return "redirect:/selectBook";
		}
		
	}
	
	@RequestMapping(value = "moveAdmin", method = RequestMethod.GET)
	public String moveAdmin(HttpSession session){
		String loginId = (String) session.getAttribute("loginId");
		if(loginId.equals("admin")){
			return "Admin";
		}else{
			return "redirect/selectBook";
		}
		
	}
	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	public String updateUser(Users user, MultipartFile uploadFile){
	
		boolean updateResult;
		
		if(!(uploadFile.isEmpty()||uploadFile==null||uploadFile.getSize()==0)){
			String orgName = uploadFile.getOriginalFilename();
			String savName;
			Date date = new Date();
			savName = date.getTime()+orgName;
			try {
				uploadFile.transferTo(new File(UPLOADPATH+savName));
	
			user.setImage(savName);
			System.out.println(user);
			updateResult = uService.updateUser(user);
				if(!updateResult){
					System.out.println("aaa");
					return "redirect:/moveSignup";
				}else{
					return "redirect:/selectBook";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:/selectBook";
		} else {
			return "redirect:/selectBook";
		}
		
		
	}
	
	
}
