package project;

import javafx.application.Application;
import project.Controller.PaintAppController;

public class Driver {


    /**
     * Entry point for a game of Whack-a-Mole.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(PaintAppController.class, args);
    }


}
