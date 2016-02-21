package bll;


import bll.model.MarketsSimSymbol;
import bll.model.yql.YQLSymbol;
import bll.model.ecobolsa.mc.delaytime.EcoJsonWrapper;
import bll.model.yfp.YFPData;

public class MarketsSimService {
	
	private YQLDelayService _yqlDelayService;
	private YFParserService _yfParserService;
	private InvestingParserService _investingService;
	private EcoService _ecoService;
	
	public MarketsSimService(){
		
		_yqlDelayService = new YQLDelayService();
		_yfParserService = new YFParserService();
		_ecoService = new EcoService();
		_investingService = new InvestingParserService();
	}
	/*
	 * Obtención de valores bursátiles en función de:
	 *      País donde cotiza
	 *      Market índice donde cotiza
	 *      ticker Identificador del valor
	 *      RT Tiempo real o retardado 15 m
	 *      
	 * Los datos bursátiles los obtiene de la base de datos y si esta no está
	 * actualizada entonces llama a los servicios remotos para mantener los datos.
	 */
	public  MarketsSimSymbol getMarketsSimSymbol(String country, 
			String market, String ticker, boolean rt)
	{
		MarketsSimSymbol mss=null;
		
		System.out.println("Service...");
		if ((country.compareTo("ES") != 0) && (!rt))
		{
			YQLSymbol sb = _yqlDelayService.getYQLSymbol("'"+ticker+"'");
			mss = new MarketsSimSymbol(sb.getSymbol(), sb.getPrice(),
					sb.getTime(), sb.getDate(), sb.getPrevClose());
		}
		else if ((country.compareTo("ES") == 0) && (!rt))
		{
			System.out.println("ES & |rs");
			EcoJsonWrapper ejw = _ecoService.getEcoMCDelay(ticker);
			mss = new MarketsSimSymbol(ejw.symbol, ejw.price, ejw.time, null, null);
		}
		else if ((country.compareTo("ES") == 0) && rt)
		{
			System.out.println("ES & rs");
			EcoJsonWrapper ejw = _ecoService.getEcoMCRealTime(ticker);
			mss = new MarketsSimSymbol(ejw.symbol, ejw.price, ejw.time, null, null);
		}
		else if ((market.compareTo("nasdaq") == 0) && rt)
		{
			System.out.println("NASDAQ\n");
			YFPData yfp = _yfParserService.getYFPData(ticker);
			System.out.println("nasdaq " + yfp.getSymbol());
			mss = new MarketsSimSymbol(yfp.getSymbol(), yfp.getLastTrade(), null,null,null);
		}

		// Comprobar BD y Actualizar si necesario consultando
		
		
		
		// Obtener valor de la Base de datos.
		
		// Generar mss y devolver
		
		return mss;
	}
}
