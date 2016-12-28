package Zhihu;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCrawler {
	
	public AbstractCrawler() {
		// TODO Auto-generated constructor stub
		resultRegexList = new ArrayList<>();
		
	}
	
	public AbstractCrawler(Generator pageGenerator) {
		this();
		this.pageGenerator = pageGenerator;
	}
	
	public void run() {
		
		if (pageGenerator == null) {
			System.out.println("没有2匹配的页面加载器，退出");
		} else {
			while (pageGenerator.hasNext()) {
				String page = pageGenerator.next();
				Object result;
				result = setCrawlerPolicy(page, resultRegexList);
				storeResPolicy(result);
			}
		}
		
	}
	
	public void setResultFilter(List<String> rList) {
		resultRegexList = rList;
	}
	
	//抓取规则，为抓取提供独特性方法
	public abstract Object setCrawlerPolicy(String page, List<String> resultRegexList);
	
	//设置结果储存规则
	public abstract void storeResPolicy(Object result);
	
	//加载页面生成器
	public void setPageGenerator(Generator generator) {
		pageGenerator = generator;
	}	
	
	private List<String> resultRegexList;
	private Generator pageGenerator;

}
