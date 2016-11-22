package game.java.memory.containers;

public class GetGame {

    public static String GAMEID = "GameId";
    public static String PLAYERNUMBER = "PlayerNo";

    public int gameId;
    public int playerNumber;

    public GetGame(int g, int pn){
        this.gameId = g;
        this.playerNumber = pn;
    }

    @Override
    public String toString(){
        return  String.valueOf(gameId) + " " + String.valueOf(playerNumber);
    }
}
