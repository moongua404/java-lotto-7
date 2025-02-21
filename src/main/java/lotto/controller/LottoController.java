package lotto.controller;

import static lotto.utils.LottoConstants.LOTTO_PRICE;

import java.util.List;
import lotto.model.dto.WinningDataDto;
import lotto.service.LottoControlService;
import lotto.service.RandomService;
import lotto.utils.ExceptionConstants;
import lotto.utils.Utility;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    InputView inputView;
    OutputView outputView;
    LottoControlService lottoControlService;
    RandomService randomService;
    ErrorHandler errorHandler;

    private static final int MAX_ATTEMPTS = 100;

    public LottoController(InputView inputView, OutputView outputView, LottoControlService lottoControlService,
                           RandomService randomService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoControlService = lottoControlService;
        this.randomService = randomService;
        errorHandler = new ErrorHandler();
    }

    public void run() {
        try {
            int amount = inputPrice();
            inputLottoNumber();
            outputLottoData(amount);
            inputBonusNumber();
            composeLottoSystem();
            printResult(amount);
        } catch (Exception e) {
            outputView.printException(e);
        }
    }

    private int inputPrice() {
        while (true) {
            if (errorHandler.willGenerateError()) {
                throw new IllegalStateException(ExceptionConstants.INTERNAL_SERVER_ERROR.getMessage());
            }
            try {
                int price = inputView.getPrice();
                validatePrice(price);
                lottoControlService.buyLotto(price / LOTTO_PRICE, randomService::getLottoNumbers);
                errorHandler.resetAttemptCount();
                return price / LOTTO_PRICE;
            } catch (Exception e) {
                outputView.printException(e);
            }
        }
    }

    private void validatePrice(int price) {
        if (!Utility.isDividedByThousand(price)) {
            throw new IllegalArgumentException(ExceptionConstants.INDIVISIBLE_PRICE.getMessage());
        }
    }

    private void outputLottoData(int amount) {
        outputView.printResultGuide(amount);
        lottoControlService.getLotto().forEach(lotto -> outputView.printLotto(lotto));
    }

    private void inputLottoNumber() {
        while (true) {
            if (errorHandler.willGenerateError()) {
                throw new IllegalStateException(ExceptionConstants.INTERNAL_SERVER_ERROR.getMessage());
            }
            try {
                String line = inputView.getLottoNumber();
                lottoControlService.setWinningNumbers(line);
                errorHandler.resetAttemptCount();
                break;
            } catch (Exception e) {
                outputView.printException(e);
            }
        }
    }

    private void inputBonusNumber() {
        while (true) {
            if (errorHandler.willGenerateError()) {
                throw new IllegalStateException(ExceptionConstants.INTERNAL_SERVER_ERROR.getMessage());
            }
            try {
                int bonusNumber = inputView.getBonusNumber();
                lottoControlService.setBonusNumber(bonusNumber);
                errorHandler.resetAttemptCount();
                break;
            } catch (Exception e) {
                outputView.printException(e);
            }
        }
    }

    private void composeLottoSystem() {
        lottoControlService.composeLotto();
    }

    private void printResult(int amount) {
        List<WinningDataDto> lottoResult = lottoControlService.checkWinning();
        outputView.printResult(lottoResult);
        float result = lottoControlService.calculateROI(lottoResult, amount * LOTTO_PRICE);
        outputView.printRateOfReturn(result);
    }

    static class ErrorHandler {
        private int attempts;

        ErrorHandler() {
            this.attempts = 0;
        }

        boolean willGenerateError() {
            attempts++;
            if (attempts > MAX_ATTEMPTS) {
                return true;
            }
            return false;
        }

        void resetAttemptCount() {
            attempts = 0;
        }
    }
}
