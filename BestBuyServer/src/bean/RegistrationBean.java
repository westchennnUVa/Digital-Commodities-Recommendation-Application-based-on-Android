package bean;
/**
 * Created by Westchennn on 17/3/18.
 */
public class RegistrationBean {

	private String to;
	private String time;
	private String userName;
	private String IDCard;
	private String phone;
	private String name;
	private String people;
	
	public RegistrationBean(String to, String time, String userName, String IDCard, String phone, String name, String people) {
		this.to = to;
		this.time = time;
		this.userName = userName;
		this.IDCard = IDCard;
		this.phone = phone;
		this.name = name;
		this.people = people;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIDCard() {
		return IDCard;
	}

	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

}
