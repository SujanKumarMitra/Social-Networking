package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Post;
import model.User;

public class CRUD implements Properties {
	public static int USER_CREATED = 6;
	public static int USER_NOT_CREATED = -6;
	public static int LOGIN_VERIFIED = 1;
	public static int LOGIN_NOT_VERIFIED = -1;
	public static int POST_UPLOAD_SUCCESS = 2;
	public static int POST_UPLOAD_FAIL = -2;
	public static int UPDATE_SUCCESS = 3;
	public static int UPDATE_FAILED = -3;
	public static int DELETE_SUCCESS = 4;
	public static int DELETE_FAILED = -4;
	public static int LIKE_DONE = 5;
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
	public static String getImageSource(int id)
	{
		try {
			con = Properties.getConnection();
			stmt = con.prepareStatement("SELECT ImageSource from users WHERE id= ?");
			stmt.setInt(1, id);
//			stmt.setString(2, Hashing.getMd5(password));
			rs = stmt.executeQuery();
			if(rs.next())
			{
				return rs.getString(1);
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
				post.setImageSource(rs.getString("ImageSource"));
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

	public static int postUpload(String heading, String content, int id,String path) {
		try {
			con = Properties.getConnection();
			stmt = con.prepareStatement("INSERT into posts(Name,user_id,Content,ImageSource) VALUES(?,?,?,?)");
			stmt.setString(1, heading);
			stmt.setString(3, content);
			stmt.setInt(2, id);
			stmt.setString(4, path);
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

	public static ArrayList<Post> fetchRecentPosts(int id)
	{
		ArrayList<Post> posts = null;
		Post post = null;
		try {
			posts = new ArrayList<>();
			con = Properties.getConnection();
			stmt = con.prepareStatement("SELECT * FROM posts WHERE user_id=? ORDER BY id DESC LIMIT 3");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				post = new Post();
				post.setId(rs.getInt("id"));
				post.setName(rs.getString("Name"));
				post.setUserId(rs.getInt("user_id"));
				post.setContent(rs.getString("Content"));
				posts.add(post);
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
		return posts;
		
	}

	public static int updateUser(String userName, String email,int id) {
		try {
			con = Properties.getConnection();
			stmt = con.prepareStatement("UPDATE users SET Name = ?,Email = ? WHERE id = ?");
			stmt.setString(1, userName);
			stmt.setString(2, email);
			stmt.setInt(3, id);
			int res = stmt.executeUpdate();
			if(res>0)
			{
				return UPDATE_SUCCESS;
			}
			
		} catch (Exception e) {
			
		}
		finally {
			try {
				con.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return UPDATE_FAILED;
	}

	public static int updatePassword(int id, String password) {
		try {
			con = Properties.getConnection();
			stmt = con.prepareStatement("UPDATE users SET Password = ? WHERE id = ?");
			stmt.setString(1, Hashing.getMd5(password));
			stmt.setInt(2, id);
			int res = stmt.executeUpdate();
			if(res>0)
			{
				return UPDATE_SUCCESS;
			}
			
		} catch (Exception e) {
			
		}
		finally {
			try {
				con.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return UPDATE_FAILED;
	}

	public static int deleteUser(User user) {
		try {
			con = Properties.getConnection();
			stmt = con.prepareStatement("DELETE FROM users WHERE id = ?");
			stmt.setInt(1, user.getId());
			stmt.executeUpdate();
			stmt = con.prepareStatement("DELETE FROM posts WHERE user_id = ?");
			stmt.setInt(1, user.getId());
			stmt.executeUpdate();
			return DELETE_SUCCESS;
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
		return DELETE_FAILED;
	}

	public static int like(User user, int postId) {
		try {
			con = Properties.getConnection();
			stmt = con.prepareStatement("SELECT * from likes WHERE user_id = ? and post_id = ?");
			stmt.setInt(1,user.getId());
			stmt.setInt(2, postId);
			rs = stmt.executeQuery();
			if(rs.next())
			{
				stmt = con.prepareStatement("DELETE FROM likes WHERE user_id = ? and post_id = ?");
				stmt.setInt(1,user.getId());
				stmt.setInt(2, postId);
				int res = stmt.executeUpdate();
				if(res>0)
				{
					return LIKE_DONE;
				}
			}
			else
			{
				stmt = con.prepareStatement("INSERT into likes(user_id,post_id) VALUES(?,?)");
				stmt.setInt(1,user.getId());
				stmt.setInt(2, postId);
				int res = stmt.executeUpdate();
				if(res>0)
				{
					return LIKE_DONE;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DELETE_FAILED;
	}
	public static ArrayList<String> getLikedUsers(int id)
	{
		ArrayList<String> names = null;
		try {
			names = new ArrayList<>();
			con = Properties.getConnection();
			stmt = con.prepareStatement("SELECT user_id from likes WHERE post_id = ?");
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				PreparedStatement tmp = con.prepareStatement("SELECT Name from users WHERE id = ?");
				tmp.setInt(1, rs.getInt("user_id"));
				ResultSet res = tmp.executeQuery();
				if(res.next())
				{
					names.add(res.getString("Name"));
				}
				res.close();
				tmp.close();
			}
			return names;
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
		return names;
		
	}
	public static HashMap<String, String> getComments(int id)
	{
		HashMap<String, String> map = null;
		try {
			map = new HashMap<>();
			con = Properties.getConnection();
			stmt = con.prepareStatement("SELECT * from comments WHERE post_id = ?");
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				PreparedStatement tmp = con.prepareStatement("SELECT Name from users WHERE id = ?");
				tmp.setInt(1, rs.getInt("user_id"));
				ResultSet res = tmp.executeQuery();
				if(res.next())
				{
					map.put(res.getString("Name"),rs.getString("Content"));
				}
				res.close();
				tmp.close();
			}
			return map;
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
		return map;
		
	}

	public static int insertComment(int userId, int postId, String comment) {
		try {
			con = Properties.getConnection();
			stmt = con.prepareStatement("INSERT into comments(Content,user_id,post_id)VALUES(?,?,?)");
			stmt.setString(1, comment);
			stmt.setInt(2, userId);
			stmt.setInt(3, postId);
			int res = stmt.executeUpdate();
			if(res>0)
			{
				return UPDATE_SUCCESS;
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
		return UPDATE_FAILED;
	}

	public static int createUser(String name, String email, String pass, String path) {
		try {
			con = Properties.getConnection();
			stmt = con.prepareStatement("INSERT INTO users(Name,Email,Password,ImageSource)VALUES(?,?,?,?)");
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, Hashing.getMd5(pass));
			stmt.setString(4, path);
			int res = stmt.executeUpdate();
			if(res>0)
			{
				return USER_CREATED;
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
		return USER_NOT_CREATED;
		
		
	}
	public static User checkUser(String email) {
		User user = null;
		try {
			Connection con = Properties.getConnection();
			stmt = con.prepareStatement("SELECT * from users WHERE Email = ?");
			stmt.setString(1, email);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("Email"));
				user.setName(rs.getString("Name"));
				user.setImageSource(rs.getString("ImageSource"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}
