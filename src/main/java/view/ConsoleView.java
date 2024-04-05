package view;

import controller.LottoGame;
import domain.Lotto;
import domain.Lottos;
import enumeration.Rank;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class ConsoleView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int getCash() {
        printCashPrompt();
        return SCANNER.nextInt();
    }

    private static void printCashPrompt() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printBoughtLottosPrompt(LottoGame game) {
        printBunchSizePrompt(game.lottos().bunch().size());
        printLottosNumbersPrompt(game.lottos());
    }

    private static void printBunchSizePrompt(int bunchSize) {
        System.out.println(bunchSize + "개 구매했습니다.");
    }

    private static void printLottosNumbersPrompt(Lottos lottos) {
        StringBuilder sb = new StringBuilder();
        lottos.bunch().stream().map(ConsoleView::lottoToString).forEach(e -> sb.append(e).append("\n"));
        System.out.println(sb);
    }

    private static String lottoToString(Lotto lotto) {
        return lotto.numbers().stream()
                .map(e -> String.valueOf(e.value()))
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public static List<Integer> getWinningNumbers() {
        printWinningNumbersPrompt();
        SCANNER.nextLine();
        String next = SCANNER.nextLine();
        return Arrays.stream(next.split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static void printWinningNumbersPrompt() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static int getWinningBonus() {
        printWinningBonusPrompt();
        return SCANNER.nextInt();
    }

    private static void printWinningBonusPrompt() {
        System.out.println("보너스 볼을 입력 주세요.");
    }

    public static void printStatistics(LottoGame game) {
        printWinningPrompt();
        IntStream.rangeClosed(Lotto.RANK_BEGIN, Lotto.RANK_END).boxed().collect(Collectors.toList())
                .stream()
                .sorted(Collections.reverseOrder())
                .map(e -> getWinningDetailPrompt(game.ranks(), e))
                .forEach(System.out::println);
        printProfitRate(getPrize(game.ranks()), game.lottos().bunch().size() * Lotto.PRICE);
    }

    private static void printWinningPrompt() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    private static String getWinningDetailPrompt(List<Rank> ranks, int rank) {
        return getWinningDetailHead(rank) + "(" + Rank.of(rank).prize() + ")- " + filterRanksCount(ranks, rank) + "개";
    }

    private static String getWinningDetailHead(int rank) {
        if (rank == Lotto.RANK_BEGIN) {
            return Lotto.LENGTH + "개 일치 ";
        }
        if (rank == Lotto.RANK_USING_BONUS) {
            return (Lotto.LENGTH - 1) + "개 일치, 보너스 볼 일치 ";
        }
        return (Lotto.LENGTH - (rank - Lotto.RANK_USING_BONUS) - 1) + "개 일치";
    }

    private static int filterRanksCount(List<Rank> ranks, int rank) {
        return ranks.stream().filter(e -> e.rank() == rank).mapToInt(e -> 1).sum();
    }

    private static double getPrize(List<Rank> ranks) {
        return ranks.stream()
                .mapToLong(Rank::prize)
                .sum();
    }

    private static void printProfitRate(double prize, double cash) {
        System.out.printf("총 수익률은 %.2f 입니다.\n", prize / cash);
    }
}
