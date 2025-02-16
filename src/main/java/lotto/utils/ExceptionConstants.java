package lotto.utils;

public enum ExceptionConstants {
    INDIVISIBLE_PRICE("1,000원 단위로 입력해 주세요."),
    TOO_BIG_PRICE("입력한 금액 보다 작은 금액을 입력해 주세요."),
    INVALID_PRICE("유효한 구입 금액을 입력해 주세요"),
    INVALID_LOTTO_RANGE("1부터 45 사이의 값을 입력해 주세요."),
    DUPLICATED_LOTTO_NUMBER("중복되지 않은 번호를 입력해주세요."),
    INVALID_LOTTO_NUMBER_FORM("당첨 번호 6개를 정확히 입력해주세요. ex)1,2,3,4,5,6"),
    INVALID_BONUS_NUMBER_FORM("보너스 번호 하나를 정확히 입력해주세요. ex)7");


    private final String message;

    ExceptionConstants(String message) {
        this.message = "[ERROR] " + message;
    }

    public String getMessage() {
        return message;
    }
}
