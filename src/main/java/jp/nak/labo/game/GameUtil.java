package jp.nak.labo.game;

import java.util.Random;

public class GameUtil {
    private static Random rnd = new Random((long) Math.random());

    public static int getRandomIdx(int size) {
        return rnd.nextInt(size);
    }

}
