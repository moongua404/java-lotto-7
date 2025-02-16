package lotto.model.dto;

import lotto.utils.LottoPrize;

public record WinningDataDto(LottoPrize lottoPrize, int count) {
}
