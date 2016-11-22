package game.java.memory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import game.java.memory.containers.GetGame;
import game.java.memory.containers.GetGameScore;
import game.java.memory.containers.MakeMove;

public class WebAPI {

    private static String HOME = "http://10.0.2.2:9999/MemoryService_war_exploded/MemoryService/";
    private static String GETGAME = HOME + "GetGame";
    private static String GETACTIVEPLAYER = HOME + "GetActivePlayer";
    private static String GETGAMESCORE = HOME + "GetGameScore";
    private static String GETNOTSHOWNMOVES = HOME + "GetNotShownMoves";
    private static String MAKEMOVE = HOME + "MakeMove";


    public static GetGame getGame(int gameId) throws JSONException, ExecutionException, InterruptedException {
        JSONParser jsonParser = new JSONParser();
        JSONObject returnedValues = new AsyncCaller().execute(GETGAME, String.valueOf(gameId)).get();
        GetGame getGame = new GetGame(returnedValues.getInt(GetGame.GAMEID),returnedValues.getInt(GetGame.PLAYERNUMBER));
        return getGame;
    }

    public static GetGameScore getGameScore(int gameId) throws JSONException, ExecutionException, InterruptedException {
        JSONParser jsonParser = new JSONParser();
        JSONObject returnedValues = new AsyncCaller().execute(GETGAMESCORE, String.valueOf(gameId)).get();
        GetGameScore getGameScore = new GetGameScore(returnedValues.getInt(GetGameScore.PLAYER1SCORE),returnedValues.getInt(GetGameScore.PLAYER2SCORE));
        return getGameScore;
    }

    public static MakeMove makeMove(int userId, int gameId, int x1, int y1, int x2, int y2) throws JSONException, ExecutionException, InterruptedException {
        JSONParser jsonParser = new JSONParser();
        JSONObject returnedValues = new AsyncCaller().execute(
                MAKEMOVE,
                String.valueOf(userId),
                String.valueOf(gameId),
                String.valueOf(x1),
                String.valueOf(y1),
                String.valueOf(x2),
                String.valueOf(y2)).get();
        MakeMove makeMove = new MakeMove(
                returnedValues.getInt(MakeMove.X1),
                returnedValues.getInt(MakeMove.Y1),
                returnedValues.getInt(MakeMove.X2),
                returnedValues.getInt(MakeMove.Y2),
                returnedValues.getInt(MakeMove.VALUE1),
                returnedValues.getInt(MakeMove.VALUE2));
        return makeMove;
    }

}
