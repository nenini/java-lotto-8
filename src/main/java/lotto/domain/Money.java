package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class Money {
    private final long moneyValue;

    public Money(long moneyValue) {
        this.moneyValue = moneyValue;
    }

    public static Money of(long total) {
        return new Money(total);
    }

    public static Money totalMoney(Map<Rank, Integer> ranks) {
        long total = 0;
        for (Map.Entry<Rank, Integer> entry : ranks.entrySet()) {
            total += entry.getValue() * entry.getKey().getPrize();
        }
        return Money.of(total);
    }

    public static String ratePrize(Money totalMoney, Money purchaseMoney) {

        BigDecimal total = BigDecimal.valueOf(totalMoney.moneyValue);
        BigDecimal purchase = BigDecimal.valueOf(purchaseMoney.moneyValue);

        BigDecimal percent = total
                .multiply(BigDecimal.valueOf(100))
                .divide(purchase, 1, RoundingMode.HALF_UP);

        return percent.toPlainString() + "%";
    }
}
