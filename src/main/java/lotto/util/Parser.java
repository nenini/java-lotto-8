package lotto.util;

import lotto.global.error.AppException;
import lotto.global.error.ErrorCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private Parser() {
    }

    public static int parseInt(String rawInput) {
        String trimmedRawInput = checkBasicInput(rawInput);

        try {
            return Integer.parseInt(trimmedRawInput);
        } catch (NumberFormatException e) {
            AppException.throwError(ErrorCode.NOT_INTEGER);
            throw e;
        }
    }

    public static List<Integer> parseIntList(String rawInput) {
        String trimmedRawInput = checkBasicInput(rawInput);

        List<String> tokens = Arrays.asList(trimmedRawInput.split(",", -1));
        List<Integer> result = new ArrayList<>(tokens.size());

        for (String token : tokens) {
            result.add(parseInt(token));
        }

        return result;
    }


    private static void checkInputNotNull(String rawInput) {
        if (rawInput == null) {
            AppException.throwError(ErrorCode.NULL_INPUT);
        }
    }

    private static void checkInputNotEmpty(String input) {
        if (input.isEmpty()) {
            AppException.throwError(ErrorCode.EMPTY_INPUT);
        }
    }

    private static String checkBasicInput(String input) {
        checkInputNotNull(input);
        String trimmedInput = input.trim();
        checkInputNotEmpty(trimmedInput);

        return trimmedInput;
    }
}
