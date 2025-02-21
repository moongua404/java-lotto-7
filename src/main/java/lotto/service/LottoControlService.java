package lotto.service;

import java.util.List;
import java.util.function.Supplier;
import lotto.model.dto.WinningDataDto;

public interface LottoControlService {
    void buyLotto(int amount, Supplier<List<Integer>> pickFunction);

    public void setWinningNumbers(String line);

    public void setBonusNumber(int bonusNumber);

    public void composeLotto();

    List<WinningDataDto> checkWinning();

    float calculateROI(List<WinningDataDto> winningDataDto, int price);

    List<List<Integer>> getLotto();
}
