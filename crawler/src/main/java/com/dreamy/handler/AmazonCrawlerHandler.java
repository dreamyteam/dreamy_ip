package com.dreamy.handler;

import com.dreamy.mogodb.beans.BookInfo;
import com.dreamy.utils.ConstUtil;
import com.dreamy.utils.HttpUtils;
import com.dreamy.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AmazonCrawlerHandler extends AbstractCrawlerHandler {

	@Override
	public int getId() {
		return ConstUtil.CRAWLER_SOURCE_AMAZON;
	}

	@Override
	public BookInfo getByUrl(String url) {
		String html = HttpUtils.getHtmlGet(url, "null");
		if (StringUtils.isNotEmpty(html)) {
			Document document = Jsoup.parse(html);
			if (document != null) {
				BookInfo bean = new BookInfo();
				Elements infos = document.select("div.content >ul>li");
				if (infos != null && infos.size() > 0) {

					Element title = infos.first();
					if (title != null) {
						String content=title.text();
						if(StringUtils.isNotEmpty(content))
						{
							String arr[]=content.split(";");

							bean.setPress(arr[0].replace("出版社:",""));
							bean.setPushTime(result(arr[1]));
						}
					}
				}
				Elements authos = document.getElementsByClass("author");
				if (authos != null && authos.size() > 0) {
					String author="";
					for(Element element:authos)
					{
						author=author+element.text();
					}
					bean.setAuthor(author);
				}
				Element sort = document.getElementById("SalesRank");
				bean.setSaleSort(sort.text());
			}




		}


		return null;
	}



	@Override
	public String analyeUrl(String url) {
		return null;
	}


	public static String result(String content) {
		String result="";
		Pattern p = Pattern
				.compile("[0-9]{4}[年|\\-|/][0-9]{1,2}[月|\\-|/][0-9]{1,2}");
		Matcher m = p.matcher(content);
		while (m.find()) {
			if (!"".equals(m.group())) {
				result = m.group();
				result = result.replaceAll("年", "-");
				result = result.replaceAll("月", "-");
				result = result.replaceAll("/", "-");

			}
		}
		return result;
	}


}
