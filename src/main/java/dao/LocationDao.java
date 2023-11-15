package dao;

import java.util.List;

import domain.Location;

public interface LocationDao {

	List<Location> findAll() throws Exception;

}
