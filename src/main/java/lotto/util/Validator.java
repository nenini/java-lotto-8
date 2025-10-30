package lotto.util;

import lotto.global.error.AppException;
import lotto.global.error.ErrorCode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator {
    private Validator() {
    }

    public static void requiredPurchase(int purchase) {
        if (purchase < 1000 || purchase % 1000 != 0){
            AppException.throwError(ErrorCode.INVALID_PURCHASE);
        }
    }

    public static void requiredSixNumber(List<Integer> numbers){
        if (numbers.size() != 6){
            AppException.throwError(ErrorCode.NOT_SIX_NUMBERS);
        }
    }

    public static void requiredRange1To45(List<Integer> numbers){
        for(int number : numbers){
            if (number > 45||number < 1){
                AppException.throwError(ErrorCode.OUT_OF_NUMBER_RANGE);
            }
        }
    }

    public static void requiredDistinctNumbers(List<Integer> numbers){
        Set<Integer> set = new HashSet<>(numbers);
        if (set.size() != numbers.size()){
            AppException.throwError(ErrorCode.DUPLICATED_NUMBER);
        }
    }

    public static void requiredBonusRange1To45(int number){
        if (number > 45||number < 1){
            AppException.throwError(ErrorCode.OUT_OF_NUMBER_RANGE);
        }
    }

    public static void requiredBonusNotContainWinningNumbers(List<Integer> numbers,int number){
        if(numbers.contains(number)){
            AppException.throwError(ErrorCode.BONUS_CONFLICT);
        }
    }

}
