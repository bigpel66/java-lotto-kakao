package domain;

import enumeration.Rank;

import java.util.List;
import java.util.stream.Collectors;

public final class Lottos {
    private final List<Lotto> bunch;

    private Lottos(List<Lotto> lottos) {
        this.bunch = lottos.stream().collect(Collectors.toUnmodifiableList());
    }

    public static Lottos of(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public List<Lotto> bunch() {
        return bunch;
    }

    public List<Rank> scratch(WinningLotto winningLotto) {
        return bunch.stream()
                .map(e -> Rank.of(e, winningLotto))
                .collect(Collectors.toUnmodifiableList());
    }
}
