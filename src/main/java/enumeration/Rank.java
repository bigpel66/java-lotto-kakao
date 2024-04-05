package enumeration;

import domain.Lotto;
import domain.WinningLotto;

import java.util.Arrays;

public enum Rank {
    FIRST(1, 2_000_000_000),
    SECOND(2, 30_000_000),
    THIRD(3, 1_500_000),
    FORTH(4, 50_000),
    FIFTH(5, 5_000),
    NONE(0, 0),
    ;

    private final int place;
    private final int prize;

    Rank(int place, int prize) {
        this.place = place;
        this.prize = prize;
    }

    public static Rank of(Lotto lotto, WinningLotto winningLotto) {
        int matchCount = winningLotto.matchCount(lotto);
        boolean matchBonus = winningLotto.matchBonus(lotto);
        return Rank.of(Rank.place(matchCount, matchBonus));
    }

    public static Rank of(int place) {
        return Arrays.stream(Rank.values())
                .filter(e -> e.place == place)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("당첨을 판별할 수 없는 순위입니다."));
    }

    private static int place(final int matchCount, final boolean matchBonus) {
        if (isFirstPlace(matchCount)) {
            return 1;
        }
        if (isSecondPlace(matchCount, matchBonus)) {
            return 2;
        }
        if (isOtherPlace(matchCount)) {
            return otherPlace(matchCount);
        }
        return 0;
    }

    private static boolean isFirstPlace(int matchCount) {
        return matchCount == Lotto.LENGTH;
    }

    private static boolean isSecondPlace(int matchCount, boolean matchBonus) {
        return matchCount == Lotto.LENGTH - 1 && matchBonus;
    }

    private static boolean isOtherPlace(int matchCount) {
        return matchCount >= Lotto.LENGTH - Lotto.RANK_USING_BONUS - 1;
    }

    private static int otherPlace(int matchCount) {
        return Lotto.LENGTH - matchCount + Lotto.RANK_USING_BONUS;
    }

    public int rank() {
        return place;
    }

    public long prize() {
        return prize;
    }
}
