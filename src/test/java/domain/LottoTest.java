package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Lotto 관련 테스트")
class LottoTest {
    @Test
    void 정수_리스트를_입력_받아_생성() {
        Lotto lotto = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lotto).isNotNull();
    }

    @Test
    void 정수_리스트_입력으로_구성된_Lotto의_값은_정렬된_상태를_유지() {
        Lotto lotto = Lotto.of(List.of(6, 5, 4, 3, 2, 1));
        assertThat(lotto.numbers())
                .isEqualTo(Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::of).collect(Collectors.toList()));
    }

    @Test
    void null이_인자로_들어간_Lotto의_생성은_NPE를_발생() {
        assertThatThrownBy(() -> Lotto.of(null)).isInstanceOf(NullPointerException.class);
    }
}