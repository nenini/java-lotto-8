package lotto;

import lotto.domain.WinningLottoNumber;
import lotto.global.error.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName(value = "WinningNumbers: 메인 6개 + 보너스 검증")
public class WinningLottoNumberTest {

    @Test
    @DisplayName("생성: 메인 6개 유효 + 보너스(1~45, 미중복) → 통과")
    void winningNumberCreateOk() {
        assertThatCode(() -> new WinningLottoNumber(List.of(1, 2, 3, 4, 5, 6), 7))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("생성 실패: 보너스가 메인 번호와 중복이면 예외")
    void winningNumberBonusConflictFail() {
        assertThatCode(() -> new WinningLottoNumber(List.of(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.BONUS_CONFLICT.message());
    }

    @Test
    @DisplayName("생성 실패: 메인 6개 규칙 위반(개수/범위/중복)")
    void winningNumberCreateFail() {
        assertThatThrownBy(() -> new WinningLottoNumber(List.of(1, 2, 3, 4, 5), 7))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.NOT_SIX_NUMBERS.message());

        assertThatThrownBy(() -> new WinningLottoNumber(List.of(0, 2, 3, 4, 5, 6), 7))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.OUT_OF_NUMBER_RANGE.message());

        assertThatThrownBy(() -> new WinningLottoNumber(List.of(1, 1, 2, 3, 4, 5), 7))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.DUPLICATED_NUMBER.message());
    }


}
