package lotto;

import lotto.domain.Lotto;
import lotto.global.error.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // TODO: 추가 기능 구현에 따른 테스트 코드 작성
    @Test
    @DisplayName("Lotto 생성: 6개, 1~45, 중복없음 -> 오름차순으로 생성")
    void lottoSortedCreate(){
        Lotto lotto = new Lotto(List.of(6, 2, 3, 5, 4, 1));
        assertThat(lotto.getNumbers()).containsExactly(1,2,3,4,5,6);
    }

    @Test
    @DisplayName("Lotto 생성 실패: 개수가 6개 아님, 1~45 범위 위반, 중복 -> 예외")
    void lottoCreateFail(){
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))       // size != 6
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.NOT_SIX_NUMBERS.message());

        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 6)))     // out of range
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.OUT_OF_NUMBER_RANGE.message());

        assertThatThrownBy(() -> new Lotto(List.of(1, 1, 2, 3, 4, 5)))     // duplicate
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.DUPLICATED_NUMBER.message());
    }
}
