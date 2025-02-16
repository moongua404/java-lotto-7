package lotto;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import lotto.model.dto.WinningDataDto;
import lotto.utils.LottoPrize;
import lotto.view.InputView;
import lotto.view.OutputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ViewTest extends NsTest {
    private InputView inputView;
    private OutputView outputView;

    @BeforeEach
    void setUp() {
        inputView = new InputView(new MockInputProvider("1000", "1,2,3,4,5,6", "7"));
        outputView = new OutputView();
    }

    @Nested
    class InputTest {
        @Test
        void priceInputTest() throws Exception {
            int price = inputView.getPrice();
            assertEquals(1000, price);
        }

        @Test
        void lottoNumberInputTest() throws Exception {
            inputView.getPrice();
            String lottoNumber = inputView.getLottoNumber();
            assertEquals("1,2,3,4,5,6", lottoNumber);
        }

        @Test
        void bonusNumberInputTest() throws Exception {
            inputView.getPrice();
            inputView.getLottoNumber();
            int bonusNumber = inputView.getBonusNumber();
            assertEquals(7, bonusNumber);
        }
    }

    @Nested
    class OutputTest {
        @Test
        void lottoNumberOutputTest() throws Exception {
            assertSimpleTest(() -> {
                outputView.printLotto(List.of(1, 2, 3, 4, 5, 6));
                assertThat(output()).contains("[1, 2, 3, 4, 5, 6]");
            });
        }

        @Test
        void resultOutputTest() throws Exception {
            assertSimpleTest(() -> {
                outputView.printResult(List.of(
                        new WinningDataDto(LottoPrize.FIFTH_PRICE, 5),
                        new WinningDataDto(LottoPrize.FOURTH_PRICE, 4),
                        new WinningDataDto(LottoPrize.THIRD_PRICE, 3),
                        new WinningDataDto(LottoPrize.SECOND_PRICE, 2),
                        new WinningDataDto(LottoPrize.FIRST_PRICE, 1)
                ));
                assertThat(output()).contains("""
                        당첨 통계
                        ---
                        3개 일치 (5,000원) - 5개
                        4개 일치 (50,000원) - 4개
                        5개 일치 (1,500,000원) - 3개
                        5개 일치, 보너스 볼 일치 (30,000,000원) - 2개
                        6개 일치 (2,000,000,000원) - 1개"""
                );
            });
        }
    }

    @Test
    void ROIOutputTest() throws Exception {
        assertSimpleTest(() -> {
            outputView.printRateOfReturn(62.5f);
            assertThat(output()).contains("총 수익률은 62.5%입니다.");
        });
    }

    @Override
    public void runMain() {
    }
}
