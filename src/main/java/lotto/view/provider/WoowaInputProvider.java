package lotto.view.provider;

import camp.nextstep.edu.missionutils.Console;

public class WoowaInputProvider implements InputProvider {
    @Override
    public String readLine() {
        return Console.readLine();
    }
}
