package bll;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bll.model.investing.SymbolInfo;

public class InvestingParserService {
	SymbolInfo[] getSymbolInfo(String name) throws Exception
	{
		SymbolInfo ss[];
		String url = "http://www.investing.com/indices/spain-35-components";
		
		
		Connection.Response res = Jsoup.connect(url).userAgent("Mozzila").
		        timeout(5000).ignoreHttpErrors(true).followRedirects(true).execute();
		if (res.statusCode() == 307) {
		    String sNewUrl = res.header("Location");
		    if (sNewUrl != null && sNewUrl.length() > 7)
		        url = sNewUrl;
		    res = Jsoup.connect(url).
		            timeout(5000).execute();
		}
		Document doc = res.parse();
		Element body = doc.body();
		
		
		Element table = body.getElementById("cr1");
		System.out.println(table);
		Elements trs = table.select("tr");
	
		Element e1=null,e2=null,e3=null,e4=null,e5=null,e6=null,
				e7=null, e8=null, e9=null, e10=null;
		
		int maxQuotes = trs.size();
		ss = new SymbolInfo[maxQuotes];
		int cont = 0;
		for (Element i : trs)
		{
			System.out.println("TR");
			Elements listTableData = i.select("td");
			System.out.println(listTableData.size());
			if (listTableData.size() == 10)
			{
				
				e1 = listTableData.get(0);
				System.out.println(e1.text());
				e2 = listTableData.get(1);
				System.out.println(e2.text());
				e3 = listTableData.get(2);
				System.out.println(e3.text());
				e4 = listTableData.get(3);
				System.out.println(e4.text());
				e5 = listTableData.get(4);
				System.out.println(e5.text());
				e6 = listTableData.get(5);
				System.out.println(e6.text());
				e7 = listTableData.get(6);
				System.out.println(e7.text());
				e8 = listTableData.get(7);
				System.out.println(e8.text());
				e9 = listTableData.get(8);
				System.out.println(e9.text());
				e10 = listTableData.get(9);
				System.out.println(e10.text());
				if ((name.compareTo(e2.text()) == 0) || (name.compareTo("all") == 0))
				{
			    	SymbolInfo s = new SymbolInfo(e2.text(), e3.text(), e4.text(), e5.text(),
			    			e6.text(), e7.text(), e8.text(), e9.text());	
			    	
			    	ss[cont] = s;
			    	cont++;
			  
				}
				
			}
	
		}	
		return ss;
	}

}
