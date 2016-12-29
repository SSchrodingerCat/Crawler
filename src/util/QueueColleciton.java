/**
 * @author adventure
 * @time 2016/12/25
 * 
 * @description
 * 		以队列形式进行添加删除的集合接口
 */
package util;

public interface QueueColleciton {
	
	public boolean add(Object object);
	
	public Object peek();
	
	public Object poll();
	
	public boolean isEmpty();
	
	public int size();

}
