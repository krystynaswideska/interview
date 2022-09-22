public class PriceView {
    public void printPriceDetails(int uid, String instrumentName, double bid, double ask, String timestamp){
        System.out.println(createPriceDetails(uid, instrumentName, bid, ask, timestamp));
    }

    public String createPriceDetails(int uid, String instrumentName, double bid, double ask, String timestamp){
        String priceDetails = "Unique id: " + uid + "\n" +
                "Instrument Name: " + instrumentName + "\n" +
                "Bid: " + String.format("%4f", bid) + "\n" +
                "Ask: " + String.format("%4f", ask) + "\n" +
                "Timestamp: " + timestamp;
        return priceDetails;
    }
}