import controller.LottoGame;
import domain.LottoMachine;
import view.ConsoleView;

import java.util.List;

public final class Application {
    public static void main(String[] args) {
        LottoGame game = lottoGame();
        ConsoleView.printBoughtLottosPrompt(game);
        game.start(winningNumbers(), winningBonus());
        ConsoleView.printStatistics(game);
    }

    private static LottoGame lottoGame() {
        return LottoGame.of(
                LottoMachine.autoIssue(ConsoleView.getCash()),
                ConsoleView.manualIssue()
        );
    }

    private static List<Integer> winningNumbers() {
        return ConsoleView.getWinningNumbers();
    }

    private static int winningBonus() {
        return ConsoleView.getWinningBonus();
    }
}
