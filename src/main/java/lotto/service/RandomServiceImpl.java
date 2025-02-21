package lotto.service;

import static lotto.utils.LottoConstants.LOTTO_NUMBER_COUNT;
import static lotto.utils.LottoConstants.LOWER_BOUND_NUMBER;
import static lotto.utils.LottoConstants.UPPER_BOUND_NUMBER;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomServiceImpl implements RandomService {
    public List<Integer> getLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(LOWER_BOUND_NUMBER, UPPER_BOUND_NUMBER, LOTTO_NUMBER_COUNT);
    }
}
