package engine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Salaries {



public static String getSalary(String title) throws Exception{
    Document doc = Jsoup.connect("https://www.glassdoor.com/Salaries/"+title+"-salary-SRCH_KO0,17.htm").get();
    Elements el = doc.select(".OccMedianBasePayStyle__payNumber");
    System.out.print(el.text());
    return el.text();
}


}
