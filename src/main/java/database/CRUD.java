package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.Post;
import controller.User;

public class CRUD implements Properties {
	public static int LOGIN_VERIFIED = 1;
	public static int LOGIN_NOT_VERIFIED = -1;
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
				user.setEmail("Email");
				user.setName(rs.getString("Name"));
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
				post.setLike(rs.getInt("like"));
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
}
