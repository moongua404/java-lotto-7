package lotto.view;

import java.util.List;
import lotto.model.dto.WinningDataDto;
import lotto.utils.MessageConstants;

public class OutputView {
    private final String SEPARATOR = ", ";
    private final String WRAPPER = "[%s]";

    public void printLotto(List<Integer> lottoNumber) {
        List<String> numbers = lottoNumber.stream().map(Object::toString).toList();
        System.out.printf(WRAPPER + "%n", String.join(SEPARATOR, numbers));
    }

    public void printResultGuide(int amount) {
        System.out.printf(MessageConstants.PURCHASE_AMOUNT_MESSAGE.getMessage() + "%n", amount);
    }

    public void printResult(List<WinningDataDto> result) {
        System.out.println(MessageConstants.WINNING_RESULTS_MESSAGE.getMessage());
        result.forEach((data) ->
                System.out.printf(MessageConstants.WINNING_NUMBER_MESSAGE.getMessage() + "%n",
                        data.lottoPrize().getCondition(), data.lottoPrize().getPrice(), data.count())
        );
    }

    public void printRateOfReturn(float rateOfReturn) {
        System.out.printf(MessageConstants.RATE_OF_RETURN_MESSAGE.getMessage() + "%n", rateOfReturn);
    }

    public void printException(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
