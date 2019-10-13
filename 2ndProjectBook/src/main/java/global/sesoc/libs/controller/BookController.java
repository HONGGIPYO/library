package global.sesoc.libs.controller;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import global.sesoc.libs.dto.Books;
import global.sesoc.libs.service.BookService;
import global.sesoc.libs.util.PageNavigator;

@Controller
public class BookController {
	
	@Autowired
	BookService bService;
	
	final static String UPLOADPATH="C:/SPRINGHOME/2ndProjectBook/src/main/webapp/resources/saveimg/";
	
	static int COUNTPERPAGE = 5;
	
	
	
	@RequestMapping(value = "selectBook", method = RequestMethod.GET)
	public String selectBook (@RequestParam(value="page",defaultValue="1")int page,Model m, Books book){
		
		int PAGEPERGROUP = 5;
		
		PageNavigator navi = new PageNavigator(COUNTPERPAGE,PAGEPERGROUP,page,bService.selectCount(book));
		
		m.addAttribute("navi",navi);
		m.addAttribute("bList",bService.selectBook(book, navi));
		m.addAttribute("searchWord",book.getSearchWord());
		m.addAttribute("searchTopic",book.getSearchTopic());
		return "home";
		
	}
	
	
	@RequestMapping(value = "selectOneBook", method = RequestMethod.GET)
	public @ResponseBody Books selectOneBook(String booknum){
		Books book = new Books();
		book = bService.selectOneBook(booknum);
		return book;
		
		
	}
	@RequestMapping(value = "selectPaging", method = RequestMethod.GET)
	public @ResponseBody String selectPaging(int selectPaging){
		
		COUNTPERPAGE = selectPaging;
		
		return "yes";
	
		
	}
	
	@RequestMapping(value = "insertNewBook", method = RequestMethod.POST)
	public String insertNewBook(Books book, MultipartFile uploadFile){
		boolean insertResult;
		
		if(!(uploadFile.isEmpty()||uploadFile==null||uploadFile.getSize()==0)){
			String orgName = uploadFile.getOriginalFilename();
			String savName;
			Date date = new Date();
			savName = date.getTime()+orgName;
			try {
				uploadFile.transferTo(new File(UPLOADPATH+savName));
	
			book.setImageurl(savName);
				insertResult = bService.insertNewBook(book);
				if(!insertResult){
					return "Admin";
				}else{
					return "Admin";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "Admin";
		} else {
			return "Admin";
		}
	}
	

}
