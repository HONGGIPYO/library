package global.sesoc.libs.repository;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import global.sesoc.libs.dto.Lend;

@Repository
public class LendRepository {
	
	@Autowired
	SqlSession session;

	public boolean insertLend(Lend lend) {
		// TODO Auto-generated method stub
		boolean insertResult = false;
		LendMapper mapper = session.getMapper(LendMapper.class);
		try{
		insertResult = mapper.insertLend(lend);
		}catch(Exception e){
			return insertResult;
		}
		return insertResult;
	}

	public ArrayList<Lend> selectLend(Lend lend) {
		// TODO Auto-generated method stub
		ArrayList<Lend>lList = new ArrayList<Lend>();
		
		LendMapper mapper = session.getMapper(LendMapper.class);
		try{
		lList = mapper.selectLend(lend);
		}catch(Exception e){
			e.printStackTrace();
			return lList;
		}
		return lList;
	}

	public ArrayList<Lend> selectAllLend() {
		// TODO Auto-generated method stub
		ArrayList<Lend>lList = new ArrayList<Lend>();
		LendMapper mapper = session.getMapper(LendMapper.class);
		try{
		lList = mapper.selectAllLend();
		}catch(Exception e){
			return lList;
		}
		return lList;
	}

	public boolean updateLend(Lend lend) {
		// TODO Auto-generated method stub
		boolean updateResult=false;
		LendMapper mapper = session.getMapper(LendMapper.class);
		try{
		updateResult = mapper.updateLend(lend);
		}catch(Exception e){
			return updateResult;
		}
		return updateResult;
	}

	public Lend selectOneLend(Lend lend) {
		// TODO Auto-generated method stub
		Lend l = new Lend();
		LendMapper mapper = session.getMapper(LendMapper.class);
		try{
		l = mapper.selectOneLend(lend);
		}catch(Exception e){
			return l;
		}
		return l;
	}

	public boolean updateStatus(Lend lend) {
		// TODO Auto-generated method stub
		boolean updateResult = false;
		
		LendMapper mapper = session.getMapper(LendMapper.class);
		try{
			updateResult = mapper.updateStatus(lend);
		}catch(Exception e){
			return updateResult;
		}
		return updateResult;
	}

	public boolean updateDelay(Lend lend) {
		// TODO Auto-generated method stub
	boolean updateResult = false;
		
		LendMapper mapper = session.getMapper(LendMapper.class);
		try{
			updateResult = mapper.updateDelay(lend);
		}catch(Exception e){
			return updateResult;
		}
		return updateResult;
	}

	public ArrayList<Lend> checkLend(Lend lend) {
		// TODO Auto-generated method stub
		ArrayList<Lend>lList = new ArrayList<Lend>();
		LendMapper mapper = session.getMapper(LendMapper.class);
		try{
			lList = mapper.checkLend(lend);
		}catch(Exception e){
			return lList;
		}
		return lList;
	}


}
