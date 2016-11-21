package game.java.memory.containers;


public class GetGameScore {

    public static String PLAYER1SCORE = "Player1Score";
    public static String PLAYER2SCORE = "Player2Score";

    public int player1Score;
    public int player2Score;

    public GetGameScore(int i, int j){
        this.player1Score = i;
        this.player2Score = j;
    }

    @Override
    public String toString(){
        return String.valueOf(player1Score) + " " + String.valueOf(player2Score);
    }
}
