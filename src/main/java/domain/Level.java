package domain;

public class Level {

	private Integer levelId;
	private String levelName;

	public Level(Integer levelId, String levelName) {
		this.levelId = levelId;
		this.levelName = levelName;
	}

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

}
