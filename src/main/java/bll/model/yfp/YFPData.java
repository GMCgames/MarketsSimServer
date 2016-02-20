package bll.model.yfp;

public class YFPData {
	String symbol;
	String name;
	String lastTrade;
	String change;
	String volume;
	
	public String getSymbol(){
		return symbol;
	}
	public void setSymbol(String symbol){
		this.symbol = symbol;
	}
	public String getName(){
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getLastTrade()
	{
		return lastTrade;
	}
	public void setLastTrade(String lastTrade)
	{
		this.lastTrade = lastTrade;
	}
	public String getChange()
	{
		return change;
	}
	public void setChange(String change)
	{
		this.change = change;
	}
	public String getVolume()
	{
		return volume;
	}
	public void setVolume(String volume)
	{
		this.volume = volume;
	}
}
