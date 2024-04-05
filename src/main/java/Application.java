import controller.LottoGame;
import domain.LottoMachine;

import java.util.List;

import static view.ConsoleView.*;

public final class Application {
    public static void main(String[] args) {
        LottoGame game = lottoGame();
        printBoughtLottosPrompt(game);
        game.start(winningNumbers(), winningBonus());
        printStatistics(game);
    }

    private static LottoGame lottoGame() {
        return LottoGame.of(LottoMachine.autoIssue(getCash()));
    }

    private static List<Integer> winningNumbers() {
        return getWinningNumbers();
    }

    private static int winningBonus() {
        return getWinningBonus();
    }
}
