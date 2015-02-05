package test.model.game;

import model.board.BoardException;
import model.game.GameModel;
import org.junit.Before;
import org.junit.Test;
import serializer.Serializer;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Brandt on 2/1/15.
 */
public class GameModelTest {

    GameModel testGame;
    Serializer testSerializer;
    File file;
    Scanner stream;
    StringBuilder builder;
    String json;

    @Before
    public void setup() throws FileNotFoundException, BoardException {
        testGame = new GameModel();
        testSerializer = new Serializer();
    }

    public void initializeModel(String filename) throws BoardException, FileNotFoundException {
        file = new File(System.getProperty("user.dir") + "/JunitJsonFiles/" + filename);
        stream = new Scanner(file);
        builder = new StringBuilder();
        while(stream.hasNext()) builder.append(stream.next());
        json = builder.toString();
        testGame = testSerializer.deSerializeFromServer(testGame, json);
        stream.close();
    }

    @Test
    public void example() throws BoardException, FileNotFoundException {
        initializeModel("Json2.json");
        //do test stuff
    }




}
