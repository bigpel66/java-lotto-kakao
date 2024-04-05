package domain;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonus;
    private final Set<Integer> lottoNumbers;

    private WinningLotto(Lotto lotto, LottoNumber bonus) {
        validateRequiredArguments(lotto, bonus);
        this.lotto = lotto;
        this.bonus = bonus;
        this.lottoNumbers = lotto.numbers().stream().map(LottoNumber::value).collect(Collectors.toSet());
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
        return lotto.numbers().stream().filter(this::containsNumber).mapToInt(e -> 1).sum();
    }

    private boolean containsNumber(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber.value());
    }

    public boolean matchBonus(Lotto lotto) {
        Optional<LottoNumber> lottoNumber = lotto.numbers().stream().filter(e -> e.equals(bonus)).findFirst();
        return lottoNumber.isPresent();
    }
}
