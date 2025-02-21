package lotto;

import lotto.controller.LottoController;
import lotto.service.LottoControlService;
import lotto.service.LottoControlServiceImpl;
import lotto.service.RandomService;
import lotto.service.RandomServiceImpl;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.provider.InputProvider;
import lotto.view.provider.WoowaInputProvider;

public class AppConfig {
    public InputProvider inputProvider() {
        return new WoowaInputProvider();
    }

    public InputView inputView() {
        return new InputView(inputProvider());
    }

    public OutputView outputView() {
        return new OutputView();
    }

    public LottoControlService lottoControlService() {
        return new LottoControlServiceImpl();
    }

    public RandomService randomService() {
        return new RandomServiceImpl();
    }

    public LottoController lottoController() {
        return new LottoController(
                inputView(),
                outputView(),
                lottoControlService(),
                randomService()
        );
    }
}
