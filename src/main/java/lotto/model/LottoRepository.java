package lotto.model;

import static lotto.utils.LottoConstants.NUMBER_SEPARATOR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.model.dto.WinningDataDto;
import lotto.utils.ExceptionConstants;
import lotto.utils.LottoPrize;
import lotto.utils.Utility;

public class LottoRepository {
    private final List<Lotto> purchasedLotto;
    private final List<Integer> winningNumbers;
    private int bonusNumber;

    private LottoRepository(LottoRepositoryBuilder lottoRepositoryBuilder) {
        purchasedLotto = lottoRepositoryBuilder.purchasedLotto;
        winningNumbers = lottoRepositoryBuilder.winningNumbers;
        bonusNumber = lottoRepositoryBuilder.bonusNumber;
    }

    public List<WinningDataDto> countGrade() {
        List<LottoPrize> prizes = purchasedLotto.stream()
                .map(lotto -> lotto.checkWinning(winningNumbers, bonusNumber))
                .toList();
        return LottoPrize.getPrizeTypes().stream()
                .map(lottoPrize -> new WinningDataDto(lottoPrize, (int) Utility.countValue(prizes, lottoPrize)))
                .toList();
    }

    public static class LottoRepositoryBuilder {
        private List<Lotto> purchasedLotto;
        private List<Integer> winningNumbers;
        private Integer bonusNumber;

        public LottoRepositoryBuilder setPurchasedLotto(List<Lotto> purchasedLotto) {
            this.purchasedLotto = new ArrayList<>();
            this.purchasedLotto.addAll(purchasedLotto);
            return this;
        }

        public LottoRepositoryBuilder setWinningNumber(String line) {
            winningNumbers = new ArrayList<>();
            List<Integer> numbers = Arrays.stream(line.split(NUMBER_SEPARATOR))
                    .map(Integer::parseInt)
                    .toList();
            setWinningNumbers(numbers);
            return this;
        }

        private LottoRepositoryBuilder setWinningNumbers(List<Integer> winningNumbers) {
            new Lotto(winningNumbers);
            this.winningNumbers.clear();
            this.winningNumbers.addAll(winningNumbers);
            return this;
        }

        public LottoRepositoryBuilder setBonusNumber(int bonusNumber) {
            Lotto tempLotto = new Lotto(winningNumbers);
            tempLotto.validateAdditionalNumber(bonusNumber);
            this.bonusNumber = bonusNumber;
            return this;
        }

        public LottoRepository build() {
            if (purchasedLotto == null || winningNumbers == null || bonusNumber == null) {
                throw new IllegalStateException(ExceptionConstants.INTERNAL_SERVER_ERROR.getMessage());
            }
            return new LottoRepository(this);
        }

        public List<Lotto> getPurchasedLotto() {
            return purchasedLotto;
        }
    }
}
