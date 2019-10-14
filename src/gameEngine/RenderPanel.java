package gameEngine;

import helpers.Point;
import javafx.scene.transform.Scale;
import main.MainWindow;
import sun.applet.Main;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel {

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0,0,1000,600);




        for (int i =0 ; i< MainWindow.worlds.get(0).getLocations().toArray().length; i++ ) {
            ArrayList temp = MainWindow.worlds.get(0).getLocations();
            Point a = (Point) temp.get(i);

            if (a.getVal()== World.Wall) {
                g.setColor(Color.white);
                g.fillRect(a.getPosx(), a.getPosy(), MainWindow.SCALE, MainWindow.SCALE);
            }
            if (a.getVal()== World.Nibble) {
                g.setColor(Color.blue);
                g.fillRect(a.getPosx()+10, a.getPosy()+10, 10, 10);
            }
            if (a.getVal()== World.Empty) {
                g.setColor(Color.black);
                g.fillRect(a.getPosx(), a.getPosy(), MainWindow.SCALE, MainWindow.SCALE);
            }

        }



        g.setColor(Color.yellow);
        g.fillRect(MainWindow.pacmans.get(0).getPosx()+3, MainWindow.pacmans.get(0).getPosy()+3, 24, 24);

        /**

        for (int i =0 ; i< MainWindow.worlds.get(1).getLocations().toArray().length; i++ ) {
            ArrayList temp = MainWindow.worlds.get(1).getLocations();
            Point a = (Point) temp.get(i);

            if (a.getVal()== World.Wall) {
                g.setColor(Color.white);
                g.fillRect(a.getPosx()+270, a.getPosy(), MainWindow.SCALE, MainWindow.SCALE);
            }
            if (a.getVal()== World.Nibble) {
                g.setColor(Color.blue);
                g.fillRect(a.getPosx()+10+270, a.getPosy()+10, 10, 10);
            }
            if (a.getVal()== World.Empty) {
                g.setColor(Color.black);
                g.fillRect(a.getPosx()+270, a.getPosy(), MainWindow.SCALE, MainWindow.SCALE);
            }

        }

        g.setColor(Color.yellow);
        g.fillRect(MainWindow.pacmans.get(1).getPosx()+3+270, MainWindow.pacmans.get(1).getPosy()+3, 24, 24);

        for (int i =0 ; i< MainWindow.worlds.get(2).getLocations().toArray().length; i++ ) {
            ArrayList temp = MainWindow.worlds.get(2).getLocations();
            Point a = (Point) temp.get(i);

            if (a.getVal()== World.Wall) {
                g.setColor(Color.white);
                g.fillRect(a.getPosx()+540, a.getPosy(), MainWindow.SCALE, MainWindow.SCALE);
            }
            if (a.getVal()== World.Nibble) {
                g.setColor(Color.blue);
                g.fillRect(a.getPosx()+10+540, a.getPosy()+10, 10, 10);
            }
            if (a.getVal()== World.Empty) {
                g.setColor(Color.black);
                g.fillRect(a.getPosx()+540, a.getPosy(), MainWindow.SCALE, MainWindow.SCALE);
            }

        }

        g.setColor(Color.yellow);
        g.fillRect(MainWindow.pacmans.get(2).getPosx()+3+540, MainWindow.pacmans.get(1).getPosy()+3, 24, 24);

        */
    }

}
