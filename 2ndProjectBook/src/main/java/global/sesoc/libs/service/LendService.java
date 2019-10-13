package global.sesoc.libs.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import global.sesoc.libs.dto.Lend;
import global.sesoc.libs.repository.LendRepository;

@Service
public class LendService {
	
	@Autowired
	LendRepository lRepo;

	public boolean insertLend(Lend lend) {
		// TODO Auto-generated method stub
		return lRepo.insertLend(lend);
	}

	public ArrayList<Lend> selectLend(Lend lend) {
		// TODO Auto-generated method stub
		return lRepo.selectLend(lend);
	}

	public ArrayList<Lend> selectAllLend() {
		// TODO Auto-generated method stub
		return lRepo.selectAllLend();
	}

	public boolean updateLend(Lend lend) {
		// TODO Auto-generated method stub
		return lRepo.updateLend(lend);
	}

	public Lend selectOneLend(Lend lend) {
		// TODO Auto-generated method stub
		return lRepo.selectOneLend(lend);
	}

	public boolean updateStatus(Lend lend) {
		// TODO Auto-generated method stub
		return lRepo.updateStatus(lend);
	}

	public boolean updateDelay(Lend lend) {
		// TODO Auto-generated method stub
		return lRepo.updateDelay(lend);
		
	}

	public ArrayList<Lend> checkLend(Lend lend) {
		// TODO Auto-generated method stub
		return lRepo.checkLend(lend);
	}


}
