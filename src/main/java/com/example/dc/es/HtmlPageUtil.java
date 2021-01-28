package com.example.dc.es;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：duanchao
 * @ Date       ： 13:58 2021/1/28
 * @ Description：
 */
@Component
public class HtmlPageUtil {


    public List<BookVo> jiexi(String keyWord) throws IOException {
        String url = "https://search.jd.com/Search?keyword="+keyWord;

        Document parse = Jsoup.parse(new URL(url),30000);
        Element j_goodsList = parse.getElementById("J_goodsList");

        Elements li = j_goodsList.getElementsByTag("li");
        List<BookVo> bookVos = new ArrayList<>();
        for (Element el : li) {
            String image = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            BookVo bookVo = new BookVo();
            bookVo.setTitle(title);
            bookVo.setPrice(price);
            bookVo.setImg(image);
            bookVos.add(bookVo);
        }

        return bookVos;


    }
}