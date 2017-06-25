
public class Result {
	private int id;
	private String title;
	private String link;
	
	public Result(){}
	public void setId(int id){
		this.id = id;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setLink(String link){
		this.link = link;
	}
	public int getId(){
		return this.id;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getLink(){
		return this.link;
	}
}
