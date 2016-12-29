package Zhihu;

import java.util.ArrayList;
import java.util.List;

public class main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZhihuCalwer defaultCrawler = new ZhihuCalwer("https://www.zhihu.com/people/chen-lan-xiang-76/answers");
		defaultCrawler.setPageRegex("UserLink-link.+?href=\"(.+?)\">");
		List<String> list = new ArrayList<String>();
		list.add(new String("Icon--company.+?div>(.+?)<"));
		list.add(new String("(Icon--female)"));
		list.add(new String("(Icon--male)"));
		defaultCrawler.setResultFilter(list);
		defaultCrawler.run();
	}
	

}
