package game.java.memory.containers;


public class MakeMove {
    public static String X1 = "x1";
    public static String Y1 = "y1";
    public static String X2 = "x2";
    public static String Y2 = "y2";
    public static String VALUE1 = "value1";
    public static String VALUE2 = "value2";

    public int x1;
    public int y1;
    public int x2;
    public int y2;
    public int value1;
    public int value2;

    public MakeMove(int x1, int y1, int x2, int y2, int value1, int value2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public String toString(){
        return  String.valueOf(value1) + " " +
                String.valueOf(value2) + " " +
                String.valueOf(x1) + " " +
                String.valueOf(y1) + " " +
                String.valueOf(x2) + " " +
                String.valueOf(y2);
    }
}
