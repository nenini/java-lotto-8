package lotto.service;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("LottoMachine: 구매 금액만큼 유효한 로또를 발행")
public class LottoMachineTest {
    @Test
    @DisplayName("8000원을 입력하면 8장을 발행하고 각 장은 6개/1~45/중복X/오름차순을 만족")
    void lottoBundleSizeAndValidation() {
        LottoMachine lottoMachine = new LottoMachine();
        List<Lotto> bundle = lottoMachine.createLottoBundle(8000);

        assertThat(bundle.size()).isEqualTo(8);

        for (Lotto lotto : bundle) {
            List<Integer> numbers = lotto.getNumbers();
            assertThat(numbers).hasSize(6);
            assertThat(numbers).allMatch(n -> n >= 1 && n <= 45);
            assertThat(numbers).doesNotHaveDuplicates();
            assertThat(numbers).isSorted();
        }
    }
}
