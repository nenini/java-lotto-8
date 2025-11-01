package lotto.domain;

import lotto.util.Validator;

import java.util.List;

public class WinningLottoNumber {
    private final List<Integer> numbers;
    private final int bonus;

    public WinningLottoNumber(List<Integer> numbers, int bonus) {
        validateNumbers(numbers);
        validateBonus(numbers, bonus);

        this.numbers = numbers;
        this.bonus = bonus;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getBonus() {
        return bonus;
    }


    public static void validateNumbers(List<Integer> numbers) {
        Validator.requiredSixNumber(numbers);
        Validator.requiredRange1To45(numbers);
        Validator.requiredDistinctNumbers(numbers);
    }

    public static void validateBonus(List<Integer> numbers, int bonus) {
        Validator.requiredBonusRange1To45(bonus);
        Validator.requiredBonusNotContainWinningNumbers(numbers, bonus);
    }


}
