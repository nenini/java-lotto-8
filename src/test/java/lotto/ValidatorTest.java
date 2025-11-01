package lotto;

import lotto.global.error.ErrorCode;
import lotto.util.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("금액/번호 규칙 검증")
public class ValidatorTest {
    @Test
    @DisplayName("구입금액: 1000 이상 & 1000 단위면 통과")
    void purchaseMoneyOk() {
        assertThatCode(() -> Validator.requiredPurchase(8000)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1000, 800, 12345})
    @DisplayName("구입금액: 0, 음수, , 1000 이하, 1000단위 위반의 예외")
    void purchaseMoneyFail(int input) {
        assertThatCode(() -> Validator.requiredPurchase(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.INVALID_PURCHASE.message());
    }

    @Test
    @DisplayName("로또번호: 개수 6, 범위 1~45, 중복없으면 통과")
    void lottoNumberRuleOk() {
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6);
        assertThatCode(() -> {
            Validator.requiredSixNumber(nums);
            Validator.requiredRange1To45(nums);
            Validator.requiredDistinctNumbers(nums);
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또번호: 개수 6개가 아니면 예외")
    void lottoNumberRuleSixFail() {
        List<Integer> lessNums = List.of(1, 2, 3, 4, 5);
        assertThatCode(() -> Validator.requiredSixNumber(lessNums))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.NOT_SIX_NUMBERS.message());

        List<Integer> moreNums = List.of(1, 2, 3, 4, 5, 6, 7);
        assertThatCode(() -> Validator.requiredSixNumber(moreNums))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.NOT_SIX_NUMBERS.message());
    }

    @Test
    @DisplayName("로또번호: 범위가 1~45이 아니면 예외")
    void lottoNumberRuleRangeFail() {
        List<Integer> nums = List.of(1, 2, 3, 100, 5, 6);
        assertThatCode(() -> Validator.requiredRange1To45(nums))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.OUT_OF_NUMBER_RANGE.message());
    }

    @Test
    @DisplayName("로또번호: 숫자 중복이 있으면 예외")
    void lottoNumberRuleDuplicateFail() {
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 5);
        assertThatCode(() -> Validator.requiredDistinctNumbers(nums))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.DUPLICATED_NUMBER.message());
    }

    @Test
    @DisplayName("보너스번호: 범위 1~45는 통과")
    void lottoBonusNumberRuleOk() {
        assertThatCode(() -> Validator.requiredBonusRange1To45(3)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("보너스번호: 1~45 범위 위반은 예외")
    void lottoBonusNumberRuleRangeFail() {
        assertThatThrownBy(() -> Validator.requiredBonusRange1To45(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.OUT_OF_NUMBER_RANGE.message());
        assertThatThrownBy(() -> Validator.requiredBonusRange1To45(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.OUT_OF_NUMBER_RANGE.message());
    }

    @Test
    @DisplayName("보너스번호: 당첨번호와 중복이면 예외")
    void lottoBonusNumberRuleConflictFail() {
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6);

        assertThatThrownBy(() -> Validator.requiredBonusNotContainWinningNumbers(nums, 5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.BONUS_CONFLICT.message());
    }


}
