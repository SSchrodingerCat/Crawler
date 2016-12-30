/**
 * adventure
 * @time 2016/12/28
 * 
 * @description
 * 		实现了QueueCollection接口的类
 * 		使用SQL作为缓冲
 * 		适用于大量数据需要记录的情况
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import exception.SQLInitFailException;

public class SQLBufferedQueueCollection implements QueueColleciton {

	public SQLBufferedQueueCollection() {
		// TODO Auto-generated constructor stub
		headList = new ArrayList<String>();
		endList = new ArrayList<String>();
		innerSetSize = DEFAULTSIZE;
		connection = null;
		pds = null;
		size = 0;
		try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      connection = DriverManager.getConnection(DB_URL, USER, PASS);

		      //STEP 4: Execute a query
		      pds = connection.createStatement();
		} catch (ClassNotFoundException | SQLException exception) {
			//exception.printStackTrace();
			System.out.println("初始化失败,使用普通缓冲模式");
		}
	}
	
	/* (non-Javadoc)
	 * @see util.QueueColleciton#add(java.lang.Object)
	 */
	@Override
	public boolean add(Object object) {
		// TODO Auto-generated method stub
		try {
			//throw new NullPointerException();
			if (pds == null || connection == null) throw new SQLInitFailException();
			
			endList.add(0, (String) object);
			if (endList.size() >= innerSetSize/2) {
				swapBuffer("SQL");
			}
			if (isEmptyInSQL()) {
				headList.add(endList.remove(0));
			}
			
			size += 1;
			
			return true;
		} catch (SQLInitFailException exception) {
			headList.add(0, (String)object);
			size += 1;
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see util.QueueColleciton#peek()
	 */
	@Override
	public Object peek() {
		// TODO Auto-generated method stub
		return headList.get(headList.size() - 1);
	}

	/* (non-Javadoc)
	 * @see util.QueueColleciton#poll()
	 */
	@Override
	public Object poll() {
		// TODO Auto-generated method stub
		try {
			//throw new NullPointerException();
			if (pds == null || connection == null) throw new SQLInitFailException();
			
			String retVal = headList.remove(headList.size() - 1);
			if (headList.size() <= 0) {
				swapBuffer("HEADLISTMEMORY");
			}
			size -= 1;
			return retVal;
			
		} catch (SQLInitFailException exception) {
			if (headList.isEmpty())
				return null;
			String retVal = headList.get(headList.size() - 1);
			headList.remove(headList.size() - 1);
			size -= 1;
			return retVal;
		}
	}
	
	private void swapBuffer(String des) {
		if (des.equals("SQL")) {
			while (endList.size() != 0) {
				addEleInSQL(endList.get(0));
				endList.remove(0);
			}
		} else if (des.equals("HEADLISTMEMORY")) {
			headList = getEleInSQL();
		} else {
			return;
		}
	}
	
	@Override
 	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	private void addEleInSQL(String element)  {
		
		try {
			pds.executeUpdate("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private List<String> getEleInSQL() {
	
		try {
			int count = 0;
			List<String> tmp = new ArrayList<>();
			ResultSet resultSet = pds.executeQuery("");
			while (resultSet.next()) {
				tmp.add(resultSet.getString("element"));
				count += 1;
				if (count >= innerSetSize/2)
					break;
			}
			return tmp;
		} catch (SQLException exception) {
			return null;
		}
	}
	
	private boolean isEmptyInSQL() {
		
		try {
			return pds.execute("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new NullPointerException();
		}
	}
	
	private List<String> headList;
	private List<String> endList;
	
	private Connection connection;
	private Statement pds;
	
	private int size;
	private int innerSetSize;
	
	private static final int DEFAULTSIZE = 1000;
	public static final String DB_URL = "jdbc:mysql://127.0.0.1/student";  
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    public static final String USER = "root";
    public static final String PASS = "root";
    public static final String SQLSTAT = "";
    public static final String CHECKSTAT = "";

}
