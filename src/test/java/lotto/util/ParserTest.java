package lotto.util;

import lotto.global.error.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

@DisplayName("문자열 → 숫자/숫자리스트 파싱")
public class ParserTest {

    @Test
    @DisplayName("정상 정수/공백 포함 정수는 통과")
    void parseIntOk() {
        assertThat(Parser.parseInt("1000")).isEqualTo(1000);
        assertThat(Parser.parseInt(" 2000 ")).isEqualTo(2000);
    }

    @Test
    @DisplayName("공백 정수 입력은 예외")
    void parseIntBlankFail() {
        assertThatThrownBy(() -> Parser.parseInt(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.EMPTY_INPUT.message());
    }

    @Test
    @DisplayName("비정수 입력은 예외")
    void parseIntNonIntegerFail() {
        assertThatThrownBy(() -> Parser.parseInt("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.NOT_INTEGER.message());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6", "1 ,2, 3,4 ,5 ,6"})
    @DisplayName("쉼표/공백 허용, 6개 정수 파싱 성공")
    void parseIntListOk(String input) {
        assertThat(Parser.parseIntList(input)).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,,3,4,5,6", "1 ,2, ,4,5,6"})
    @DisplayName("6개의 토큰 중 빈 토큰은 예외")
    void parseIntListBlankFail(String input) {
        assertThatThrownBy(() -> Parser.parseIntList(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.EMPTY_INPUT.message());
    }


    @ParameterizedTest
    @ValueSource(strings = {"1,2,a,4,5,6", "1,2,3,4,@,6"})
    @DisplayName("비숫자 토큰은 예외")
    void parseIntListNonIntegerFail(String input) {
        assertThatThrownBy(() -> Parser.parseIntList(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.NOT_INTEGER.message());
    }


}
