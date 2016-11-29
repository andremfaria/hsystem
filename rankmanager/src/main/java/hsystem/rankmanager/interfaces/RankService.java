package hsystem.rankmanager.interfaces;

import java.util.Iterator;

import hsystem.foundation.beans.Actor;
import hsystem.foundation.beans.Highscore;

public interface RankService {

	public Highscore insertHighscore(Highscore highscore);
	public boolean updateHighscore(Highscore highscore);
	public boolean removeHighscore(String publicId);
	public Highscore getHighscore(String userId);
	public Iterator<Highscore> getAll();
	public Iterator<Highscore> getBetween(int first, int last);

}
