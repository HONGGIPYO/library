package global.sesoc.libs.repository;

import java.util.ArrayList;

import global.sesoc.libs.dto.Lend;

public interface LendMapper {

	boolean insertLend(Lend lend);

	ArrayList<Lend> selectLend(Lend lend);

	ArrayList<Lend> selectAllLend();

	boolean updateLend(Lend lend);

	Lend selectOneLend(Lend lend);

	boolean updateStatus(Lend lend);

	boolean updateDelay(Lend lend);

	ArrayList<Lend> checkLend(Lend lend);

}
