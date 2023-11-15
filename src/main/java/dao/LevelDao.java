package dao;

import java.util.List;

import domain.Level;

public interface LevelDao {

	List<Level> findAll() throws Exception;

}
