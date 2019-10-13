package global.sesoc.libs.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import global.sesoc.libs.dto.Books;
import global.sesoc.libs.dto.Lend;
import global.sesoc.libs.dto.Users;
import global.sesoc.libs.service.BookService;
import global.sesoc.libs.service.LendService;
import global.sesoc.libs.service.UserService;

@Controller
public class LendController {
	
	@Autowired
	LendService lService;
	@Autowired
	BookService bService;
	@Autowired
	UserService uService;
	
	
	@RequestMapping(value = "insertLend", method = RequestMethod.POST)
	public @ResponseBody String insertLend(Lend lend,HttpSession session){
		boolean insertResult;
		Users user = new Users();
		String userNum = (String) session.getAttribute("userNum");
		lend.setUsernum(userNum);
		user = uService.selectUserByuserNum(userNum);
		lend.setUsername(user.getUsername());
		insertResult = lService.insertLend(lend);
		
		
		if(!insertResult){
			return "no";
		
		}else{
			return "yes";
		}
	}
	
	
	@RequestMapping(value = "selectLend", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Lend> selectLend(HttpSession session, int numberTh){
		ArrayList<Lend>lList = new ArrayList<Lend>();
		ArrayList<Lend>bList = new ArrayList<Lend>();
		Books book = new Books();
		Lend lend = new Lend();
		String usernum = (String) session.getAttribute("userNum");
		lend.setUsernum(usernum);
		lList = lService.selectLend(lend);
		for(int i = 0 ; i < lList.size() ; i++){
			book = bService.selectBookByBooknum(lList.get(i).getBooknum());
			lList.get(i).setImageurl(book.getImageurl());
			lList.get(i).setTitle(book.getTitle());
			if (i > numberTh-5) {
				if(i <= numberTh){
					bList.add(lList.get(i));
				}
			}
		}
		return bList;
	}
	
	
	
	@RequestMapping(value = "selectAllLend", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Lend>selectAllLend(){
		ArrayList<Lend>lList = new ArrayList<Lend>();
		Books book = new Books();
		Users user = new Users();
		lList = lService.selectAllLend();
		for(int i = 0 ; i < lList.size() ; i++){
			book = bService.selectBookByBooknum(lList.get(i).getBooknum());
			lList.get(i).setTitle(book.getTitle());
			user = uService.selectUserByuserNum(lList.get(i).getUsernum());
			lList.get(i).setUsername(user.getUsername());
	
		}
		
		for(int i = 0 ; i < lList.size() ; i++){
			String enddate = lList.get(i).getEnddate();

			if(enddate!=null){
				
		
				Calendar calendar = Calendar.getInstance();
	            Date date = calendar.getTime();
	            String today = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
	 
	            try {
	                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                Date beginDate = formatter.parse(enddate);
	                Date endDate = formatter.parse(today);
	                 
	                long diff = endDate.getTime() - beginDate.getTime();
	       
	                long diffDays = diff / (24 * 60 * 60 * 1000);
	          
	   
	            
	    
	                lList.get(i).setDelay((int) diffDays);
	          
	                boolean delayResult;
	                delayResult = lService.updateDelay(lList.get(i));
	                
	           
	                if(!delayResult){
	   
	                }
	                
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
			}
		}
		return lList;
	}
	
	
	@RequestMapping(value = "updateLend", method ={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String updateLend(Lend lend, int actiondate, int booknum){
		boolean updateResult;
		boolean updateBookStatus;
		ArrayList<Lend>lList = new ArrayList<Lend>();
		ArrayList<Lend>checkList = new ArrayList<Lend>();
		lList = lService.checkLend(lend);
		for(int i = 0 ; i < lList.size() ; i++){
			if(lList.get(i).getStatus().equals("lent")||lList.get(i).getStatus().equals("delayed")){
				checkList.add(lList.get(i));
			}
		}
		if(checkList.size()!=0){
			return "no";
		}else{
			
	    Calendar cal = new GregorianCalendar(Locale.KOREA);
	    cal.setTime(new Date());
	    cal.add(Calendar.DAY_OF_YEAR, actiondate);
	
	    SimpleDateFormat fm = new SimpleDateFormat(
	            "yyyy-MM-dd HH:mm:ss");
	    String strDate = fm.format(cal.getTime());
	    
	    lend.setEnddate(strDate);
		
		updateResult = lService.updateLend(lend);
		updateBookStatus = bService.updateBookStatusN(booknum);
		
	
		if(!updateResult){
			return "no";
			
		}else{
			return "yes";
		}
		}
	}
	
	@RequestMapping(value = "updateStatus", method = RequestMethod.POST)
	public @ResponseBody String updateStatus(Lend lend, int booknum){
		boolean updateResult;
		boolean updateBookStatus;
	
		updateResult = lService.updateStatus(lend);
	
		updateBookStatus = bService.updateBookStatusY(booknum);
		
		if(!updateResult){
			return "no";
		}else{
			return "yes";
		}
	}
	
	@RequestMapping(value = "searchBorrowRequest", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Lend> searchBorrowRequest(String selectSearch, String searchText){
		ArrayList<Lend>lList = new ArrayList<Lend>();
		Lend lend = new Lend();
		switch(selectSearch){
		case "책 제목" :
			lend.setTitle(searchText);
			lList = lService.selectLend(lend);
			break;

		case "신청자" :
			lend.setUsername(searchText);
			lList = lService.selectLend(lend);
			break;
		
		}
	
		return lList;
	}
	

	
	
}
