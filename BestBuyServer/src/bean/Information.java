package bean;
/**
 * Created by Westchennn on 17/3/18.
 */
public class Information {
	
	private String id;
	private String head;
	private String name;
	private String time;
	private String type;
	private String content;
	private int prizeNum;
	private int commentNum;
	
	public Information(String id, String head, String name, String time, String type, String content, int prizeNum, int commentNum){
		this.id = id;
		this.head = head;
		this.name = name;
		this.time = time;
		this.type = type;
		this.content = content;
		this.prizeNum = prizeNum;
		this.commentNum = commentNum;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPrizeNum() {
		return prizeNum;
	}
	public void setPrizeNum(int prizeNum) {
		this.prizeNum = prizeNum;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	
	

}
