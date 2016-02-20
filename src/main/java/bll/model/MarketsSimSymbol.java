package bll.model;

public class MarketsSimSymbol {

        
        private final String symbol;
        private final String price;
        private final String time;
        private final String date;
        private final String prevClose;

        public MarketsSimSymbol (String symbol, String price,
        		String time, String date, String prevClose)
        {
            this.time = time;
            this.symbol = symbol;
            this.price = price;
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
