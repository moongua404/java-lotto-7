package lotto.utils;

import java.lang.reflect.InvocationTargetException;

public enum ExceptionConstants {
    INDIVISIBLE_PRICE(IllegalArgumentException.class, "1,000원 단위로 입력해 주세요."),
    TOO_BIG_PRICE(IllegalArgumentException.class, "입력한 금액 보다 작은 금액을 입력해 주세요."),
    INVALID_PRICE(IllegalArgumentException.class, "유효한 구입 금액을 입력해 주세요"),
    INVALID_LOTTO_RANGE(IllegalArgumentException.class, "1부터 45 사이의 값을 입력해 주세요."),
    DUPLICATED_LOTTO_NUMBER(IllegalArgumentException.class, "중복되지 않은 번호를 입력해주세요."),
    INVALID_LOTTO_NUMBER_FORM(IllegalArgumentException.class, "당첨 번호 6개를 정확히 입력해주세요. ex)1,2,3,4,5,6"),
    INVALID_BONUS_NUMBER_FORM(IllegalArgumentException.class, "보너스 번호 하나를 정확히 입력해주세요. ex)7");


    private final Class<? extends Exception> exception;
    private final String message;

    ExceptionConstants(Class<? extends Exception> exception, String message) {
        this.exception = exception;
        this.message = "[ERROR] " + message;
    }

    public Exception getException()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return this.exception.getConstructor(String.class).newInstance(message);
    }
}
