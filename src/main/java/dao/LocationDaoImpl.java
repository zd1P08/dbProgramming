package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Location;

public class LocationDaoImpl implements LocationDao {

	private DataSource ds;

	public LocationDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Location> findAll() throws Exception {
		List<Location> locationList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String spl = "SELECT * FROM locations";
			PreparedStatement stmt = con.prepareStatement(spl);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				locationList.add(mapToLocation(rs));
			}
		} catch (Exception e) {
			throw e;
		}

		return locationList;
	}

	private Location mapToLocation(ResultSet rs) throws Exception {
		Integer locationId = (Integer) rs.getObject("location_id");
		String locationName = rs.getString("location_name");

		return new Location(locationId, locationName);
	}

}