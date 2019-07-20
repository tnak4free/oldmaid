package jp.nak.labo.game.trump;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jp.nak.labo.game.GameUtil;
import jp.nak.labo.game.trump.obj.TrumpCard;
import jp.nak.labo.game.trump.obj.TrumpCard.TrumpKind;

public class TrumpMgr {

    private List<TrumpCard> _cards = new ArrayList<>();
    private List<TrumpCard> _poolCards = new ArrayList<>();

    private static int JOKER_NUMBER = -1;

    public TrumpMgr(boolean isAddJoker, boolean isShuffle) {
        init(isAddJoker, isShuffle);
    }

    public void init(boolean isAddJoker, boolean isShuffle) {
        for (TrumpKind kind : TrumpKind.values()) {
            if (kind == TrumpKind.JOKER) {
                continue;
            }
            for (int number = 1; number < TrumpCard.MAX_NUMBER + 1; number++) {
                _cards.add(new TrumpCard(kind, number));
            }
        }
        if (isAddJoker) {
            _cards.add(createJoker());
        }
        if (isShuffle) {
            Collections.shuffle(_cards);
        }
    }

    public void pool(TrumpCard... cards) {
        _poolCards.addAll(Arrays.asList(cards));
    }

    public TrumpCard createJoker() {
        return new TrumpCard(TrumpKind.JOKER, getJokerNumber());
    }

    public int getJokerNumber() {
        return JOKER_NUMBER;
    }

    public TrumpCard draw() {
        if (_cards.isEmpty()) {
            return null;
        }

        return _cards.remove(GameUtil.getRandomIdx(_cards.size()));
    }

    public boolean hasCards() {
        return !_cards.isEmpty();
    }

}
