package bll.model.yql;

public class YQLSymbol {

        
        private final String symbol;
        private final String price;
        private final String time;
        private final String date;
        private final String prevClose;

        public YQLSymbol (String symbol, String price,
        		String time, String date, String prevClose)
        {
            
            this.symbol = symbol;
            this.price = price;
            this.time = time;
            this.date = date;
            this.prevClose = prevClose;
        }
        public String getTime() {
            return time;
        }

        public String getSymbol() {
            return symbol;
        }
        public String getPrice() {
        	return price;
        }
        public String getPrevClose() {
        	return prevClose;
        }
        public String getDate() {
        	return date;
        }
}
