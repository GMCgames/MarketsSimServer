package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bll.StatusService;
import bll.MarketsSimService;
import bll.model.Status;
import bll.model.MarketsSimSymbol;


@RestController
public class StatusController {
	
	private StatusService _statusService;
	private MarketsSimService _marketsSimService;
	
	public StatusController() {
		_statusService = new StatusService();
		_marketsSimService = new MarketsSimService();

	}
	
	@RequestMapping("/status")
	public Status status(){
		return _statusService.getCurrentStatus();
	}

	@RequestMapping("/MarketsSimQuery")
	public MarketsSimSymbol getMarketsSimSymbol(
			@RequestParam(value="country", defaultValue="ES") String country,
			@RequestParam(value="market", defaultValue="IBEX35") String market,
			@RequestParam(value="ticker", defaultValue="ACCIONA") String ticker,
			@RequestParam(value="rt", defaultValue="no") String rt)
	{
		MarketsSimSymbol mss=null;
		boolean isRealtime;
		
		isRealtime = (rt.compareTo("yes") == 0);

		System.out.println(country + market + ticker + isRealtime);
		mss = _marketsSimService.getMarketsSimSymbol(country, market, ticker, isRealtime);
		
		
		return mss;
	}
			
}
