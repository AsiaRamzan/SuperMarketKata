package cdl.com;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;


public class ItemOfferTest {
  ItemOffer itemOffer = new ItemOffer("A", 1, 50.0);

  @Test
  public void priceTest() {
    assertEquals(itemOffer.getPrice(), 50.00, 0.001);
  }
}
