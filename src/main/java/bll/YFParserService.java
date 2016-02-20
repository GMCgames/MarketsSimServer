package bll;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bll.model.yfp.*;

public class YFParserService {
    public YFPData getYFPData(String name)
    {
    	Document doc = null;
    	YFPData data = new YFPData();
    	String url = "http://finance.yahoo.com/q/cp?s=%5EIXIC&c=0&alpha=" + name.charAt(0);
    	try {
    		doc = Jsoup.connect(url).get();
    	}
    	catch (Exception e)
    	{
    		System.out.println(e);
    		return null;
    	}
    	//Element content = doc.getElementById("content");
    	Element body = doc.body();
    	Elements trs = body.select("tr");
    	for (Element i : trs)
    	{
    		System.out.println("TR");
    		Elements listTableData = i.getElementsByClass("yfnc_tabledata1");
    		System.out.println(listTableData.size());
    		if (listTableData.size() == 5) 
    		{
    			Element e1 = listTableData.get(0);
    			if (e1.text().compareTo(name) == 0)
    			{
	    			data.setSymbol(e1.text());
	    			Element e2 = listTableData.get(1);
	    			data.setName(e2.text());
	    			Element e3 = listTableData.get(2);
	    			data.setLastTrade(e3.text());
	    			Element e4 = listTableData.get(3);
	    			data.setChange(e4.text());
	    			Element e5 = listTableData.get(4);
	    			data.setVolume(e5.text());
    			}
    		}

    	}
    	return data;
    }
    
}
