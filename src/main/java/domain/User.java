package domain;

public class User {
	
	private Integer userId;
	private String loginId;
	private String loginPass;
	private String userName;
	private Integer levelId;
	private Integer adminDiv;
	private String levelName;
	
	public User() {

	}

	public User(Integer userId, String loginId, String loginPass, String userName,
			Integer levelId, Integer adminDiv, String levelName) {
		this.userId = userId;
		this.loginId = loginId;
		this.loginPass = loginPass;
		this.userName = userName;
		this.levelId = levelId;
		this.adminDiv = adminDiv;
		this.levelName = levelName;
	}
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginPass() {
		return loginPass;
	}
	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getLevelId() {
		return levelId;
	}
	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}
	public Integer getAdminDiv() {
		return adminDiv;
	}
	public void setAdminDiv(Integer adminDiv) {
		this.adminDiv = adminDiv;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
//	@Override
//	public String toString() {
//		return "User [userId=" + userId + ", loginId=" + loginId + ", loginPass=" + loginPass + ", userName=" + userName
//				+ ", levelId=" + levelId + ", adminDiv=" + adminDiv + "]";
//	}
	
	

}
