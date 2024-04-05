package controller;

import domain.Lotto;
import domain.Lottos;
import domain.WinningLotto;
import enumeration.Rank;

import java.util.List;

public final class LottoGame {
    private final Lottos lottos;
    private List<Rank> ranks;

    private LottoGame(Lottos lottos) {
        this.lottos = lottos;
    }

    public static LottoGame of(List<Lotto> auto) {
        return new LottoGame(Lottos.of(auto));
    }

    public void start(List<Integer> numbers, int bonus) {
        this.ranks = this.lottos.scratch(WinningLotto.of(numbers, bonus));
    }

    public Lottos lottos() {
        return lottos;
    }

    public List<Rank> ranks() {
        return ranks;
    }
}
