package lotto.view;

import lotto.utils.ExceptionConstants;
import lotto.utils.MessageConstants;
import lotto.view.provider.InputProvider;

public class InputView {
    private final InputProvider inputProvider;

    public InputView(InputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }

    public int getPrice() {
        try {
            System.out.println(MessageConstants.PURCHASE_GUIDE_MESSAGE.getMessage());
            return Integer.parseInt(inputProvider.readLine());
        } catch (Exception e) {
            throw new IllegalArgumentException(ExceptionConstants.INVALID_PRICE.getMessage());
        }
    }

    public String getLottoNumber() {
        try {
            System.out.println(MessageConstants.LOTTO_NUMBER_GUIDE_MESSAGE.getMessage());
            return inputProvider.readLine();
        } catch (Exception e) {
            throw new IllegalArgumentException(ExceptionConstants.INVALID_LOTTO_NUMBER_FORM.getMessage());
        }
    }

    public int getBonusNumber() {
        try {
            System.out.println(MessageConstants.BONUS_NUMBER_GUIDE_MESSAGE.getMessage());
            return Integer.parseInt(inputProvider.readLine());
        } catch (Exception e) {
            throw new IllegalArgumentException(ExceptionConstants.INVALID_BONUS_NUMBER_FORM.getMessage());
        }
    }
}
