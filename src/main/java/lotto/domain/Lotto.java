package lotto.domain;

import lotto.util.Validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = sortNumbers(numbers);
    }

    private void validate(List<Integer> numbers) {
        Validator.requiredSixNumber(numbers);
        Validator.requiredRange1To45(numbers);
        Validator.requiredDistinctNumbers(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public List<Integer> sortNumbers(List<Integer> numbers) {
        List<Integer> copyNumbers = new ArrayList<>(numbers);
        Collections.sort(copyNumbers);
        return Collections.unmodifiableList(copyNumbers);

    }

}
