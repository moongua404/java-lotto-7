package lotto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import lotto.model.Lotto;
import lotto.model.dto.WinningDataDto;
import lotto.utils.LottoPrize;
import lotto.utils.Utility;

public class LottoControlServiceImpl {
    List<Lotto> lottoRepository;

    LottoControlServiceImpl() {
        this.lottoRepository = new ArrayList<>();
    }

    public void buyLotto(int amount, Supplier<List<Integer>> pickFunction) {
        lottoRepository.addAll(
                IntStream.range(0, amount)
                        .mapToObj(i -> new Lotto(pickFunction.get()))
                        .toList()
        );
    }

    public List<WinningDataDto> checkWinning(List<Integer> winningNumbers, int bonusNumber) {
        List<LottoPrize> prizes = lottoRepository.stream()
                .map(lotto -> lotto.checkWinning(winningNumbers, bonusNumber))
                .toList();
        return LottoPrize.getPrizeTypes().stream()
                .map(lottoPrize -> new WinningDataDto(lottoPrize, (int) Utility.countValue(prizes, lottoPrize)))
                .toList();
    }

    public float calculateROI(List<WinningDataDto> winningDataDto, int price) {
        long returnValue = winningDataDto.stream()
                .mapToInt(dto -> dto.lottoPrize().getPrice() * dto.count())
                .sum();
        return (float) (returnValue / price);
    }
}
