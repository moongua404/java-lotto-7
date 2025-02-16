package lotto.service;

import java.util.List;
import lotto.model.dto.WinningDataDto;

public interface LottoControlService {
    void buyLotto(int amount);

    List<WinningDataDto> checkWinning(List<Integer> winningNumbers, int bonusNumber);

    float calculateROI(List<WinningDataDto> winningDataDto);
}
