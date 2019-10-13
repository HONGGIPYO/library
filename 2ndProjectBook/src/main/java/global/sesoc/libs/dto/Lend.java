package global.sesoc.libs.dto;

public class Lend extends Books{
	private int lendnum;
	private String usernum;
	private int booknum;
	private String requestdate;
	private String startdate;
	private String enddate;
	private String returndate;
	private String status;
	private String username;
	private int delay;
	public Lend() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Lend(int booknum, String title, String publisher, String author, String status, String content,
			String imageurl, String searchWord, String searchTopic) {
		super(booknum, title, publisher, author, status, content, imageurl, searchWord, searchTopic);
		// TODO Auto-generated constructor stub
	}
	public Lend(int lendnum, String usernum, int booknum, String requestdate, String startdate, String enddate,
			String returndate, String status, String username, int delay) {
		super();
		this.lendnum = lendnum;
		this.usernum = usernum;
		this.booknum = booknum;
		this.requestdate = requestdate;
		this.startdate = startdate;
		this.enddate = enddate;
		this.returndate = returndate;
		this.status = status;
		this.username = username;
		this.delay = delay;
	}
	public int getLendnum() {
		return lendnum;
	}
	public void setLendnum(int lendnum) {
		this.lendnum = lendnum;
	}
	public String getUsernum() {
		return usernum;
	}
	public void setUsernum(String usernum) {
		this.usernum = usernum;
	}
	public int getBooknum() {
		return booknum;
	}
	public void setBooknum(int booknum) {
		this.booknum = booknum;
	}
	public String getRequestdate() {
		return requestdate;
	}
	public void setRequestdate(String requestdate) {
		this.requestdate = requestdate;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getReturndate() {
		return returndate;
	}
	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	@Override
	public String toString() {
		return String.format(
				"Lend [lendnum=%s, usernum=%s, booknum=%s, requestdate=%s, startdate=%s, enddate=%s, returndate=%s, status=%s, username=%s, delay=%s]",
				lendnum, usernum, booknum, requestdate, startdate, enddate, returndate, status, username, delay);
	}
	
	

	
}
