package global.sesoc.libs.repository;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;

import global.sesoc.libs.dto.Books;

public interface BookMapper {

	ArrayList<Books> selectBook(Books book, RowBounds rb);

	Books selectOneBook(String booknum);

	Books selectBookByBooknum(int booknum);
	
	int selectCount(Books book);

	boolean updateBookStatusN(int booknum);

	boolean updateBookStatusY(int booknum);

	boolean insertNewBook(Books book);

}
