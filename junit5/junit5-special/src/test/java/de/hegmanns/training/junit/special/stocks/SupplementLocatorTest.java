package de.hegmanns.training.junit.special.stocks;

import de.hegmanns.training.junit5.special.stocks.Exchange;
import de.hegmanns.training.junit5.special.stocks.Order;
import de.hegmanns.training.junit5.special.stocks.Stock;
import de.hegmanns.training.junit5.special.stocks.validator.Supplement;
import de.hegmanns.training.junit5.special.stocks.validator.SupplementLocator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SupplementLocatorTest {

    private SupplementLocator locator;

    @BeforeEach
    public void beforeAnyTest() {
        locator = new SupplementLocator();
    }

//    @Test
//    public void possibleSupplementsForOrderForExpireTodayForTypeAInOnlyOneTest() {
//        Exchange xetra = new Exchange();
//        xetra.setId("123");
//        xetra.setExchangeName("XETRA");
//
//        Stock anyStock = new Stock();
//        anyStock.setStockName("anyName");
//        anyStock.setXetraType("A");
//
//        Order anyOrderExpireToday = new Order();
//        anyOrderExpireToday.setExpireDate(LocalDate.now());
//        anyOrderExpireToday.setExchange(xetra);
//        anyOrderExpireToday.setStock(anyStock);
//
//        Set<Supplement> expectedSupplements = Arrays.stream(Supplement.values())
//                .filter((s) -> s != Supplement.STOP_LIMIT).filter((s) -> s != Supplement.AUCTION_ONLY).filter((s) -> s != Supplement.FILL_OR_KILL)
//                .forEach((s) -> Assertions.assertThat(locator.getPossibleSupplements(anyOrderExpireToday)).contains(s));
//    }



    @ParameterizedTest
    @EnumSource(Supplement.class)
    public void possibleSupplementsForOrderForExpireTodayForTypeA(Supplement supplement) {
        Exchange xetra = new Exchange();
        xetra.setId("123");
        xetra.setExchangeName("XETRA");

        Stock anyStock = new Stock();
        anyStock.setStockName("anyName");
        anyStock.setXetraType("A");

        Order anyOrderExpireToday = new Order();
        anyOrderExpireToday.setExpireDate(LocalDate.now());
        anyOrderExpireToday.setExchange(xetra);
        anyOrderExpireToday.setStock(anyStock);

        Assumptions.assumeFalse(supplement == Supplement.STOP_LIMIT, "Isn't possible for Type A");
        Assumptions.assumeFalse(supplement == Supplement.AUCTION_ONLY, "Isn't possible for Type A");
        Assumptions.assumeFalse(supplement == Supplement.FILL_OR_KILL, "Isn't possible for Type A");

        Assertions.assertThat(locator.getPossibleSupplements(anyOrderExpireToday)).containsAnyOf(supplement);
    }

    @ParameterizedTest
    @EnumSource(Supplement.class)
    public void NoPossibleSupplementsForOrderForExpireTodayForTypeA(Supplement supplement) {
        Exchange xetra = new Exchange();
        xetra.setId("123");
        xetra.setExchangeName("XETRA");

        Stock anyStock = new Stock();
        anyStock.setStockName("anyName");
        anyStock.setXetraType("A");

        Order anyOrderExpireToday = new Order();
        anyOrderExpireToday.setExpireDate(LocalDate.now());
        anyOrderExpireToday.setExchange(xetra);
        anyOrderExpireToday.setStock(anyStock);

        Assumptions.assumeTrue(supplement != Supplement.LIMIT, "Is possible for Type A");
        Assumptions.assumeTrue(supplement != Supplement.STOP, "Is possible for Type A");
        Assumptions.assumeTrue(supplement != Supplement.AUCTION_ONLY, "Is possible for Type A");
        Assumptions.assumeTrue(supplement != Supplement.FILL_OR_KILL, "Is possible for Type A");

        Assertions.assertThat(locator.getPossibleSupplements(anyOrderExpireToday)).doesNotContain(supplement);
    }

    @ParameterizedTest
    @EnumSource(names = {"LIMIT", "STOP", "AUCTION_ONLY", "FILL_OR_KILL"})
    public void possibleSupplementsForOrderForExpireTodayForTypeAAsStringRepresentation(Supplement supplement) {
        Exchange xetra = new Exchange();
        xetra.setId("123");
        xetra.setExchangeName("XETRA");

        Stock anyStock = new Stock();
        anyStock.setStockName("anyName");
        anyStock.setXetraType("A");

        Order anyOrderExpireToday = new Order();
        anyOrderExpireToday.setExpireDate(LocalDate.now());
        anyOrderExpireToday.setExchange(xetra);
        anyOrderExpireToday.setStock(anyStock);

        Assertions.assertThat(locator.getPossibleSupplements(anyOrderExpireToday)).containsAnyOf(supplement);
    }

    @ParameterizedTest
    @EnumSource(names = {"STOP_LIMIT"})
    public void NoPossibleSupplementsForOrderForExpireTodayForTypeAAsStringRepresentation(Supplement supplement) {
        Exchange xetra = new Exchange();
        xetra.setId("123");
        xetra.setExchangeName("XETRA");

        Stock anyStock = new Stock();
        anyStock.setStockName("anyName");
        anyStock.setXetraType("A");

        Order anyOrderExpireToday = new Order();
        anyOrderExpireToday.setExpireDate(LocalDate.now());
        anyOrderExpireToday.setExchange(xetra);
        anyOrderExpireToday.setStock(anyStock);


        Assertions.assertThat(locator.getPossibleSupplements(anyOrderExpireToday)).doesNotContain(supplement);
    }


}
