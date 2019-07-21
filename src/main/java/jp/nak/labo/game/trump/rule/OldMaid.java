package jp.nak.labo.game.trump.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import jp.nak.labo.game.trump.TrumpMgr;
import jp.nak.labo.game.trump.obj.Player;
import jp.nak.labo.game.trump.obj.TrumpCard;

/**
 * ババ抜き
 *
 */
public class OldMaid {

    private TrumpMgr _trumpMgr;

    private List<Player> _players;
    private List<Player> _clearedPlayers;
    private int _turnIdx = 0;

    public OldMaid(List<Player> players) {
        _players = players;
        _clearedPlayers = new ArrayList<>();
    }

    public void start() {
        if (isGameOver()) {
            return;
        }
        _trumpMgr = new TrumpMgr(true, true);

        int idx = 0;
        TrumpCard card = null;
        while ((card = _trumpMgr.draw()) != null) {
            _players.get(idx).deal(card);
            idx = (idx == _players.size() - 1) ? 0 : idx + 1;
        }

        // 開始直後のペア（クリアするとプレイヤリスト減るのでビューで回す）
        List<Player> playerView = new ArrayList<>(_players);
        for (Player player : playerView) {
            poolPairCards(player);
        }
    }

    public void nextTurn() {
        if (isGameOver()) {
            return;
        }

        Player from = _players.get(_turnIdx);
        Player to = _players.get(_turnIdx == (_players.size() - 1) ? 0 : _turnIdx + 1);
        drawFromOther(from, to);
    }

    public void poolPairCards(Player player) {
        if (isGameOver()) {
            return;
        }
        // 同じ数字でグルーピング
        Map<Integer, List<TrumpCard>> groupCards = player.getCards().stream()
                .collect(Collectors.groupingBy(TrumpCard::getNumber));

        // 2ペアずつ捨てる
        groupCards.values().forEach(new Consumer<List<TrumpCard>>() {
            @Override
            public void accept(List<TrumpCard> cards) {
                int pairCnt = cards.size() / 2;
                for (int i = 0; i < pairCnt * 2; i++) {
                    player.removeCard(cards.get(i));
                    _trumpMgr.pool(cards.get(i));
                }
            }
        });

        if (isClearGame(player)) {
            _clearedPlayers.add(_players.remove(_players.indexOf(player)));
        }
    }

    public void drawFromOther(Player from, Player to) {
        if (isGameOver()) {
            return;
        }
        to.deal(from.draw());
        // 引かれて０になるパターンがある
        poolPairCards(from);
        poolPairCards(to);
    }

    public boolean isClearGame(Player player) {
        return player.getCards().size() == 0;
    }

    public boolean isGameOver() {
        return _players.size() <= 1;
    }
}
