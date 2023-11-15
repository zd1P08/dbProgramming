package domain;

import java.util.Date;

public class Event {

	private Integer eventId;
	private Integer userId;
	private Date eventStartDate;
	private Date eventEndDate;
	private Integer locationId;
	private Integer amount;
	private Integer capacity;
	private Integer participantsNumber;
	private Integer levelId;
	private String content;
	private String levelName;
	private String userName;
	private String locationName;

	public Event() {

	}

	public Event(Integer eventId, Integer userId, Date eventStartDate, Date eventEndDate, Integer locationId, Integer amount, Integer capacity,
			Integer participantsNumber, Integer levelId, String content, String levelName, String userName, String locationName) {
		this.eventId = eventId;
		this.userId = userId;
		this.eventStartDate = eventStartDate;
		this.eventEndDate = eventEndDate;
		this.locationId = locationId;
		this.amount = amount;
		this.capacity = capacity;
		this.participantsNumber = participantsNumber;
		this.levelId = levelId;
		this.content = content;
		this.levelName = levelName;
		this.userName = userName;
		this.locationName = locationName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Date getEventStartDate() {
		return eventStartDate;
	}

	public void setEventStartDate(Date eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	public Date getEventEndDate() {
		return eventEndDate;
	}

	public void setEventEndDate(Date eventEndDate) {
		this.eventEndDate = eventEndDate;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getParticipantsNumber() {
		return participantsNumber;
	}

	public void setParticipantsNumber(Integer participantsNumber) {
		this.participantsNumber = participantsNumber;
	}

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
