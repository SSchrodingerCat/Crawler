/**
 * @author adventure
 * @time 2016/12/25
 * 
 * @description
 * 		只能进行添加的集合接口
 */
package util;

public interface AddOnlyColletion {
	
	public boolean add(Object object);
	
	public int size();
	
	public Object get(int i);
	
	public boolean isEmpty();

}
