package jp.nak.labo.game.trump.obj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.nak.labo.game.GameUtil;

public class Player {
    List<TrumpCard> _cards = new ArrayList<>();

    public void deal(TrumpCard card) {
        _cards.add(card);
    }

    public TrumpCard draw() {
        if (_cards.isEmpty()) {
            return null;
        }
        return _cards.remove(GameUtil.getRandomIdx(_cards.size()));
    }

    public void removeCard(TrumpCard... cards) {
        _cards.removeAll(Arrays.asList(cards));
    }

    public List<TrumpCard> getCards() {
        return _cards;
    }

}
