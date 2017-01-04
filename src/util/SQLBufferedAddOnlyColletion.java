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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import exception.SQLInitFailException;

public class SQLBufferedAddOnlyColletion implements AddOnlyColletion {
	
	public SQLBufferedAddOnlyColletion() {
		// TODO Auto-generated constructor stub
		innerSet = new HashSet<>();
		size = 0;
		try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      connection = DriverManager.getConnection(DB_URL, USER, PASS);

		      //STEP 4: Execute a query
		      System.out.println("Creating database...");
		      pds = connection.createStatement();
		} catch (ClassNotFoundException | SQLException exception) {
			//exception.printStackTrace();
			System.out.println("初始化失败,使用普通缓冲模式");
		}
	}

	@Override
	public boolean add(Object object) {
		// TODO Auto-generated method stub
		//增加一个URL进入缓存
		//	返回值
		//		true：添加成功
		//		false：添加失败，有缓存记录
		
		try {
			
			if (pds == null || connection == null) throw new SQLInitFailException();
			
			if (checkHasURL((String)object))
				return false;
			else {
				if (innerSet.size() < innerSetSize) {
					innerSet.add((String)object);
					size += 1;
					return true;
				} else {
					if (!hasEleInSQL()) {
						String repalceString = replacePolicy();
						innerSet.remove(repalceString);
						innerSet.add((String)object);
						addEleInSQL(repalceString);
						size += 1;
						return true;
					} else {
						return false;
					}
				}
			}	
		} catch (SQLInitFailException exception) {
			//处理数据库初始化失败情况
			if (innerSet.contains((String)object))
				return false;
			else {
				innerSet.add((String)object);
				return true;
			}
			
		}
	}
	
	protected String replacePolicy() {
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
			ResultSet ret = pds.executeQuery("");
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
	
	private void addEleInSQL(String element) {
		try {
			pds.executeUpdate("");
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
	private Statement pds;
	private int innerSetSize;
	
	private int size;
	
	private static final int DEFAULTSIZE = 1000;
	public static final String DB_URL = "jdbc:mysql://127.0.0.1/student";  
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    public static final String USER = "root";
    public static final String PASS = "root";
    public static final String SQLSTAT = "";
    public static final String CHECKSTAT = "";

}
