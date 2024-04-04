package controller;

import domain.Lotto;
import domain.Lottos;
import domain.WinningLotto;
import enumeration.Rank;

import java.util.ArrayList;
import java.util.List;

public final class LottoGame {
    private final int manualCount;
    private final int autoCount;
    private final Lottos lottos;
    private List<Rank> ranks;

    private LottoGame(List<Lotto> auto, List<Lotto> manual) {
        validateStock(auto.size(), manual.size());
        this.manualCount = manual.size();
        this.autoCount = auto.size() - manualCount;
        this.lottos = Lottos.of(mergeLottos(auto, manual));
    }

    public static LottoGame of(List<Lotto> auto, List<Lotto> manual) {
        return new LottoGame(auto, manual);
    }

    private void validateStock(int autoCount, int manualCount) {
        if (autoCount < manualCount) {
            throw new IllegalStateException("수동 발급이 불가능한 상태입니다.");
        }
    }

    private List<Lotto> mergeLottos(List<Lotto> auto, List<Lotto> manual) {
        return new ArrayList<>() {{
            addAll(manual);
            addAll(auto.subList(0, autoCount));
        }};
    }

    public void start(List<Integer> numbers, int bonus) {
        this.ranks = this.lottos.scratch(WinningLotto.of(numbers, bonus));
    }

    public int manualCount() {
        return manualCount;
    }

    public int autoCount() {
        return autoCount;
    }

    public Lottos lottos() {
        return lottos;
    }

    public List<Rank> ranks() {
        return ranks;
    }
}
