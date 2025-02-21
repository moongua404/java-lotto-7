package lotto.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utility {
    private static final String SEPARATOR = ",";

    public static <T> long countValue(List<T> items, T target) {
        return items.stream().filter(item -> item.equals(target)).count();
    }

    public static <T> boolean hasDuplicatedValue(List<T> itemList) {
        Set<T> uniqueNames = new HashSet<>(itemList);
        return uniqueNames.size() != itemList.size();
    }

    public static boolean isInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    public static boolean isInRange(List<Integer> values, int min, int max) {
        return !values.stream()
                .map(value -> isInRange(value, min, max))
                .toList()
                .contains(false);
    }

    public static boolean isDividedByThousand(int value) {
        return value % 1000 == 0;
    }

    public static List<Integer> parseInt(String line) {
        return Arrays.stream(line.trim().split(SEPARATOR))
                .map(Integer::parseInt)
                .toList();
    }
}
