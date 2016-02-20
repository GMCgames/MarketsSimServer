package bll.model.yql;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JQuote implements Serializable{
 
  public String Symbol;
  public String LastTradeDate;
  public String Open;
  public String PreviousClose;
  public String Ask;
  public String LastTradeTime;
 
//more fields declared here
 
}
