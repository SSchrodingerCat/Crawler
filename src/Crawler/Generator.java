/**
 * @author adventure
 * @time 2016/12/29
 * 
 * @description
 * 		生成器接口，包含next()和hasNext()函数
 */
package Crawler;

public interface Generator {
	
	public String next();
	public boolean hasNext();
	
}
