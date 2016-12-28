package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class URLManager implements AddOnlyColletion {
	
	public static URLManager getInstance() {
		if (urlManager == null)
			urlManager = new URLManager();
		return urlManager;
	}
	
	private URLManager() {
		// TODO Auto-generated constructor stub
		innerURLSet = new HashSet<>();
	}

	@Override
	public boolean add(Object object) {
		// TODO Auto-generated method stub
		//增加一个URL进入缓存
		//	返回值
		//		true：添加成功
		//		false：添加失败，有缓存记录
		if (checkHasURL((String)object))
			return false;
		else {
			if (innerURLSet.size() < innerURLSetSize)
				innerURLSet.add((String)object);
			else {
				innerURLSet.remove(replacePolicy());
				innerURLSet.add((String)object);
			}
			return true;
		}
	}
	
	protected String replacePolicy() {
		return null;
		
	}
	
	private Object remove(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean checkHasURL(String url) {
		if (innerURLSet.contains(url))
			return true;
		else {
			if (innerURLSet.size() < innerURLSetSize) {
				return false;
			} else {
				return false;
			}
		}
	}
	
	private boolean hasEleInSQL() {
		try {
			ResultSet ret = pds.executeQuery();
			if (!ret.next())
				return false;
			else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	private void addEleInSQL() {
		try {
			pds.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private HashSet<String> innerURLSet;
	private Connection connection;
	private PreparedStatement pds;
	private int innerURLSetSize;
	
	private static URLManager urlManager;
	
	private static final int DEFAULTSIZE = 1000;
	public static final String url = "jdbc:mysql://127.0.0.1/student";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "root";
    public static final String SQLSTAT = "";

}
