package bll.model.ecobolsa.mc.delaytime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EcoJsonWrapper {
	@JsonProperty("nb")
	public String symbol;
	@JsonProperty("p")
	public String price;
	@JsonProperty("vn")
	public String varNet;
	@JsonProperty("v")
	public String var;
	@JsonProperty("h")
	public String time;
}