/**
 * adventure
 * @time 2016/12/28
 * 
 * @description
 * 		实现了AddOnlyColletion接口的类
 * 		使用SQL作为缓冲
 * 		适用于大量数据需要记录的情况
 */
package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class SQLBufferedAddOnlyColletion implements AddOnlyColletion {
	
	public SQLBufferedAddOnlyColletion() {
		// TODO Auto-generated constructor stub
		innerSet = new HashSet<>();
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
			if (innerSet.size() < innerSetSize)
				innerSet.add((String)object);
			else {
				innerSet.remove(replacePolicy());
				innerSet.add((String)object);
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
		if (innerSet.contains(url))
			return true;
		else {
			if (innerSet.size() < innerSetSize) {
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
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Object get(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size == 0);
	}
	
	private HashSet<String> innerSet;
	private Connection connection;
	private PreparedStatement pds;
	private int innerSetSize;
	
	private int size;
	
	private static final int DEFAULTSIZE = 1000;
	public static final String url = "jdbc:mysql://127.0.0.1/student";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "root";
    public static final String SQLSTAT = "";

}
