package gameEngine;

import genetics.DNA;
import helpers.Point;
import main.MainWindow;
import neuralNetwork.NeuralNet;

public class Pacman {

    private double score;
    private int posx, posy, direction;
    private boolean dead;
    public static final int UP = 0, RIGHT =1, DOWN =2, LEFT = 3;
    public float hue;
    public World world;

    public static final int FOVDIVISIONS = 1;
    public static final int FIRSTSTAGESIZE = FOVDIVISIONS * World.worldSize * World.worldSize ;
    public static final int stageSizes[] = new int[] { FIRSTSTAGESIZE, 100, 125, 60, 30, 4 };
    public static final boolean isNNSymmetric = false;
    public static int dnalength = NeuralNet.calcNumberOfCoeffs(stageSizes, isNNSymmetric) + 1;
    public DNA dna;
    public NeuralNet brainNet;

    private int nibblebonus=10;

    public Pacman (int x, int y, int dir, int sc, boolean d, DNA dna, World w){
        posx = x;
        posy = y;
        direction = dir;
        score = sc;
        dead = d;
        world = w;

        if (dna == null) {
            this.dna = new DNA(true, dnalength);
        } else {
            this.dna = dna;
        }

        brainNet = new NeuralNet(stageSizes);
        reloadFromDNA();
    }



    public void reloadFromDNA() {
        if (isNNSymmetric)
            brainNet.loadCoeffsSymmetrical(this.dna.data);
        else
            brainNet.loadCoeffs(this.dna.data);
        this.hue = (float) this.dna.data[this.dna.data.length - 1] / 256f;
    }


    public void move() {




            for (int i = 0; i < world.getLocations().toArray().length; i++) {
                Point temp = (Point) world.getLocations().get(i);
                if (temp.getPosy() == posy && temp.getPosx() == posx && temp.getVal() == 2) {
                    world.deleteNibble(posx, posy);
                    score+=10;
                    world.setPacman(posx, posy);
                }
            }


            if (direction== UP){
                for (int i = 0; i <  world.getLocations().toArray().length; i++) {
                    Point temp = (Point) world.getLocations().get(i);
                    if (temp.getPosy() == posy - MainWindow.SCALE && temp.getPosx() == posx && temp.getVal() != 1) {
                        world.deletePacman(posx, posy);

                        posy = posy - MainWindow.SCALE;
                        break;
                    }
                }
            }

            if (direction == DOWN){
                for (int i = 0; i <  world.getLocations().toArray().length; i++) {
                    Point temp = (Point) world.getLocations().get(i);
                    if (temp.getPosy() == posy + MainWindow.SCALE && temp.getPosx() == posx && temp.getVal() != 1) {
                        world.deletePacman(posx, posy);
                        posy = posy + MainWindow.SCALE;
                        break;
                    }
                }
            }

            if (direction == RIGHT){
                for (int i = 0; i <  world.getLocations().toArray().length; i++) {
                    Point temp = (Point) world.getLocations().get(i);
                    if (temp.getPosy() == posy && temp.getPosx() == posx + MainWindow.SCALE && temp.getVal() != 1) {
                        world.deletePacman(posx, posy);
                        posx = posx + MainWindow.SCALE;
                        break;}
                }
            }

            if (direction == LEFT){
                for (int i = 0; i <  world.getLocations().toArray().length; i++) {
                    Point temp = (Point) world.getLocations().get(i);
                    if (temp.getPosy() == posy && temp.getPosx() == posx - MainWindow.SCALE && temp.getVal() != 1) {
                        world.deletePacman(posx, posy);
                        posx = posx - MainWindow.SCALE;
                        break; }
                }
            }

            //MainWindow.worlds.get(j).getWorld()[World.dataSize+1] = posx;
            //MainWindow.worlds.get(j).getWorld()[World.dataSize+2] = posy;

            double output[] = brainNet.calc(world.getWorld());

            //System.out.print(output[0] +" "+ output[1]+" " + output[2]+" " + output[3]);

            if (output[0]>output[1] && output[0]>output[2] &&output[0]>output[3]){direction = 0;}
            else if (output[1]>output[0] && output[1]>output[2] &&output[1]>output[3]){direction = 1;}
            else if (output[2]>output[0] && output[2]>output[1] &&output[2]>output[3]){direction = 2;}
            else if (output[3]>output[0] && output[3]>output[1] &&output[3]>output[2]){direction = 3;}

            //System.out.print(direction);




    }


    public void SetDirection(int i){
        direction = i;
    }

    public int getPosx() {return posx;}
    public int getPosy() {return posy;}
    public int getDirection() {return direction;}
    public double getScore() {return score;}
    public boolean isDead() {return dead;}


    public void UpdateScore(double x) {
        if (score > 0) {
            score = score + x;
        }
    }


}
