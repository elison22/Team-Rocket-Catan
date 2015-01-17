package model;

import model.board.BoardManager;
import model.cards.GameBank;
import model.player.PlayerManager;

import java.util.List;

/**
 * Created by brandt on 1/17/15.
 */
public class Game {

    ModeEnum currentMode;
    int currentTurn = 0;
    BoardManager boardMan;
    PlayerManager playerMan;
    GameBank cardBank;

}
