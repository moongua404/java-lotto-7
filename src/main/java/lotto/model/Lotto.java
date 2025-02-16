package lotto.model;

import static lotto.utils.ExceptionConstants.DUPLICATED_LOTTO_NUMBER;
import static lotto.utils.ExceptionConstants.INVALID_LOTTO_RANGE;

import java.util.List;
import java.util.stream.Stream;
import lotto.utils.LottoPrize;
import lotto.utils.Utility;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        if (Utility.hasDuplicatedValue(numbers)) {
            throw new IllegalArgumentException(DUPLICATED_LOTTO_NUMBER.getMessage());
        }
        if (Utility.isInRange(numbers, 0, 45)) {
            throw new IllegalArgumentException(INVALID_LOTTO_RANGE.getMessage());
        }
    }

    // TODO: 추가 기능 구현
    public LottoPrize checkWinning(List<Integer> winningNumbers, int bonusNumber) {
        List<Integer> totalWinningNumber = Stream.concat(winningNumbers.stream(), Stream.of(bonusNumber)).toList();
        int matchCount = (int) numbers.stream().filter(totalWinningNumber::contains).count();
        boolean matchBonus = numbers.contains(bonusNumber);
        return LottoPrize.getLottoPrize(matchCount, matchBonus);
    }
}
