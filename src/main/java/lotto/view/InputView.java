package lotto.view;

import lotto.utils.ExceptionConstants;
import lotto.utils.MessageConstants;
import lotto.view.provider.InputProvider;

public class InputView {
    private final InputProvider inputProvider;

    public InputView(InputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }

    public int getPrice() throws Exception {
        try {
            System.out.println(MessageConstants.PURCHASE_GUIDE_MESSAGE.getMessage());
            return Integer.parseInt(inputProvider.readLine());
        } catch (Exception e) {
            throw ExceptionConstants.INVALID_PRICE.getException();
        }
    }

    public String getLottoNumber() throws Exception {
        try {
            System.out.println(MessageConstants.LOTTO_NUMBER_GUIDE_MESSAGE.getMessage());
            return inputProvider.readLine();
        } catch (Exception e) {
            throw ExceptionConstants.INVALID_LOTTO_NUMBER_FORM.getException();
        }
    }

    public int getBonusNumber() throws Exception {
        try {
            System.out.println(MessageConstants.BONUS_NUMBER_GUIDE_MESSAGE.getMessage());
            return Integer.parseInt(inputProvider.readLine());
        } catch (Exception e) {
            throw ExceptionConstants.INVALID_BONUS_NUMBER_FORM.getException();
        }
    }
}
