import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class PriceControllerTest {

    private Scanner scanner;
    Price model;
    PriceView view;
    PriceController controller;

    @BeforeEach
    void setUp() {
        model = new Price();
        view = new PriceView();
        controller = new PriceController(model, view);

        try {
            scanner = new Scanner(new File("src/testPrices.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void testSave() {
        String expected = "";
        double bid;
        double ask;
        if (scanner.hasNextLine()) {
            String insert = scanner.nextLine();
            controller.save(insert);
            String[] pom = insert.split(",");
            bid = Double.parseDouble(pom[2]);
            ask = Double.parseDouble(pom[3]);
            expected = "Unique id: " + pom[0] + "\n" +
                    "Instrument Name: " + pom[1] + "\n" +
                    "Bid: " + String.format("%4f", bid) + "\n" +
                    "Ask: " + String.format("%4f", ask) + "\n" +
                    "Timestamp: " + pom[4];
        }

        Assert.assertTrue(expected.equals(controller.printView()));
    }

    @Test
    void testGetLast() {
        String insert = "";
        while (scanner.hasNextLine()) {
            insert = scanner.nextLine();
            controller.save(insert);
        }
        String[] pom = insert.split(",");
        double expectedBid = Double.parseDouble(pom[2]) - Double.parseDouble(pom[2])*0.001;
        double expectedAsk = Double.parseDouble(pom[3]) + Double.parseDouble(pom[3])*0.001;
        Price testPrice = controller.getLast();
        Assertions.assertEquals(expectedBid, testPrice.getBid());
        Assertions.assertEquals(expectedAsk, testPrice.getAsk());
    }
}