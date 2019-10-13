package global.sesoc.libs.dto;

public class Books {
	private int booknum;
	private String title;
	private String publisher;
	private String author;
	private String status;
	private String content;
	private String imageurl;
	private String searchWord;
	private String searchTopic;
	public Books() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Books(int booknum, String title, String publisher, String author, String status, String content,
			String imageurl, String searchWord, String searchTopic) {
		super();
		this.booknum = booknum;
		this.title = title;
		this.publisher = publisher;
		this.author = author;
		this.status = status;
		this.content = content;
		this.imageurl = imageurl;
		this.searchWord = searchWord;
		this.searchTopic = searchTopic;
	}
	public int getBooknum() {
		return booknum;
	}
	public void setBooknum(int booknum) {
		this.booknum = booknum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getSearchTopic() {
		return searchTopic;
	}
	public void setSearchTopic(String searchTopic) {
		this.searchTopic = searchTopic;
	}
	@Override
	public String toString() {
		return String.format(
				"Books [booknum=%s, title=%s, publisher=%s, author=%s, status=%s, content=%s, imageurl=%s, searchWord=%s, searchTopic=%s]",
				booknum, title, publisher, author, status, content, imageurl, searchWord, searchTopic);
	}
	
	

}
