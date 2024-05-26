import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    void TestEveryBranch() {

        //1. Empty Item List
        assertTrue(SILab2.checkCart(new ArrayList<>(), 50), "allItems list can't be null!");

        //2. Insufficient Payment
        List<Item> insufficientPayment = new ArrayList<>();
        insufficientPayment.add(new Item("Item", "1111111111", 500, 0));
        assertFalse(SILab2.checkCart(insufficientPayment, 300), "Insufficient payment");

        //3. Price Over With Discount And Barcode Starting With 0
        List<Item> priceOver = new ArrayList<>();
        priceOver.add(new Item("Item1", "011111111", 700, 0.1f));
        assertTrue(SILab2.checkCart(priceOver, 400), "Expected true for price over 300 with discount and barcode starting with zero");

        //4. Null Item List
        List<Item> nullItem = null;
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(nullItem, 200), "allItems list can't be null!");

        //5. No Barcode
        List<Item> noBarcode = new ArrayList<>();
        noBarcode.add(new Item("Item", null, 500, 0.1f));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(noBarcode, 450), "No barcode!");

        //6. Invalid Barcode
        List<Item> invalidBarcode = new ArrayList<>();
        invalidBarcode.add(new Item("Item", "123456AAA", 1000, 0.3f));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(invalidBarcode, 800), "Invalid character in item barcode!");

        //7. Item Name Is Null, Set To "Unknown"
        List<Item> nullName = new ArrayList<>();
        Item nullNameItem = new Item(null, "123456789", 600, 0.2f);
        nullName.add(nullNameItem);
        SILab2.checkCart(nullName, 1000);
        assertEquals("unknown", nullNameItem.getName(), "Expected item name to be set to 'unknown'");

        //8. Valid Item List
        List<Item> valid = new ArrayList<>();
        valid.add(new Item("Item1", "123456779", 400, 0.1f));
        valid.add(new Item("Item2", "123456889", 500, 0));
        assertTrue(SILab2.checkCart(valid, 1000), "Valid item list");
    }

    @Test
    void TestMultipleConditions() {

        //1. Price Equal To 300, Discount Over 0, Barcode Character Equal To 0
        List<Item> itemListCase5 = new ArrayList<>();
        itemListCase5.add(new Item("Item1", "0123456789", 300, 0.1f));
        assertTrue(SILab2.checkCart(itemListCase5, 1000), "Expected true");

        //2. Price Over, Discount Equal To 0, First Barcode Character Not Equal To 0
        List<Item> itemListCase4 = new ArrayList<>();
        itemListCase4.add(new Item("Item1", "1123456789", 400, 0));
        assertTrue(SILab2.checkCart(itemListCase4, 1000), "Expected true");

        //3. Price Over, Discount Equal To 0, First Barcode Character Equal To 0
        List<Item> itemListCase3 = new ArrayList<>();
        itemListCase3.add(new Item("Item1", "0123456789", 400, 0));
        assertTrue(SILab2.checkCart(itemListCase3, 1000), "Expected true");

        //4. Price Over, Discount Over 0, First Barcode Character Not Equal To 0
        List<Item> itemListCase2 = new ArrayList<>();
        itemListCase2.add(new Item("Item1", "1123456789", 400, 0.1f));
        assertTrue(SILab2.checkCart(itemListCase2, 1000), "Expected true");

        //5. Price Over, Discount Over 0, First Barcode Character Equal To 0
        List<Item> itemListCase1 = new ArrayList<>();
        itemListCase1.add(new Item("Item1", "0123456789", 400, 0.1f));
        assertTrue(SILab2.checkCart(itemListCase1, 1000), "Expected true");

        //6. Price Equal To 300, Discount Equal To 0, First Barcode Character Not Equal To 0
        List<Item> itemListCase8 = new ArrayList<>();
        itemListCase8.add(new Item("Item1", "1123456789", 300, 0));
        assertTrue(SILab2.checkCart(itemListCase8, 1000), "Expected true");

        //7. Price Equal To 300, Discount Over 0, First Barcode Character Not Equal To 0
        List<Item> itemListCase6 = new ArrayList<>();
        itemListCase6.add(new Item("Item1", "1123456789", 300, 0.1f));
        assertTrue(SILab2.checkCart(itemListCase6, 1000), "Expected true");

        //8. Price Equal To 300, Discount Equal To 0, First Barcode Character Equal To 0
        List<Item> itemListCase7 = new ArrayList<>();
        itemListCase7.add(new Item("Item1", "0123456789", 300, 0));
        assertTrue(SILab2.checkCart(itemListCase7, 1000), "Expected true");
    }
}