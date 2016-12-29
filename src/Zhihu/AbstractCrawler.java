package Zhihu;
/**
 * @author adventure
 * @time 2016.12.25
 * 
 * @description
 * 	抽象爬虫类
 * 		主要有两个抽象方法
 * 			Object setCrawlerPolicy(String page)
 * 				参数为目标页面
 * 				返回从目标页面获得的结果对象
 * 			void storeResPolicy(Object result)
 * 				参数为从页面获得的结果对象
 *		实例方法
 *			void run()
 *				无参数，开启一个线程执行爬虫操作
 *		实例对象
 *			generator pageGenerator
 *				页面生成器，控制生成页面的逻辑 
 */

public abstract class AbstractCrawler {
	
	public AbstractCrawler() {
		// TODO Auto-generated constructor stub
		
	}
	
	public AbstractCrawler(Generator pageGenerator) {
		this();
		this.pageGenerator = pageGenerator;
	}
	
	public void run() {
		
		if (pageGenerator == null) {
			System.out.println("没有相匹配的页面加载器，退出");
		} else {
			while (pageGenerator.hasNext()) {
				String page = pageGenerator.next();
				Object result;
				result = setCrawlerPolicy(page);
				storeResPolicy(result);
			}
		}
		
	}
	
	//抓取规则，为抓取提供独特性方法
	public abstract Object setCrawlerPolicy(String page);
	
	//设置结果储存规则
	public abstract void storeResPolicy(Object result);
	
	//加载页面生成器
	public void setPageGenerator(Generator generator) {
		pageGenerator = generator;
	}	
	
	private Generator pageGenerator;

}
