/*
 * Copyright 2023 The Bank of New York Mellon.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import bnymellon.codekatas.coffeekata.CoffeeShopOrder;
import bnymellon.codekatas.coffeekata.Item;
import bnymellon.codekatas.coffeekata.beverage.Americano;
import bnymellon.codekatas.coffeekata.beverage.CoffeeDrink;
import bnymellon.codekatas.coffeekata.beverage.Latte;
import bnymellon.codekatas.coffeekata.beverage.Macchiato;
import bnymellon.codekatas.coffeekata.food.Bagel;
import bnymellon.codekatas.coffeekata.food.Cookie;
import bnymellon.codekatas.coffeekata.food.Donut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static bnymellon.codekatas.coffeekata.food.BagelType.EVERYTHING;
import static bnymellon.codekatas.coffeekata.food.CookieType.CHOCOLATE_CHIP;
import static bnymellon.codekatas.coffeekata.food.DonutType.GLAZED;
import static bnymellon.codekatas.coffeekata.food.SpreadType.HERB_GARLIC_CREAM_CHEESE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoffeeShopTest
{
    private List<Item> itemList;
    private CoffeeShopOrder coffeeShopOrder;
    private Bagel bagel1;
    private Cookie cookie1;
    private Donut donut1;

    @BeforeEach
    public void setUp()
    {
        bagel1 = new Bagel(EVERYTHING, HERB_GARLIC_CREAM_CHEESE, true);
        cookie1 = new Cookie(CHOCOLATE_CHIP, true);
        donut1 = new Donut(GLAZED);
        itemList = List.of(bagel1, cookie1, donut1);
        coffeeShopOrder = new CoffeeShopOrder("Emilie", itemList);
    }

    @Test
    public void getFoodItemsForOrderTest()
    {
        List<String> expected = List.of("EVERYTHING bagel with HERB_GARLIC_CREAM_CHEESE",
                "CHOCOLATE_CHIP cookie", "GLAZED donut");
        assertEquals(expected, coffeeShopOrder.getFoodItemsForOrder());
    }

    @Test
    public void generateReceiptTest()
    {
        String expectedReceipt = """
                Bagel: EVERYTHING $2.5
                Cookie: CHOCOLATE_CHIP $1.25
                Donut: GLAZED $1.75
                Total: $5.5""";
        assertEquals(expectedReceipt, coffeeShopOrder.generateReceipt());
    }

    @Test
    public void testBagelRecord()
    {
        Bagel bagel2 = new Bagel(EVERYTHING, HERB_GARLIC_CREAM_CHEESE, true);
        assertTrue(Bagel.class.isRecord());
        assertEquals(bagel1, bagel2);
        assertEquals("Bagel[bagelType=EVERYTHING, spreadType=HERB_GARLIC_CREAM_CHEESE, toasted=true]", bagel1.toString());
    }

    @Test
    public void testBagelGetters()
    {
        assertTrue(bagel1.toasted());
        assertEquals(bagel1.bagelType(), EVERYTHING);
        assertEquals(bagel1.spreadType(), HERB_GARLIC_CREAM_CHEESE);
    }

    @Test
    public void testCookieRecord()
    {
        Cookie cookie2 = new Cookie(CHOCOLATE_CHIP, true);
        assertTrue(Cookie.class.isRecord());
        assertEquals(cookie1, cookie2);
        assertEquals("Cookie[cookieType=CHOCOLATE_CHIP, warmed=true]", cookie1.toString());
    }

    @Test
    public void testCookieGetters()
    {
        assertTrue(cookie1.warmed());
        assertEquals(cookie1.cookieType(), CHOCOLATE_CHIP);
    }

    @Test
    public void testDonutRecord()
    {
        Donut donut2 = new Donut(GLAZED);
        assertTrue(Donut.class.isRecord());
        assertEquals(donut1.donutType(), GLAZED);
        assertEquals(donut1, donut2);
        assertEquals("Donut[donutType=GLAZED]", donut1.toString());
    }

    @Test
    public void testDonutGetters()
    {
        assertEquals(donut1.donutType(), GLAZED);
    }

    @Test
    public void testSealedClasses()
    {
        assertTrue(CoffeeDrink.class.isSealed());
        assertFalse(Americano.class.isSealed());
        assertFalse(Macchiato.class.isSealed());
        assertFalse(Latte.class.isSealed());
    }

    @Test
    public void getDrinkItems()
    {
        List<String> expected = List.of("HOT Americano", "HOT CARAMEL Latte with ALMOND_MILK",
                "HOT VANILLA Macchiato with WHOLE_MILK", "MATCHA Tea");
        assertEquals(expected, coffeeShopOrder.getDrinkForOrder());
    }
}
