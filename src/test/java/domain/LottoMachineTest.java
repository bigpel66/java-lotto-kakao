package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("LottoMachine 관련 테스트")
public class LottoMachineTest {
    @Test
    void LottoMachine은_로또_수를_통해_로또를_발급() {
        List<Lotto> lottos = LottoMachine.autoIssue(14500);
        assertThat(lottos).isNotNull();
        assertThat(lottos.size()).isEqualTo(14);
    }

    @ParameterizedTest
    @CsvSource(value = {"1000:1", "14500:14", "2200:2"}, delimiter = ':')
    void LottoMachine의_numberOfLotto는_금액으로_살_수_있는_로또_수를_반환(int cash, int bunchSize) {
        assertThat(LottoMachine.autoIssue(cash).size()).isEqualTo(bunchSize);
    }
}
