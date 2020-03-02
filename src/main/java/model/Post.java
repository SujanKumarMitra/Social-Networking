package model;

public class Post {
	private int id;
	private String name;
	private int userId;
	private String content;
	private String ImageSource;
	public Post()
	{
		super();
	}
	public String getImageSource() {
		return ImageSource;
	}
	public void setImageSource(String imageSource) {
		ImageSource = imageSource;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
