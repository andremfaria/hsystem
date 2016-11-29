package hsystem.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import hsystem.foundation.SpringApplicationContext;
import hsystem.foundation.beans.Actor;
import hsystem.foundation.beans.Highscore;
import hsystem.foundation.security.Security;

public class DbService {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

	public DataSource getDataSource() {
		return this.dataSource;
	}

	// ACTORS
	public Actor getActor(String publicId) {

		initDb();
		Actor act = null;
		try {
			act = this.jdbcTemplate.queryForObject("select * from ACTOR where PUBLIC_ID = ?",

					new Object[] { publicId }, new RowMapper<Actor>() {
						public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
							Actor actor = new Actor();
							actor.setPublic_id(rs.getString("PUBLIC_ID"));
							actor.setCryptedPassword(rs.getString("PASSWORD"));
							actor.setNationality(rs.getString("NATIONALITY"));
							actor.setCreationDate(rs.getDate("CREATION_DATE"));
							actor.setIsDeleted(rs.getBoolean("IS_DELETED"));
							return actor;
						}
					});
		} catch (Exception e) {
			return null;
		}

		return act;
	}

	public boolean insertActor(Actor act) {
		int res = 0; 
		try {
			initDb();
			res = this.jdbcTemplate.update("INSERT INTO  HIGHSCORES.ACTOR VALUES (?,?,?, NOW(),FALSE)", act.getPublic_id(),
					act.getCryptedPassword(), act.getNationality());
		} catch (Exception e) {
			return false;
		}

		return res > 0? true:false;
	}

	public boolean removeActor(String publicId) {
		int res = 0;
		try {
			initDb();
			res = this.jdbcTemplate.update("UPDATE ACTOR SET IS_DELETED = 1 WHERE PUBLIC_ID=?", publicId);

		} catch (Exception e) {
			return false;
		}

		return res > 0 ? true : false;
	}

	public boolean doLogin(String publicId, String password) {
		Actor act = getActor(publicId);

		if (act != null && act.getPublic_id().isEmpty() == false)
			return act.getCryptedPassword().equals(password);

		return false;
	}

	public boolean updateActor(Actor act) {
		int res = 0;
		try {
			initDb();
			res = this.jdbcTemplate.update(
					"UPDATE HIGHSCORES.ACTOR SET PASSWORD=?,NATIONALITY = ?, IS_DELETED = ? WHERE PUBLIC_ID=?",
					act.getCryptedPassword(), act.getNationality(), act.getIsDeleted(), act.getPublic_id());
		} catch (Exception e) {
			return false;
		}

		return res > 0 ? true : false;
	}

	// HIGHSCORE

	public Highscore getHighscore(String publicId) {

		Highscore high = null;

		try {

			initDb();
			high = this.jdbcTemplate.queryForObject("select * from RANK where ACTOR_ID = ?", new Object[] { publicId },
					new RowMapper<Highscore>() {
						public Highscore mapRow(ResultSet rs, int rowNum) throws SQLException {
							Highscore high = new Highscore();
							high.setActorId(rs.getString("actor_id"));
							high.setCreationDate(rs.getDate("creation_date"));
							high.setIdentifier(rs.getLong("identifier"));
							high.setPoints(rs.getLong("points"));
							return high;
						}
					});
		} catch (Exception e) {
			return null;
		}
		return high;
	}

	public boolean insertHighscore(Highscore high) {
		int res = 0;
		try {
			initDb();
			if (getHighscore(high.getActorId()) != null) {
				return updateHighscore(high);
			} else {
				res = this.jdbcTemplate.update("INSERT HIGHSCORES.RANK (POINTS, CREATION_DATE, ACTOR_ID) VALUES (?,now(),?);",
						high.getPoints(), high.getActorId());
			}
		} catch (Exception e) {
			return false;
		}

		return res > 0? true:false;
	}

	public boolean updateHighscore(Highscore high) {
		int res = 0;
		try {
			initDb();
			res = this.jdbcTemplate.update("update highscores.rank set points = ? where actor_id = ?;", high.getPoints(),
					high.getActorId());
		} catch (Exception e) {
			return false;
		}

		return res > 0? true:false;
	}

	public boolean removeHighscore(String actor_id) {
		int res = 0;
		try {
			initDb();
			res = this.jdbcTemplate.update("delete from highscores.rank where actor_id = ?;", actor_id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

		return res > 0? true:false;
	}

	// Highscores logic
	public List<Highscore> getAllOrdered() {
		initDb();
		List<Highscore> res = this.jdbcTemplate.query("select * from RANK ORDER BY POINTS DESC",
				new RowMapper<Highscore>() {
					public Highscore mapRow(ResultSet rs, int rowNum) throws SQLException {
						Highscore high = new Highscore();
						high.setActorId(rs.getString("actor_id"));
						high.setCreationDate(rs.getDate("creation_date"));
						high.setIdentifier(rs.getLong("identifier"));
						high.setPoints(rs.getLong("points"));
						return high;
					}
				});

		return res;
	}

	public List<Highscore> getSliceOrdered(int downLimit, int upLimit) {
		initDb();
		List<Highscore> res = this.jdbcTemplate.query("select * from RANK ORDER BY POINTS DESC limit ?,?",
				new Object[] { downLimit, upLimit }, new RowMapper<Highscore>() {
					public Highscore mapRow(ResultSet rs, int rowNum) throws SQLException {
						Highscore high = new Highscore();
						high.setActorId(rs.getString("actor_id"));
						high.setCreationDate(rs.getDate("creation_date"));
						high.setIdentifier(rs.getLong("identifier"));
						high.setPoints(rs.getLong("points"));
						return high;
					}
				});

		return res;
	}

	public List<Highscore> getAll() {
		initDb();
		List<Highscore> res = this.jdbcTemplate.query("select * from RANK order by points desc", new RowMapper<Highscore>() {
			public Highscore mapRow(ResultSet rs, int rowNum) throws SQLException {
				Highscore high = new Highscore();
				high.setActorId(rs.getString("actor_id"));
				high.setCreationDate(rs.getDate("creation_date"));
				high.setIdentifier(rs.getLong("identifier"));
				high.setPoints(rs.getLong("points"));
				return high;
			}
		});

		return res;
	}

	private void initDb() {
		this.dataSource = (DataSource) SpringApplicationContext.getBean("dataSource");
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
