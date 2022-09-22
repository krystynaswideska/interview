public class Main {
    //I assume that we have 4 decimal places after dot in decimal values
    public static void main(String[] args) {

        Price model = new Price();

        PriceView view = new PriceView();

        PriceController controller = new PriceController(model, view);
    }
}