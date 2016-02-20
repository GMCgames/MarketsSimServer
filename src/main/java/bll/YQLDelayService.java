package bll;

import java.net.URI;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import bll.model.yql.*;

public class YQLDelayService {
	public YQLDelayService(){
	}
	public  YQLSymbol getYQLSymbol(String name)
	{
		YQLData data = null;
		YQLSymbol symbol = null;
		String url = "https://query.yahooapis.com/v1/public/yql?q=SELECT%20*%20FROM%20yahoo.finance.quotes%20WHERE%20symbol%20%3D%20" + name + "&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(
                new MappingJackson2HttpMessageConverter());
			data = restTemplate.getForObject(new URI(url), YQLData.class);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}	
		String ltt = data.rs.jquoteHelp.quotes.LastTradeTime;
		String ask = data.rs.jquoteHelp.quotes.Ask;
		String symb = data.rs.jquoteHelp.quotes.Symbol;
		String ltd = data.rs.jquoteHelp.quotes.LastTradeDate;
		String pc = data.rs.jquoteHelp.quotes.PreviousClose;
		symbol = new YQLSymbol(symb, ask, pc, ltt, ltd);
		return symbol;
	}
}
