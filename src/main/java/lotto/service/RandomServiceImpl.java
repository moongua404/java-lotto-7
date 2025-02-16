package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomServiceImpl implements RandomService {
    public List<Integer> getLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }
}
