package lotto;

import lotto.domain.Money;
import lotto.domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Money: 총 상금 합계와 수익률(퍼센트) 계산")
public class MoneyTest {

    @Test
    @DisplayName("totalMoney: Rank별 개수*상금의 합을 계산")
    void totalMoneySumByRanks() {
        Map<Rank, Integer> counts = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) counts.put(rank, 0);

        counts.put(Rank.FIRST, 1);   // 2,000,000,000
        counts.put(Rank.THIRD, 2);   // 1,500,000 × 2
        counts.put(Rank.FIFTH, 3);   // 5,000 × 3

        Money money = Money.totalMoney(counts);

        long totalMoney = 2_000_000_000L + 2 * 1_500_000L + 3 * 5_000L;

        assertThat(Money.of(totalMoney)).usingRecursiveComparison().isEqualTo(money);

    }

    @Test
    @DisplayName("ratePrize: 수익률은 퍼센트로, 소수 둘째 자리에서 반올림")
    void ratePrizeTest() {
        String prize1 = Money.ratePrize(Money.of(5_000), Money.of(8_000));
        assertThat(prize1).isEqualTo("62.5%");

        String prize2 = Money.ratePrize(Money.of(1_250), Money.of(1_000));
        assertThat(prize2).isEqualTo("125.0%");

        String prize3 = Money.ratePrize(Money.of(1_000), Money.of(3_000));
        assertThat(prize3).isEqualTo("33.3%");
    }

}
