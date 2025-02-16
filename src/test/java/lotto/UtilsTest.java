package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;
import lotto.utils.ExceptionConstants;
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

    @Test
    void exceptionConstantsTest() {
        assertThatThrownBy(ExceptionConstants.INDIVISIBLE_PRICE::getException)
                .isInstanceOf(IllegalArgumentException.class)
                .message().isEqualTo("[ERROR] 1,000원 단위로 입력해 주세요.");
    }

    @ParameterizedTest
    @MethodSource("provideLottoNumber")
    void lottoPrizeTest(List<Integer> winningNumber, List<Integer> lottoNumber, int bonusNumber, LottoPrize prize) {
        assertEquals(prize, LottoPrize.getLottoPrize(winningNumber, lottoNumber, bonusNumber));
    }

    private static Stream<Arguments> provideLottoNumber() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 6), 45, LottoPrize.FIRST_PRICE),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 45), 6, LottoPrize.SECOND_PRICE),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 44), 45, LottoPrize.THIRD_PRICE),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 44, 45), 5, LottoPrize.THIRD_PRICE),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 43, 44), 45, LottoPrize.FOURTH_PRICE),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 42, 43, 44), 45, LottoPrize.FIFTH_PRICE),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 41, 42, 43, 44), 45, LottoPrize.NOTHING),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), List.of(39, 40, 41, 42, 43, 44), 45, LottoPrize.NOTHING)
        );
    }
}
