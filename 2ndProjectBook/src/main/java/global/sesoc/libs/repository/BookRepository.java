package global.sesoc.libs.repository;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import global.sesoc.libs.dto.Books;
import global.sesoc.libs.util.PageNavigator;

@Repository
public class BookRepository {
	
	@Autowired
	SqlSession session;

	public ArrayList<Books> selectBook(Books book, PageNavigator navi) {
		// TODO Auto-generated method stub
		ArrayList<Books>bList = new ArrayList<Books>();
		BookMapper mapper = session.getMapper(BookMapper.class);
		try{
			
			RowBounds rb = new RowBounds(navi.getStartRecord(),navi.getCountPerPage());
			
			
		
			bList = mapper.selectBook(book, rb);
			
			
		}catch(Exception e){
			return bList;
		}
		return bList;
	}

	public Books selectOneBook(String booknum) {
		// TODO Auto-generated method stub
		Books book = new Books();
		BookMapper mapper = session.getMapper(BookMapper.class);
		try{
		book = mapper.selectOneBook(booknum);
		}catch(Exception e){
			return book;
		}
		return book;
	}

	public Books selectBookByBooknum(int booknum) {
		// TODO Auto-generated method stub
		Books book = new Books();
		BookMapper mapper = session.getMapper(BookMapper.class);
		try{
		book = mapper.selectBookByBooknum(booknum);
		}catch(Exception e){
			return book;
		}
		return book;
	}

	public int selectCount(Books book) {
		// TODO Auto-generated method stub
		int result = 0;
		
		BookMapper mapper = session.getMapper(BookMapper.class);
		try{
		result = mapper.selectCount(book);
		}catch(Exception e){
			return result;
		}
		return result;
	}

	public boolean updateBookStatusN(int booknum) {
		// TODO Auto-generated method stub
		boolean updateBookResult = false;
		
		BookMapper mapper = session.getMapper(BookMapper.class);
		try{
		updateBookResult = mapper.updateBookStatusN(booknum);
		}catch(Exception e){
			return updateBookResult;
			
		}
		return updateBookResult;
	}

	public boolean updateBookStatusY(int booknum) {
		// TODO Auto-generated method stub
	boolean updateBookResult = false;
		
		BookMapper mapper = session.getMapper(BookMapper.class);
		try{
		updateBookResult = mapper.updateBookStatusY(booknum);
		}catch(Exception e){
			return updateBookResult;
			
		}
		return updateBookResult;
	}

	public boolean insertNewBook(Books book) {
		// TODO Auto-generated method stub
	boolean insertNewBookResult = false;
		
		BookMapper mapper = session.getMapper(BookMapper.class);
		try{
			insertNewBookResult = mapper.insertNewBook(book);
		}catch(Exception e){
			return insertNewBookResult;
			
		}
		return insertNewBookResult;
	}

}
