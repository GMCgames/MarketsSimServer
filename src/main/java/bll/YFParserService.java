package bll;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bll.model.yfp.*;

public class YFParserService {
    public YFPData getYFPData(String name)
    {
    
    	boolean cont = true;
    	int npage = 0;
    	Document doc = null;
    	YFPData data = new YFPData();
    	String url1 = "http://finance.yahoo.com/q/cp?s=%5EIXIC&c=";
    	String url2 = "&alpha=";
    	
    	
    	
    	
    	
       	while (cont)
    	{
    		String url = url1 + npage + url2 + name.charAt(0);
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	//Element content = doc.getElementById("content");
	    	Element body = doc.body();
	    	Elements trs = body.select("tr");
	    	cont = false;
	    	
	    	for (Element i : trs)
	    	{
	    		Elements listTableData = i.getElementsByClass("yfnc_tabledata1");
	    		if (listTableData.size() == 5)
	    		{
	    			cont = true;
	    			Element e1 = listTableData.get(0);
	    			System.out.println(e1.text());
	    			if (name.compareTo(e1.text()) < 0)
	    			{
	    				cont = false;
	    				break;
	    			}
	    			if (name.equals(e1.text()))
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
		    			cont = false;
	    			}
	    			
	    		}
	    	}
	    	npage++;
    	}
       	return data;
    }
}
