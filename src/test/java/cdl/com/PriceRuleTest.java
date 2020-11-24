package cdl.com;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class PriceRuleTest {
  @Test
	public void testTotal() {
    ScanInput sc= new ScanInput();
		sc.scanItemDataMap.put("A", 50.0);
		sc.scanItemDataMap.put("B", 30.0);
		sc.scanItemDataMap.put("C", 20.0);
		sc.itemDataList.add(sc.scanItemDataMap);
		assertEquals(PriceRules.calculateTotal(), 100.00, 0.001);
	}
}
