package lotto.utils;

public enum MessageConstants {
    PURCHASE_GUIDE_MESSAGE("구입금액을 입력해 주세요."),
    PURCHASE_AMOUNT_MESSAGE("%d개를 구매했습니다."),
    LOTTO_NUMBER_GUIDE_MESSAGE("당첨 번호를 입력해 주세요."),
    BONUS_NUMBER_GUIDE_MESSAGE("보너스 번호를 입력해 주세요."),
    WINNING_RESULTS_MESSAGE("당첨 통계\n---"),
    WINNING_NUMBER_MESSAGE("%s (%,d원) - %d개"),
    RATE_OF_RETURN_MESSAGE("총 수익률은 %.1f%%입니다.");

    private final String message;

    MessageConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
