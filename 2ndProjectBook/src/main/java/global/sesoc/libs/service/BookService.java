package global.sesoc.libs.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import global.sesoc.libs.dto.Books;
import global.sesoc.libs.repository.BookRepository;
import global.sesoc.libs.util.PageNavigator;

@Service
public class BookService {
	
	@Autowired
	BookRepository bRepo;

	public ArrayList<Books> selectBook(Books book, PageNavigator navi) {
		// TODO Auto-generated method stub
		return bRepo.selectBook(book, navi);
	}

	public Books selectOneBook(String booknum) {
		// TODO Auto-generated method stub
		return bRepo.selectOneBook(booknum);
	}

	public Books selectBookByBooknum(int booknum) {
		// TODO Auto-generated method stub
		return bRepo.selectBookByBooknum(booknum);
	}
	
	public int selectCount(Books book){
		return bRepo.selectCount(book);
	}

	public boolean updateBookStatusN(int booknum) {
		// TODO Auto-generated method stub
		return bRepo.updateBookStatusN(booknum);
	}

	public boolean updateBookStatusY(int booknum) {
		// TODO Auto-generated method stub
		return bRepo.updateBookStatusY(booknum);
	}

	public boolean insertNewBook(Books book) {
		// TODO Auto-generated method stub
		return bRepo.insertNewBook(book);
	}

}
