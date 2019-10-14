package gameEngine;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import com.sun.xml.internal.bind.v2.TODO;
import main.MainWindow;
import helpers.Point;
import sun.applet.Main;

import javax.xml.stream.Location;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.Math.sqrt;

public class World {

    public static final int worldSize = 10;

    public static final int Empty = 0, Wall = 1, Nibble = 2, Pacman = 3;

    private static int[] createmaze(int size) {
        int[] res = new int[size * size];
        for (int i =0; i< size*size; i++){
            res[i] = 2;
        }

        for (int i = 0; i< size; i++){
            res[i] = 1;
        }

        for (int i = (size*size - size); i< size*size; i++){
            res[i] = 1;
        }

        for (int i =0; i< size*size; i++){
            if (i % size == 0 ) {
                res[i] = 1;
            }

            if ((i+1) % size == 0 ){
                res[i]=1;
            }
        }


        return res;
    }

    public static int[] dataTemp = createmaze(worldSize);

    public static int[] data ={1, 1, 1, 1, 1, 1,  1, 1, 1, 1,
            1, 2, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 2, 2, 2, 1, 2, 2, 0, 0, 1,
            1, 2, 2, 2, 1, 2, 2, 2, 2, 1,
            1, 2, 2, 2, 1, 2, 2, 2, 2, 1,
            1, 2, 2, 2, 1, 1, 1, 1, 1, 1,
            1, 2, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 2, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 2, 2, 2, 2, 2, 2, 2, 2, 1,
            1, 1, 1, 1, 1, 1,  1, 1, 1, 1};




    public static int dataSize = data.length;

    private ArrayList Locations;

    public World (int[] array, ArrayList<Point> Loc){
        Locations = Loc;

        int a = 0;
            for (int j = 0; j < sqrt(array.length) ; j++ ){
                for (int k = 0; k < sqrt(array.length); k++){
                    Locations.add(new helpers.Point(k*MainWindow.SCALE, j*MainWindow.SCALE, array[j+k+a] ));
                }
                a= a+((int)sqrt(array.length)-1);
        }
    }



    public double[] getWorld(){
        double[] res = new double[dataSize];
        for (int i = 0; i < Locations.toArray().length; i++){
            Point w = (Point) Locations.get(i);
            res[i] = w.getVal();
        }
        return res;
    }

    public int nibbleCount(){
        int res = 0;
        for (int i = 0; i < getWorld().length; i++){
            if (getWorld()[i] == 2) {
                res++;
            }
        }
        return res;
    }

    public void deleteNibble (int x, int y){
        for (int i = 0; i < Locations.toArray().length; i++) {
            Point temp = (Point) Locations.get(i);
            if (temp.getPosy() == y && temp.getPosx() == x){
                temp.setVal(0);
            }

        }
    }

    public void deletePacman ( int x, int y) {
        for (int i = 0; i < Locations.toArray().length; i++) {
            Point temp = (Point) Locations.get(i);
            if (temp.getPosy() == y && temp.getPosx() == x && temp.getVal() == 3) {
                temp.setVal(0);
            }
        }
    }

    public void setPacman ( int x, int y) {
        for (int i = 0; i < Locations.toArray().length; i++) {
            Point temp = (Point) Locations.get(i);
            if (temp.getPosy() == y && temp.getPosx() == x && temp.getVal() == 0) {
                temp.setVal(3);
            }
        }
    }


    public ArrayList getLocations() {return Locations;}




}


