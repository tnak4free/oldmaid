package jp.nak.labo.game.trump.obj;

/**
 * トランプカード
 *
 */
public class TrumpCard {
    /** 種類 */
    public enum TrumpKind {
        CLUB, SPADE, HEART, DAIAMOND, JOKER
    }

    public static int MAX_NUMBER = 13;

    /** トランプの種類 */
    private TrumpKind _kind;

    /** 1～13, Jokerは-1 */
    private int _number;

    public TrumpCard(TrumpKind kind, int number) {
        _kind = kind;
        _number = number;
    }

    public TrumpKind getKind() {
        return _kind;
    }

    public int getNumber() {
        return _number;
    }

    public boolean isSameNumber(TrumpCard card) {
        return this._number == card._number;
    }

}
