package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Lotto {
    public static final int LENGTH = 6;
    public static final int PRICE = 1000;
    public static final int BEGIN = 1;
    public static final int END = 45;
    public static final int RANK_BEGIN = 1;
    public static final int RANK_END = 5;
    public static final int RANK_USING_BONUS = 2;

    private final List<LottoNumber> numbers;

    private Lotto(List<Integer> values) {
        validateValues(values);
        this.numbers = values.stream().map(LottoNumber::of).sorted().collect(Collectors.toUnmodifiableList());
    }

    public static Lotto of(List<Integer> values) {
        return new Lotto(values);
    }

    private void validateValues(List<Integer> values) {
        Objects.requireNonNull(values);
        if (new HashSet<>(values).size() != values.size()) {
            throw new IllegalStateException("중복된 숫자는 로또 숫자로 입력될 수 없습니다.");
        }
    }

    public List<LottoNumber> numbers() {
        return numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
