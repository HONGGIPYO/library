package global.sesoc.libs.repository;

import global.sesoc.libs.dto.Users;

public interface UserMapper {

	boolean signUp(Users user);

	Users selectOneUser(Users user);

	Users selectUserByuserNum(String usernum);

	boolean updateUser(Users user);

}
