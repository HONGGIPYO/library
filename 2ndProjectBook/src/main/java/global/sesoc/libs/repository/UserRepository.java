package global.sesoc.libs.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import global.sesoc.libs.dto.Users;

@Repository
public class UserRepository {
	
	@Autowired
	SqlSession session;

	public boolean signUp(Users user) {
		// TODO Auto-generated method stub
		boolean signupResult = false;
		
		UserMapper mapper = session.getMapper(UserMapper.class);
		try{
		signupResult = mapper.signUp(user);
		}catch(Exception e){
			return signupResult;
		}
		return signupResult;
	}

	public Users selectOneUser(Users user) {
		// TODO Auto-generated method stub
		Users u = new Users();
		UserMapper mapper = session.getMapper(UserMapper.class);
		try{
		u = mapper.selectOneUser(user);
		}catch(Exception e){
			return u;
		}
		return u;
	}

	public Users selectUserByuserNum(String usernum) {
		// TODO Auto-generated method stub
		Users u = new Users();
		UserMapper mapper = session.getMapper(UserMapper.class);
		try{
		u = mapper.selectUserByuserNum(usernum);
		}catch(Exception e){
			return u;
		}
		return u;
	}

	public boolean updateUser(Users user) {
		// TODO Auto-generated method stub
		boolean updateUserResult = false;
		UserMapper mapper = session.getMapper(UserMapper.class);
		try{
			updateUserResult = mapper.updateUser(user);
		}catch(Exception e){
			e.printStackTrace();
			return updateUserResult;
		}
		return updateUserResult;
		
	}

}
