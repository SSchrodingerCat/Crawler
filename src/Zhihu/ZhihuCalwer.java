package Zhihu;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZhihuCalwer extends DefaultCrawler {
	
	public ZhihuCalwer() {
		// TODO Auto-generated constructor stub
		super();
		result = Result.getInstance();
	}
	
	public ZhihuCalwer(String start) {
		super(start);
		result = Result.getInstance();
	}
	
	public Result result;
	
	public final Object setCrawlerPolicy(String page, List<String> resultRegexList) {
		// TODO Auto-generated method stub
		for (String string : resultRegexList) {
			System.out.println(string);
			System.out.println(page);
			Pattern pattern = Pattern.compile(string);
			Matcher matcher = pattern.matcher(page);
			Boolean result = matcher.find();
			System.out.println(result);
			if (string.contains("Icon--male")) {
				this.result.addSex("male");
			} else if (string.contains("Icon--female")) {
				this.result.addSex("male");
			} else {
				this.result.addWork(String.valueOf(result));
			}
		}
		return null;
	}

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
			this.work.put(work, 0);
		}
	}
	
	public void addSex(String sex) {
		if (this.sex.containsKey(sex)) {
			this.sex.replace(sex, this.sex.get(sex) + 1);
		} else {
			this.sex.put(sex, 0);
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