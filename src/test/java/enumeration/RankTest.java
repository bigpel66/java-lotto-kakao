package enumeration;

import domain.Lotto;
import domain.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Rank 관련 테스트")
class RankTest {
    @ParameterizedTest
    @MethodSource("generateLottoAndWinningLottoArguments")
    void Lotto와_WinningLotto를_이용하여_당첨_결과를_확인(Lotto lotto, WinningLotto winningLotto, Rank rank) {
        assertThat(Rank.of(lotto, winningLotto)).isEqualTo(rank);
    }

    private static Stream<Arguments> generateLottoAndWinningLottoArguments() {
        return Stream.of(
                Arguments.of(
                        Lotto.of(List.of(1, 2, 3, 4, 5, 6)),
                        WinningLotto.of(List.of(1, 2, 3, 4, 5, 6), 7),
                        Rank.FIRST
                ),
                Arguments.of(
                        Lotto.of(List.of(1, 2, 3, 4, 5, 7)),
                        WinningLotto.of(List.of(1, 2, 3, 4, 5, 6), 7),
                        Rank.SECOND
                ),
                Arguments.of(
                        Lotto.of(List.of(1, 2, 3, 4, 5, 8)),
                        WinningLotto.of(List.of(1, 2, 3, 4, 5, 6), 7),
                        Rank.THIRD
                ),
                Arguments.of(
                        Lotto.of(List.of(1, 2, 3, 4, 9, 8)),
                        WinningLotto.of(List.of(1, 2, 3, 4, 5, 6), 7),
                        Rank.FORTH
                ),
                Arguments.of(
                        Lotto.of(List.of(4, 5, 6, 8, 9, 10)),
                        WinningLotto.of(List.of(1, 2, 3, 4, 5, 6), 7),
                        Rank.FIFTH
                ),
                Arguments.of(
                        Lotto.of(List.of(1, 2, 3, 4, 5, 6)),
                        WinningLotto.of(List.of(7, 8, 9, 10, 11, 12), 13),
                        Rank.NONE
                )
        );
    }
}