package global.sesoc.libs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import global.sesoc.libs.dto.Users;
import global.sesoc.libs.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository uRepo;

	public boolean signUp(Users user) {
		// TODO Auto-generated method stub
		return uRepo.signUp(user);
	}

	public Users selectOneUser(Users user) {
		// TODO Auto-generated method stub
		return uRepo.selectOneUser(user);
	}

	public Users selectUserByuserNum(String usernum) {
		// TODO Auto-generated method stub
		return uRepo.selectUserByuserNum(usernum);
	}

	public boolean updateUser(Users user) {
		// TODO Auto-generated method stub
		return uRepo.updateUser(user);
	}

}
