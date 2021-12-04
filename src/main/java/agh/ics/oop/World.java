package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

public class World {
    public static void main(String[] args) {
        try {
            System.out.println("Start");

            Application.launch(App.class, args);

            System.out.println("Stop");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        }
    }
}
