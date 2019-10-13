package global.sesoc.libs.dto;

public class Users {
	private String usernum;
	private String userid;
	private String password;
	private String birthdate;
	private String username;
	private String tel1;
	private int tel2;
	private int tel3;
	private String image;
	private int admin;
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(String usernum, String userid, String password, String birthdate, String username, String tel1,
			int tel2, int tel3, String image, int admin) {
		super();
		this.usernum = usernum;
		this.userid = userid;
		this.password = password;
		this.birthdate = birthdate;
		this.username = username;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.tel3 = tel3;
		this.image = image;
		this.admin = admin;
	}
	public String getUsernum() {
		return usernum;
	}
	public void setUsernum(String usernum) {
		this.usernum = usernum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public int getTel2() {
		return tel2;
	}
	public void setTel2(int tel2) {
		this.tel2 = tel2;
	}
	public int getTel3() {
		return tel3;
	}
	public void setTel3(int tel3) {
		this.tel3 = tel3;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	@Override
	public String toString() {
		return String.format(
				"Users [usernum=%s, userid=%s, password=%s, birthdate=%s, username=%s, tel1=%s, tel2=%s, tel3=%s, image=%s, admin=%s]",
				usernum, userid, password, birthdate, username, tel1, tel2, tel3, image, admin);
	}
	
	

}
