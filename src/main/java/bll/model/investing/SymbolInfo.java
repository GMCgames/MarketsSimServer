package bll.model.investing;


public class SymbolInfo {

    private String name;
    private String last;
    private String high;
    private String low;
    private String change;
    private String percent;
    private String volume;
    private String time;

    public SymbolInfo(String name, String last, String high, String low,
    		String change, String percent, String vol, String time) {
        this.name = name;
        this.last = last;
        this.high = high;
        this.low = low;
        this.change = change;
        this.percent = percent;
        this.volume = vol;
        this.time = time;
    }


    public String getName() {
        return name;
    }
    public String getLast() {
    	return last;
    }
    public String getHigh() {
    	return high;
    }
    public String getLow() {
    	return low;
    }
    public String getChange() {
    	return change;
    }
    public String getPercent() {
    	return percent;
    }
    public String getVolume() {
    	return volume;
    }
    public String getTime() {
    	return time;
    }
}