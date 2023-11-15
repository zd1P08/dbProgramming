package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Level;

public class LevelDaoImpl implements LevelDao {

	private DataSource ds;

	public LevelDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Level> findAll() throws Exception {
		List<Level> eventList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String spl = "SELECT * FROM levels";
			PreparedStatement stmt = con.prepareStatement(spl);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				eventList.add(mapToLevel(rs));
			}
		} catch (Exception e) {
			throw e;
		}

		return eventList;
	}

	private Level mapToLevel(ResultSet rs) throws Exception {
		Integer levelId = (Integer) rs.getObject("level_id");
		String levelName = rs.getString("level_name");

		return new Level(levelId, levelName);
	}

}