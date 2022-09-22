import java.text.DecimalFormat;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class PriceController {

    private Price model;
    private PriceView view;

    //temp connection to database
    private PriceDAOImpl database;

    DecimalFormat dec = new DecimalFormat("#0.0000");

    public PriceController(Price model, PriceView view){
        this.model = model;
        this.view = view;
        database = new PriceDAOImpl();
    }


    //here we can use REST point to save price
    public void save(String price) {
        String[] insert = price.split(",");
        setPriceUid(parseInt(insert[0]));
        setPriceInstrumentName(insert[1]);
        setPriceBid(parseDouble(insert[2]));
        setPriceAsk(parseDouble(insert[3]));
        setPriceTimestamp(insert[4]);
        database.save(model);
    }

    //here we can use REST point to retrieve last price
    public Price getLast() {
        model = database.onMessage();
        return model;
    }

    public void updateView() {
        view.printPriceDetails(model.getUid(), model.getInstrumentName(), model.getBid(), model.getAsk(), model.getTimestamp());
    }

    public String printView(){
        return view.createPriceDetails(model.getUid(), model.getInstrumentName(), model.getBid(), model.getAsk(), model.getTimestamp());
    }

    public int getPriceUid() {
        return model.getUid();
    }

    private void setPriceUid(int uid) {
        model.setUid(uid);
    }

    public String getPriceInstrumentName() {
        return model.getInstrumentName();
    }

    private void setPriceInstrumentName(String instrumentName) {
        model.setInstrumentName(instrumentName);
    }

    public double getPriceBid() {
        return model.getBid();
    }

    private void setPriceBid(double bid) {
        model.setBid(bid);
    }

    public double getPriceAsk() {
        return model.getAsk();
    }

    private void setPriceAsk(double ask) {
        model.setAsk(ask);
    }

    public String getPriceTimestamp() {
        return model.getTimestamp();
    }

    private void setPriceTimestamp(String timestamp) {
        model.setTimestamp(timestamp);
    }
}