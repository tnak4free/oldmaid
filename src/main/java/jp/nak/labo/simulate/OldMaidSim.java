package jp.nak.labo.simulate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import jp.nak.labo.game.trump.obj.Player;
import jp.nak.labo.game.trump.rule.OldMaid;

public class OldMaidSim {

    public static void main(String[] args) {
        try {
            OldMaidSim sim = new OldMaidSim();
            sim.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void execute() {
        IntStream.of(2, 3, 4, 10).forEach(i -> {
            final int loopCnt = 1000;
            int turnSum = 0;
            for (int loop = 0; loop < loopCnt; loop++) {
                turnSum += simulate(i);
            }
            System.out.println(String.format("%d人でやった場合の平均ターン回数は、%5f回", i, ((double) turnSum / loopCnt)));
        });

    }

    private int simulate(int playerCnt) {
        List<Player> players = new ArrayList<>();
        IntStream.of(0, playerCnt).forEach(i -> players.add(new Player()));
        OldMaid gameRule = new OldMaid(players);

        int turnIdx = 0;
        gameRule.start();
        if (gameRule.isGameOver()) {
            return turnIdx;
        }
        while (!gameRule.isGameOver()) {
            gameRule.nextTurn();
            turnIdx++;
        }
        return turnIdx;
    }

}
