package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class LottoMachine {
    private static final List<Integer> CANDIDATE_NUMBERS = IntStream.rangeClosed(Lotto.BEGIN, Lotto.END)
            .boxed()
            .collect(Collectors.toList());

    public static List<Lotto> autoIssue(int cash) {
        return IntStream.range(0, bunchSize(cash))
                .mapToObj(e -> Lotto.of(lottoNumbers()))
                .collect(Collectors.toList());
    }

    private static int bunchSize(int cash) {
        return cash / Lotto.PRICE;
    }

    private static List<Integer> lottoNumbers() {
        Collections.shuffle(CANDIDATE_NUMBERS);
        return CANDIDATE_NUMBERS.subList(0, Lotto.LENGTH);
    }
}
