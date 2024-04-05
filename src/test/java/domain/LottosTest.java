package domain;

import enumeration.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Lottos 관련 테스트")
public class LottosTest {
    @Test
    void Lottos는_로또_다발_수와_로또_생성_방식을_주입받아_생성() {
        Lottos lottos = Lottos.of(
                List.of(
                        Lotto.of(List.of(1, 2, 3, 4, 5, 6)),
                        Lotto.of(List.of(1, 2, 3, 4, 5, 6)),
                        Lotto.of(List.of(1, 2, 3, 4, 5, 6))
                )
        );
        assertThat(lottos).isNotNull();
        assertThat(lottos.bunch().size()).isEqualTo(3);
        lottos.bunch().forEach(e -> assertThat(e.numbers().size()).isEqualTo(6));
    }

    @Test
    void Lottos의_복권_당첨에_따라_수령금을_반환() {
        Lottos lottos = Lottos.of(
                List.of(
                        Lotto.of(List.of(1, 2, 3, 4, 5, 6)),
                        Lotto.of(List.of(1, 2, 3, 4, 5, 6)),
                        Lotto.of(List.of(1, 2, 3, 4, 5, 6))
                )
        );
        List<Rank> ranks = lottos.scratch(WinningLotto.of(List.of(1, 2, 3, 4, 5, 6), 7));
        assertThat(ranks.stream().mapToLong(Rank::prize).sum()).isEqualTo(Rank.FIRST.prize() * 3);
    }
}
