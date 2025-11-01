package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
@DisplayName("Rank: 일치 개수와 보너스 여부로 등수 결정")
public class RankTest {
    @Test
    @DisplayName("6개 일치 → 1등")
    void of_first() {
        assertThat(Rank.of(6, false)).isEqualTo(Rank.FIRST);
        assertThat(Rank.of(6, true)).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("5개 일치 + 보너스 → 2등 / 5개만 일치 → 3등")
    void of_second_third() {
        assertThat(Rank.of(5, true)).isEqualTo(Rank.SECOND);
        assertThat(Rank.of(5, false)).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("4개→4등, 3개→5등, 2개 이하는 MISS")
    void of_others() {
        assertThat(Rank.of(4, false)).isEqualTo(Rank.FOURTH);
        assertThat(Rank.of(3, false)).isEqualTo(Rank.FIFTH);
        assertThat(Rank.of(2, false)).isEqualTo(Rank.NONE);
        assertThat(Rank.of(0, false)).isEqualTo(Rank.NONE);
    }
}
