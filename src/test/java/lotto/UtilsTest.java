package lotto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import lotto.utils.LottoPrize;
import lotto.utils.MessageConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class UtilsTest {
    @Test
    void messageConstantsTest() {
        String result = MessageConstants.PURCHASE_GUIDE_MESSAGE.getMessage();
        assertEquals("구입금액을 입력해 주세요.", result);
    }

    @ParameterizedTest
    @MethodSource("provideLottoNumber")
    void lottoPrizeTest(int matchCount, boolean matchBonus, LottoPrize prize) {
        assertEquals(prize, LottoPrize.getLottoPrize(matchCount, matchBonus));
    }

    private static Stream<Arguments> provideLottoNumber() {
        return Stream.of(
                Arguments.of(6, false, LottoPrize.FIRST_PRICE),
                Arguments.of(6, true, LottoPrize.SECOND_PRICE),
                Arguments.of(5, true, LottoPrize.THIRD_PRICE),
                Arguments.of(4, true, LottoPrize.FOURTH_PRICE),
                Arguments.of(3, true, LottoPrize.FIFTH_PRICE),
                Arguments.of(2, true, LottoPrize.NOTHING)
        );
    }
}
