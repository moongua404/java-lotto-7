package lotto.service;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import lotto.model.Lotto;
import lotto.model.LottoRepository;
import lotto.model.LottoRepository.LottoRepositoryBuilder;
import lotto.model.dto.WinningDataDto;

public class LottoControlServiceImpl implements LottoControlService {
    LottoRepositoryBuilder lottoRepositoryBuilder;
    LottoRepository lottoRepository;

    public LottoControlServiceImpl() {
        lottoRepositoryBuilder = new LottoRepositoryBuilder();
    }

    public void buyLotto(int amount, Supplier<List<Integer>> pickFunction) {
        lottoRepositoryBuilder.setPurchasedLotto(
                IntStream.range(0, amount)
                        .mapToObj(i -> new Lotto(pickFunction.get()))
                        .toList()
        );
    }

    public void setWinningNumbers(String line) {
        lottoRepositoryBuilder.setWinningNumber(line);
    }

    public void setBonusNumber(int bonusNumber) {
        lottoRepositoryBuilder.setBonusNumber(bonusNumber);
    }

    public void composeLotto() {
        this.lottoRepository = lottoRepositoryBuilder.build();
    }

    public List<WinningDataDto> checkWinning() {
        return lottoRepository.countGrade();
    }

    public float calculateROI(List<WinningDataDto> winningDataDto, int price) {
        long returnValue = winningDataDto.stream()
                .mapToLong(dto -> (long) dto.lottoPrize().getPrice() * dto.count())
                .sum();
        return ((float) returnValue / price) * 100;
    }

    public List<List<Integer>> getLotto() {
        return lottoRepositoryBuilder.getPurchasedLotto().stream()
                .map(Lotto::getNumbers)
                .toList();
    }
}
