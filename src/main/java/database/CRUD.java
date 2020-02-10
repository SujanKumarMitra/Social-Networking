package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import controller.Post;
import controller.User;

public class CRUD implements Properties {
	public static int LOGIN_VERIFIED = 1;
	public static int LOGIN_NOT_VERIFIED = -1;
	public static int POST_UPLOAD_SUCCESS = 2;
	public static int POST_UPLOAD_FAIL = -2;
	static Connection con=null;
	static PreparedStatement stmt = null;
	static ResultSet rs = null;
	public static User verifyExecutiveLogin(String email, String password){
		try {
			con = Properties.getConnection();
			stmt = con.prepareStatement("SELECT * from users WHERE Email = ? AND Password = ?");
			stmt.setString(1, email);
			stmt.setString(2, Hashing.getMd5(password));
			rs = stmt.executeQuery();
			if(rs.next())
			{
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("Email"));
				user.setName(rs.getString("Name"));
				user.setPassword(rs.getString("Password"));
				user.setImageSource(rs.getString("ImageSource"));
				return user;
			}
			
		} catch (Exception e) {
			
		}
		finally {
			try {
				con.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static ArrayList<Post> fetchPosts()
	{
		ArrayList<Post> posts = new ArrayList<Post>();
		Post post = null;
		try {
			con = Properties.getConnection();
			stmt = con.prepareStatement("SELECT * FROM posts ORDER BY id DESC");
			rs = stmt.executeQuery();
			while(rs.next())
			{
				post = new Post();
				post.setId(rs.getInt("id"));
				post.setName(rs.getString("Name"));
				post.setContent(rs.getString("Content"));
				post.setUserId(rs.getInt("user_id"));
				posts.add(post);
			}
			
		} catch (Exception e) {
			
		}
		finally {
			try {
				con.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return posts;
		
	}

	public static String getUserNameById(int id)
	{
		try {
			con = Properties.getConnection();
			stmt = con.prepareStatement("SELECT Name from users WHERE id = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next())
			{
				return rs.getString("Name");
			}
			
		} catch (Exception e) {
			
		}
		finally {
			try {
				con.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}

	public static int postUpload(String heading, String content, int id) {
		try {
			con = Properties.getConnection();
			stmt = con.prepareStatement("INSERT into posts(Name,user_id,Content) VALUES(?,?,?)");
			stmt.setString(1, heading);
			stmt.setString(3, content);
			stmt.setInt(2, id);
			int res = stmt.executeUpdate();
			if(res>0)
			{
				return POST_UPLOAD_SUCCESS;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return POST_UPLOAD_FAIL;
	}
	public static int getLikeFromPost(int id)
	{
		try {
			con = Properties.getConnection();
			stmt = con.prepareStatement("SELECT COUNT(*) from likes WHERE post_id=?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next())
			{
				return rs.getInt("COUNT(*)");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return 0;
		
	}
	public static ArrayList<User> getFriends()
	{
		ArrayList<User> users = null;
		User user = null;
		try {
			users = new ArrayList<>();
			con = Properties.getConnection();
			stmt = con.prepareStatement("SELECT * from users");
			rs = stmt.executeQuery();
			while(rs.next())
			{
				user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("Email"));
				user.setName(rs.getString("Name"));
				user.setImageSource(rs.getString("ImageSource"));
				users.add(user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return users;
		
	}

	public static int insertRequest(int id, String cause, int amount) {
		try {
			con = Properties.getConnection();
			stmt = con.prepareStatement("INSERT into requests(Cause,user_id,Amount) VALUES(?,?,?)");
			stmt.setString(1, cause);
			stmt.setInt(2, id);
			stmt.setInt(3, amount);
			int res = stmt.executeUpdate();
			if(res>0)
			{
				return POST_UPLOAD_SUCCESS;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return POST_UPLOAD_FAIL;
	}
	public static HashMap<String, Integer> getRequests(int id)
	{
		HashMap<String, Integer> result = null;
		try {
			result = new HashMap<>();
			con = Properties.getConnection();
			stmt = con.prepareStatement("SELECT * from requests WHERE user_id = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				result.put(rs.getString("Cause"), rs.getInt("Amount"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		
	}
}
