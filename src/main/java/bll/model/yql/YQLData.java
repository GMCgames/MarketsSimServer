package bll.model.yql;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YQLData {
 
  @JsonProperty("query")
  public JResponse rs;
  
  YQLData() {      
	  
  }
}



 
