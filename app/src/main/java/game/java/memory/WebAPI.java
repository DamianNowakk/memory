package game.java.memory;

import android.app.ProgressDialog;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import game.java.memory.containers.GetActivePlayer;
import game.java.memory.containers.GetGame;
import game.java.memory.containers.GetGameScore;
import game.java.memory.containers.GetNoShownMoves;
import game.java.memory.containers.MakeMove;

public class WebAPI {

    public static String HOME = "http://192.168.0.2:8080/MemoryService_war_exploded/MemoryService/";
    private static String GETGAME = HOME + "GetGame";
    private static String GETACTIVEPLAYER = HOME + "GetActivePlayer";
    private static String GETGAMESCORE = HOME + "GetGameScore";
    private static String GETNOTSHOWNMOVES = HOME + "GetNotShownMoves";
    private static String MAKEMOVE = HOME + "MakeMove";


    public static GetGame getGame(int gameId) throws JSONException, ExecutionException, InterruptedException {
        JSONObject returnedValues = new AsyncCaller().execute(GETGAME, String.valueOf(gameId)).get();
        GetGame getGame = new GetGame(returnedValues.getInt(GetGame.GAMEID),returnedValues.getInt(GetGame.PLAYERNUMBER));
        return getGame;
    }

    public static GetGameScore getGameScore(int gameId) throws JSONException, ExecutionException, InterruptedException {
        JSONObject returnedValues = new AsyncCaller().execute(GETGAMESCORE, String.valueOf(gameId)).get();
        GetGameScore getGameScore = new GetGameScore(returnedValues.getInt(GetGameScore.PLAYER1SCORE),returnedValues.getInt(GetGameScore.PLAYER2SCORE));
        return getGameScore;
    }

    public static MakeMove makeMove(int userId, int gameId, int x1, int y1, int x2, int y2) throws JSONException, ExecutionException, InterruptedException {
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

    public static GetActivePlayer getActivePlayer(int gameId) throws JSONException, ExecutionException, InterruptedException {
        JSONObject returnedValues = new AsyncCaller().execute(GETACTIVEPLAYER, String.valueOf(gameId)).get();
        GetActivePlayer getActivePlayer = new GetActivePlayer(returnedValues.getInt(GetActivePlayer.PLAYERID));
        return getActivePlayer;
    }

    public static List<GetNoShownMoves> getNotShownMoves(int gameId) throws JSONException, ExecutionException, InterruptedException {
        JSONArray returnedValues = new AsyncCaller2().execute(GETNOTSHOWNMOVES, String.valueOf(gameId)).get();
        List<GetNoShownMoves> getNoShownMovesList = new ArrayList<>();
        for(int i = 0; i < returnedValues.length(); i++){
            GetNoShownMoves getNoShownMoves = new GetNoShownMoves(
                    returnedValues.getJSONObject(i).getInt(GetNoShownMoves.X1),
                    returnedValues.getJSONObject(i).getInt(GetNoShownMoves.Y1),
                    returnedValues.getJSONObject(i).getInt(GetNoShownMoves.X2),
                    returnedValues.getJSONObject(i).getInt(GetNoShownMoves.Y2),
                    returnedValues.getJSONObject(i).getInt(GetNoShownMoves.VALUE1),
                    returnedValues.getJSONObject(i).getInt(GetNoShownMoves.VALUE2)
                    );
            getNoShownMovesList.add(getNoShownMoves);
        }

        return getNoShownMovesList;
    }
}
