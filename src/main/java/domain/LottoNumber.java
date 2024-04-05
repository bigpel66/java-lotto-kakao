package domain;

import java.util.Objects;

public final class LottoNumber implements Comparable<LottoNumber> {
    private final int value;

    private LottoNumber(int value) {
        validateValue(value);
        this.value = value;
    }

    public static LottoNumber of(int value) {
        return new LottoNumber(value);
    }

    private void validateValue(int value) {
        if (value < Lotto.BEGIN || value > Lotto.END) {
            throw new IllegalArgumentException("로또 번호는 " + Lotto.BEGIN + "이상 " + Lotto.END + "이하여야 합니다.");
        }
    }

    public int value() {
        return value;
    }

    @Override
    public int compareTo(LottoNumber number) {
        return Integer.compare(value, number.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber number = (LottoNumber) o;
        return value == number.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
