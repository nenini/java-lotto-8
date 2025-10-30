package lotto.util;

import lotto.global.error.AppException;
import lotto.global.error.ErrorCode;

public class Validator {
    private Validator() {
    }

    public static void requiredPurchase(int purchase) {
        if (purchase < 1000 || purchase % 1000 != 0){
            AppException.throwError(ErrorCode.INVALID_PURCHASE);
        }
    }
}
