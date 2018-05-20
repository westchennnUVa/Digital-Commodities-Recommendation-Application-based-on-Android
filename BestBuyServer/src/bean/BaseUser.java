package bean;
/**
 * Created by Westchennn on 17/3/18.
 */
public class BaseUser {
	private String id;
	private String UserName;
	private String password;
	private String name;
	private String sex;
	private String telephone;
	private String IDCard;
	private int age;
	private String head;
	
	public BaseUser(String id, String Username, String password, String name, String sex, String telephone, String IDCard, int age,String bmp){
		this.id = id;
		this.UserName = Username;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.telephone = telephone;
		this.IDCard = IDCard;
		this.age = age;
		this.head = bmp;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUserName() {
		return UserName;
	}
	
	public void setUserName(String UserName) {
		this.UserName = UserName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String pwd) {
		this.password = pwd;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sexy) {
		this.sex = sexy;
	}
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String IDCard) {
		this.IDCard = IDCard;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	//byte[] bmp
	public String getHead()
	{
		return head;
	}
	public void setHead(String bmp)
	{
		this.head=bmp;
	}
}
