package hsystem.rankmanager.impl;

import java.util.Iterator;

import hsystem.db.impl.DbService;
import hsystem.foundation.SpringApplicationContext;
import hsystem.foundation.beans.Highscore;
import hsystem.rankmanager.interfaces.RankService;

public class RankServiceImpl implements RankService {

	private DbService dbService;

	public Highscore insertHighscore(Highscore highscore) {

		dbService = (DbService) SpringApplicationContext.getBean("dbService");
		return dbService.insertHighscore(highscore) ? highscore : null;
	}

	public boolean updateHighscore(Highscore highscore) {

		dbService = (DbService) SpringApplicationContext.getBean("dbService");
		return dbService.updateHighscore(highscore) ? true : false;
	}

	public boolean removeHighscore(String publicId) {
		dbService = (DbService) SpringApplicationContext.getBean("dbService");
		return dbService.removeHighscore(publicId);

	}

	public Highscore getHighscore(String userId) {
		dbService = (DbService) SpringApplicationContext.getBean("dbService");
		return dbService.getHighscore(userId);
	}

	public Iterator<Highscore> getAll() {
		dbService = (DbService) SpringApplicationContext.getBean("dbService");
		return dbService.getAll().iterator();
	}

	public Iterator<Highscore> getBetween(int first, int last) {
		dbService = (DbService) SpringApplicationContext.getBean("dbService");
		return dbService.getSliceOrdered(first, last).iterator();
	}

}
