package example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Zhihu.DefaultCrawler;

public class CalwerDemo_ZHihu extends DefaultCrawler {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalwerDemo_ZHihu crawlerDemo = new CalwerDemo_ZHihu("https://www.zhihu.com/people/chen-lan-xiang-76/answers");
		crawlerDemo.setPageRegex("UserLink-link.+?href=\"(.+?)\">");
		List<String> list = new ArrayList<String>();
		list.add(new String("Icon--company.+?div>(.+?)<"));
		list.add(new String("(Icon--female)"));
		list.add(new String("(Icon--male)"));
		crawlerDemo.setResFilter(list);
		crawlerDemo.run();
	}
	
	public CalwerDemo_ZHihu() {
		// TODO Auto-generated constructor stub
		super();
		result = Result.getInstance();
	}
	
	public CalwerDemo_ZHihu(String start) {
		super(start);
		result = Result.getInstance();
	}
	
	public void setResFilter(List<String> filter) {
		resultRegexList = filter;
	}
	
	public final Object setCrawlerPolicy(String page) {
		// TODO Auto-generated method stub
		for (String string : resultRegexList) {
			Pattern pattern = Pattern.compile(string);
			Matcher matcher = pattern.matcher(page);
			if (matcher.find()) {
				if (string.contains("Icon--male")) {
					this.result.addSex("male");
				} else if (string.contains("Icon--female")) {
					this.result.addSex("female");
				} else {
					this.result.addWork(matcher.group(1));
				}
			}
		}
		return null;
	}
	
	public void storeResPolicy(Object result) {
		System.out.println("男士" + this.result.sex.get("male") + "人|" + "女士" + this.result.sex.get("female") + "人");
		System.out.println("职业分布:");
		System.out.println(this.result.work.toString());
	}
	
	public Result result;

	private List<String> resultRegexList;

}

class Result {
	
	public static Result getInstance() {
		if (result == null)
			result = new Result();
		return result;
	}
	
	public void addWork(String work) {
		if (this.work.containsKey(work)) {
			this.work.replace(work, this.work.get(work) + 1);
		} else {
			this.work.put(work, 1);
		}
	}
	
	public void addSex(String sex) {
		if (this.sex.containsKey(sex)) {
			this.sex.replace(sex, this.sex.get(sex) + 1);
		} else {
			this.sex.put(sex, 1);
		}
	}
 	
	private Result() {
		// TODO Auto-generated constructor stub
		work = new HashMap<>();
		sex = new HashMap<>();
	}
	
	HashMap<String, Integer> work;
	HashMap<String, Integer> sex;
	
	private static Result result;
}