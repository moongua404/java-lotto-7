package lotto;

import java.io.IOException;
import lotto.view.provider.InputProvider;

public class MockInputProvider implements InputProvider {
    private final String[] inputs;
    private int index = 0;

    public MockInputProvider(String... inputs) {
        this.inputs = inputs;
    }

    @Override
    public String readLine() throws IOException {
        if (index < inputs.length) {
            return inputs[index++];
        }
        throw new IOException();
    }
}
