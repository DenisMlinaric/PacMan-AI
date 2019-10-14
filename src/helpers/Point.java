package helpers;

public class Point {

    private int posx, posy, val; boolean pacman, ghost;


    public Point(int x, int y, int value){
        posx = x;
        posy = y;
        val = value;
    }

    public int getPosx(){return posx;}
    public int getPosy(){return posy;}
    public int getVal(){return val;}

    public void setVal(int i){val = i;}



}
