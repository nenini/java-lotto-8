package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningLottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@DisplayName("LottoVerify: 발행된 로또와 당첨번호로 등수 집계")
public class LottoVerifyTest {

    private Lotto createLotto(int... nums) {
        List<Integer> list = new ArrayList<>(nums.length);
        for (int n : nums) list.add(n);
        return new Lotto(list);
    }

    @Test
    @DisplayName("각 등수에 해당하는 로또 -> Rank별 개수 집계")
    void verifyRank(){
        // given: 당첨번호 1~6, 보너스 7
        WinningLottoNumber winningLottoNumber = new WinningLottoNumber(List.of(1,2,3,4,5,6),7);

        // FIRST: 6개 일치
        Lotto first = createLotto(1, 2, 3, 4, 5, 6);

        // SECOND: 5개 + 보너스(7)
        Lotto second = createLotto(1, 2, 3, 4, 5, 7);

        // THIRD: 5개 일치 (보너스 미포함)
        Lotto third = createLotto(1, 2, 3, 4, 5, 8);

        // FOURTH: 4개 일치
        Lotto fourth = createLotto(1, 2, 3, 4, 44, 45);

        // FIFTH: 3개 일치
        Lotto fifth = createLotto(1, 2, 3, 40, 41, 42);

        // MISS: 2개 이하
        Lotto miss1 = createLotto(1, 2, 40, 41, 42, 43);
        Lotto miss2 = createLotto(10, 11, 12, 13, 14, 15);

        List<Lotto> bundle =List.of(first,second,third,fourth,fifth,miss1,miss2);

        // when
        LottoVerify verify = new LottoVerify();
        Map<Rank, Integer> verifyCounts = verify.verify(bundle, winningLottoNumber);

        // then
        assertThat(verifyCounts.get(Rank.FIRST)).isEqualTo(1);
        assertThat(verifyCounts.get(Rank.SECOND)).isEqualTo(1);
        assertThat(verifyCounts.get(Rank.THIRD)).isEqualTo(1);
        assertThat(verifyCounts.get(Rank.FOURTH)).isEqualTo(1);
        assertThat(verifyCounts.get(Rank.FIFTH)).isEqualTo(1);

        assertThat(verifyCounts.get(Rank.NONE)).isEqualTo(2);

    }
}
