import java.util.List;

public interface PriceDAO {

    public void save(Price price);

    Price onMessage();
}