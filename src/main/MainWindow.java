package main;

import gameEngine.World;
import gameEngine.Pacman;
import genetics.DNA;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MainWindow implements ActionListener, KeyListener {

    public static final int SCALE = 30, DIM = 10;

    public static double mutationrate = 0.1;
    public double currentMaxFitness = 0;


    int ticks, cycles, generation;

    public static void main(String[] args) {
        new MainWindow();
    }


    private gameEngine.RenderPanel renderPanel;
    private Timer timer = new Timer(1, this);

    public MainWindow() {
        JFrame jframe = new JFrame("PacMan");
        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
        jframe.setSize(1000, 600);
        jframe.setTitle("Neural Net PacMan Genetic Algorithm");
        jframe.setVisible(true);
        jframe.add(renderPanel = new gameEngine.RenderPanel());
        jframe.addKeyListener(this);

        Start();

    }

    public static ArrayList<Pacman> pacmans = new ArrayList<>();
    public static ArrayList<World> worlds = new ArrayList<>();

    private void Start() {
        ticks = 0;
        cycles =0;
        generation=0;


        for (int i = 0; i < DIM; i++) {
            worlds.add(new World(World.data, new ArrayList<>()));
        }
        //worlds.add(new World(World.data, new ArrayList<>()));

        for (int i = 0; i< DIM; i++){
            pacmans.add(new Pacman(60, 60, (int) Math.random()*400, 10, false, new DNA(true, Pacman.dnalength), worlds.get(i)));
        }
        timer.start();
    }

    private void Start2() {
        ticks = 0;
        cycles =0;

        worlds.clear();

        for (int i = 0; i < DIM; i++) {
            worlds.add(new World(World.data, new ArrayList<>()));
        }

        newPacman();
        timer.restart();

        generation++;
        System.out.print(generation);
        System.out.print("\n" );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        renderPanel.repaint();

        ticks++;

        //System.out.print(Pacman.dnalength);

        if (ticks % 1 == 0) {

            for (int i = 0; i < DIM; i++) {
            pacmans.get(i).move();
           }


            for (int i = 0; i < DIM; i++) {
                pacmans.get(i).UpdateScore(-0.02);
            }


            cycles ++;


            //updateDNA();

            /**
            for (int i = 0; i < DIM; i++) {
                if (pacmans.get(i).getScore() > 40) {
                pacmans.get(i).UpdateScore(200);}
            } */


        }





        for (int i = 0; i < DIM; i++) {
            if (pacmans.get(i).world.nibbleCount() == 0) {
                pacmans.get(i).UpdateScore(100);
            }
        }

        /**
        for (int i = 0; i < DIM; i++) {
            System.out.print(pacmans.get(i).getScore());
            System.out.print("\n");
        }
*/

        if (cycles == 200){
            Start2();
        }



    }



    public ArrayList<Pacman> makeMatingpool() {
        ArrayList<Pacman> matingpool = new ArrayList<>();
        double maxscore = 1;
        for (Pacman p : pacmans) {
            if (p.getScore() > maxscore) {
                maxscore = p.getScore();
            }
        }

        //System.out.print(maxscore);
        //System.out.print("\n" );

        for (Pacman p : pacmans) {
            int amount = (int) ((p.getScore()) * 100 / maxscore);
            for (int i = 0; i < amount; i++) {
                matingpool.add(p);
            }
        }

        pacmans.clear();

        return matingpool;
    }

    public ArrayList<Pacman> makeMatingpool2() {
        ArrayList<Pacman> matingpool = new ArrayList<>();
        double maxscore = 1;
        for (Pacman p : pacmans) {
            if (p.getScore() > maxscore) {
                maxscore = p.getScore();
            }
        }

        //System.out.print(maxscore);
        //System.out.print("\n" );

        for (Pacman p : pacmans) {
            int amount = (int) ((p.getScore()) * 100 / maxscore);
            for (int i = 0; i < amount; i++) {
                matingpool.add(p);
            }
        }

        return matingpool;
    }

    public void newPacman(){
        ArrayList<Pacman> matingpool = makeMatingpool();
        for (int i = 0; i < DIM; i++) {
            int idx1 = (int) (Math.random() * matingpool.size());
            int idx2 = (int) (Math.random() * matingpool.size());
            DNA parentA = matingpool.get(idx1).dna;
            DNA parentB = matingpool.get(idx2).dna;
            pacmans.add(new Pacman(60, 60, (int) Math.random()*400, 0, false, parentA.crossover(parentB, mutationrate), worlds.get(i)));
        }

    }

    public void updateDNA () {
        ArrayList<Pacman> matingpool = makeMatingpool2();
        for (int i = 0; i < DIM; i++) {
            int idx1 = (int) (Math.random() * matingpool.size());
            int idx2 = (int) (Math.random() * matingpool.size());
            DNA parentA = matingpool.get(idx1).dna;
            DNA parentB = matingpool.get(idx2).dna;
            pacmans.get(i).dna = parentA.crossover(parentB, mutationrate);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {



    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }

}

