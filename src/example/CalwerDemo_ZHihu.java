package example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Crawler.DefaultCrawler;

public class CalwerDemo_ZHihu extends DefaultCrawler {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalwerDemo_ZHihu crawlerDemo = new CalwerDemo_ZHihu("https://www.zhihu.com/people/chen-lan-xiang-76/following");
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
					String name = matcher.group(1);
					this.result.addWork(name);
					
				}
			}
		}
		return null;
	}
	
	public void storeResPolicy(Object result) {
		String[] lStrings = this.result.getMostList();
		Collection<Integer> values = this.result.work.values();
		int total = 0;
		for (Iterator iterator = values.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			total += integer;
		}
		File file = new File("E:\\Xieyuan\\uestc\\git-repos\\Crawler\\src\\example\\data.txt");
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			FileWriter writer = new FileWriter(file, false);
			int maleNum = 0;
			int femaleNum = 0;
			if (this.result.sex.get("male") != null) {
				maleNum = this.result.sex.get("male");
			}
			if (this.result.sex.get("female") != null) {
				femaleNum = this.result.sex.get("female");
			}
			writer.append("总共" + (maleNum + femaleNum) + "人\r\n男士" + maleNum + "人|" + "女士" + femaleNum + "人\r\n");
			writer.append("职业分布\r\n");
			if (lStrings[0] == null)
				return;
			else if (lStrings[1] == null) {
				writer.append(lStrings[0] + ":" + this.result.work.get(lStrings[0]) + "人");
			} else if (lStrings[2] == null) {
				writer.append(lStrings[0] + ":" + this.result.work.get(lStrings[0]) + "人"
						+ lStrings[1] + ":" + this.result.work.get(lStrings[1]) + "人");
			} else if (lStrings[3] == null){
				writer.append(lStrings[0] + ":" + this.result.work.get(lStrings[0]) + "人"
						+ lStrings[1] + ":" + this.result.work.get(lStrings[1]) + "人"
						+ lStrings[2] + ":" + this.result.work.get(lStrings[2]) + "人");
			} else if (lStrings[4] == null) {
				writer.append(lStrings[0] + ":" + this.result.work.get(lStrings[0]) + "人"
						+ lStrings[1] + ":" + this.result.work.get(lStrings[1]) + "人"
						+ lStrings[2] + ":" + this.result.work.get(lStrings[2]) + "人"
						+ lStrings[3] + ":" + this.result.work.get(lStrings[3]) + "人");
			} else if (lStrings[5] == null) {
				writer.append(lStrings[0] + ":" + this.result.work.get(lStrings[0]) + "人\r\n"
						+ lStrings[1] + ":" + this.result.work.get(lStrings[1]) + "人\r\n"
						+ lStrings[2] + ":" + this.result.work.get(lStrings[2]) + "人\r\n"
						+ lStrings[3] + ":" + this.result.work.get(lStrings[3]) + "人\r\n"
						+ lStrings[4] + ":" + this.result.work.get(lStrings[4]) + "人\r\n");
			} else if (lStrings[6] == null){
				writer.append(lStrings[0] + ":" + this.result.work.get(lStrings[0]) + "人\r\n"
						+ lStrings[1] + ":" + this.result.work.get(lStrings[1]) + "人\r\n"
						+ lStrings[2] + ":" + this.result.work.get(lStrings[2]) + "人\r\n"
						+ lStrings[3] + ":" + this.result.work.get(lStrings[3]) + "人\r\n"
						+ lStrings[4] + ":" + this.result.work.get(lStrings[4]) + "人\r\n"
						+ lStrings[5] + ":" + this.result.work.get(lStrings[5]) + "人\r\n");
			} else {
				writer.append(lStrings[0] + ":" + this.result.work.get(lStrings[0]) + "人\r\n"
						+ lStrings[1] + ":" + this.result.work.get(lStrings[1]) + "人\r\n"
						+ lStrings[2] + ":" + this.result.work.get(lStrings[2]) + "人\r\n"
						+ lStrings[3] + ":" + this.result.work.get(lStrings[3]) + "人\r\n"
						+ lStrings[4] + ":" + this.result.work.get(lStrings[4]) + "人\r\n"
						+ lStrings[5] + ":" + this.result.work.get(lStrings[5]) + "人\r\n"
						+ lStrings[6] + ":" + this.result.work.get(lStrings[6]) + "人\r\n"
						+ "其他" + (total - this.result.work.get(lStrings[0]) - this.result.work.get(lStrings[1]) - this.result.work.get(lStrings[2]) - this.result.work.get(lStrings[3])
								 - this.result.work.get(lStrings[4]) - this.result.work.get(lStrings[5]) - this.result.work.get(lStrings[6])) + "人");
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			swap(this.work.get(work) + 1, work);
		} else {
			this.work.put(work, 1);
			swap(1, work);
		}
	}
	
	public String[] getMostList() {
		return MostList;
	}
	
	public void addSex(String sex) {
		if (this.sex.containsKey(sex)) {
			this.sex.replace(sex, this.sex.get(sex) + 1);
		} else {
			this.sex.put(sex, 1);
		}
	}
 	
	private void swap(int num, String name) {
		if (count <= MostList.length - 1) {
			int index = hasElement(name);
			if (index == -1) {
				MostList[count] = name;
				count++;
			} else {
				updateLess();
			}
		} else {
			int index = hasElement(name);
			if (index == -1 && work.get(name) > lessNum) {
				MostList[lessIndex] = name;
				updateLess();
			} else if (index != -1) {
				updateLess();
			}
		}
		
	}
	
	private int hasElement(String name) {
		for (int i = 0; i < MostList.length; i++) {
			if (MostList[i] == null)
				continue;
			else {
				if (MostList[i].equals(name))
					return i;
			}
		}
		return -1;
	}
	
	private void updateLess() {
		lessNum = work.get(MostList[0]);
		for (int i = 0; i < MostList.length; i++) {
			if (MostList[i] == null)
				continue;
			if (work.get(MostList[i]) < lessNum) {
				lessNum = work.get(MostList[i]);
				lessIndex = i;
			}
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
	
	
	private String[] MostList = new String[7];
	private int lessNum = 1;
	private int lessIndex = 0;
	private int count = 0;
}