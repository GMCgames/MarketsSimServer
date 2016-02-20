package bll;

import java.net.URI;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import bll.model.ecobolsa.mc.delaytime.*;


public class EcoService {

	EcoJsonWrapper getEcoMCRealTime(String name)
	{
		String url_eco_rt = "http://www2.ecobolsa.com/js/data/mercado-continuos.json";

		EcoJsonWrapper lejw[] = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(
	            new MappingJackson2HttpMessageConverter());
			//ejw = restTemplate.getForObject(new URI(url_eco_rt), EcoJsonWrapper.class);
			lejw = restTemplate.getForObject(new URI(url_eco_rt), EcoJsonWrapper[].class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		for (int i=0; i<lejw.length; i++)
		{
			if (lejw[i].symbol.compareTo(name) == 0)
			{
				return lejw[i];
			}
		}
		
		return lejw[0];
	}
	
	
	EcoJsonWrapper getEcoMCDelay(String name)
	{
		String url_eco_rt = "http://www.ecobolsa.com/js/data/mercado-continuos.json";

		EcoJsonWrapper lejw[] = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(
	            new MappingJackson2HttpMessageConverter());
			//ejw = restTemplate.getForObject(new URI(url_eco_rt), EcoJsonWrapper.class);
			lejw = restTemplate.getForObject(new URI(url_eco_rt), EcoJsonWrapper[].class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		for (int i=0; i<lejw.length; i++)
		{
			System.out.println(i+":"+lejw[i].symbol + ":" + name);
			if (lejw[i].symbol.compareTo(name) == 0)
			{
				return lejw[i];
			}
		}
		
		return lejw[0];		
	}
}
