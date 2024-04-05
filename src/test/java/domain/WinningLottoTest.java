package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("WinningLotto 관련 테스트")
public class WinningLottoTest {
    @Test
    void WinningLotto는_Lotto와_LottoNumber를_이용하여_생성() {
        WinningLotto winning = WinningLotto.of(
                List.of(1, 2, 3, 4, 5, 6),
                42
        );
        assertThat(winning).isNotNull();
        assertThat(winning.lotto()).isNotNull();
        assertThat(winning.lotto()).isEqualTo(Lotto.of(List.of(1, 2, 3, 4, 5, 6)));
        assertThat(winning.bonus()).isEqualTo(LottoNumber.of(42));
    }

    @Test
    void Reflection을_이용하여_강제로_null을_주입하여도_검증_로직에_따라_NPE를_발생() throws NoSuchMethodException {
        Constructor<WinningLotto> constructor = WinningLotto.class.getDeclaredConstructor(
                Lotto.class,
                LottoNumber.class
        );
        constructor.setAccessible(true);
        assertThatThrownBy(() -> constructor.newInstance(null, null))
                .isInstanceOf(InvocationTargetException.class);
    }


    @ParameterizedTest
    @MethodSource("generateMatchingData")
    void 숫자_매칭_개수를_확인(Lotto lotto, WinningLotto winningLotto, int matchCount) {
        assertThat(winningLotto.matchCount(lotto)).isEqualTo(matchCount);
    }

    private static Stream<Arguments> generateMatchingData() {
        Lotto lotto = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        return Stream.of(
                Arguments.of(lotto, WinningLotto.of(List.of(1, 7, 8, 9, 10, 11), 42), 1),
                Arguments.of(lotto, WinningLotto.of(List.of(1, 2, 8, 9, 10, 11), 42), 2),
                Arguments.of(lotto, WinningLotto.of(List.of(1, 2, 3, 9, 10, 11), 42), 3),
                Arguments.of(lotto, WinningLotto.of(List.of(1, 2, 3, 4, 10, 11), 42), 4),
                Arguments.of(lotto, WinningLotto.of(List.of(1, 2, 3, 4, 5, 11), 42), 5),
                Arguments.of(lotto, WinningLotto.of(List.of(1, 2, 3, 4, 5, 6), 42), 6)
        );
    }

    @Test
    void Bonus_매치_여부를_확인() {
        Lotto lotto = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        boolean matchBonus = WinningLotto.of(List.of(4, 5, 6, 7, 8, 9), 1).matchBonus(lotto);
        assertThat(matchBonus).isTrue();
    }
}
