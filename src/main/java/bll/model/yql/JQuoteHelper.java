package bll.model.yql;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JQuoteHelper implements Serializable{
	@JsonProperty("quote")
	public JQuote quotes;
	//public List<JQuote> quotes;
 
	JQuote getQuotes(){
		return this.quotes;
	}
	void setQuotes(JQuote quotes){
		this.quotes = quotes;
	}
	//more fields, getters/setters declared here
 
}