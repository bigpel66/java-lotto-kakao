package domain;

import java.util.List;
import java.util.Objects;

public final class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonus;

    private WinningLotto(Lotto lotto, LottoNumber bonus) {
        validateRequiredArguments(lotto, bonus);
        this.lotto = lotto;
        this.bonus = bonus;
    }

    public static WinningLotto of(List<Integer> numbers, int bonus) {
        return new WinningLotto(Lotto.of(numbers), LottoNumber.of(bonus));
    }

    private void validateRequiredArguments(Lotto lotto, LottoNumber bonus) {
        Objects.requireNonNull(lotto);
        Objects.requireNonNull(bonus);
    }

    public Lotto lotto() {
        return lotto;
    }

    public LottoNumber bonus() {
        return bonus;
    }

    public int matchCount(Lotto lotto) {
        return (int) this.lotto.numbers().stream().filter(lotto::containsNumber).count();
    }

    public boolean matchBonus(Lotto lotto) {
        return lotto.containsNumber(bonus);
    }
}
