package test.model.board;

import model.board.BoardException;
import model.board.HexTile;
import shared.definitions.HexType;
import static org.junit.Assert.*;
import org.junit.*;


/**
 * Created by brandt on 1/28/15.
 */
public class HexTileTest {

    private HexTile hexTile;

    @Test
    public void setDiceNumTest() {

        hexTile = new HexTile(HexType.BRICK);
        boolean passed = true;
        try {
            hexTile.setDiceNum(0);
        } catch (BoardException e) {
            passed = false;
        }
        assertTrue(!passed);
        assertTrue(hexTile.getDiceNum()==-1);

        passed = true;
        try {
            hexTile.setDiceNum(13);
        } catch (BoardException e) {
            passed = false;
        }
        assertTrue(!passed);
        assertTrue(hexTile.getDiceNum()==-1);

        passed = true;
        try {
            hexTile.setDiceNum(2);
            hexTile.setDiceNum(5);
            hexTile.setDiceNum(8);
            hexTile.setDiceNum(12);
        } catch (BoardException e) {
            passed = false;
        }
        assertTrue(passed);
        assertTrue(hexTile.getDiceNum()==12);

    }

    @Test
    public void setTypeTest() {
        hexTile = new HexTile(HexType.BRICK);
        hexTile.setType(null);

    }

}
