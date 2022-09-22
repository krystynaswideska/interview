import java.util.ArrayList;
import java.util.List;

public class PriceDAOImpl implements PriceDAO{

    //database
    private List<Price> prices;

    public PriceDAOImpl() {
        prices = new ArrayList<>();
    }

    @Override
    public void save(Price price) {
        prices.add(price);
    }

    @Override
    public Price onMessage() {
        Price price = prices.get(prices.size() - 1);
        Price adjustedPrice = new Price();
        double bid = price.getBid();
        double ask = price.getAsk();
        adjustedPrice.setBid(bid - bid*0.001);
        adjustedPrice.setAsk(ask + ask*0.001);
        adjustedPrice.setUid(price.getUid());
        adjustedPrice.setInstrumentName(price.getInstrumentName());
        adjustedPrice.setTimestamp(price.getTimestamp());
        return adjustedPrice;
    }
}
