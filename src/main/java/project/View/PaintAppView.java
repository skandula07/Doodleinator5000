package project.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import project.Controller.PaintAppController;
import java.io.IOException;


public class PaintAppView implements GuiView {


    private FXMLLoader loader;

    /**
     * constructs the GUI view
     *
     * @param controller a controller for the whack a mole game
     */
    public PaintAppView(PaintAppController controller) {
        // initialization and location setting omitted for brevity
        this.loader = new FXMLLoader();
        this.loader.setController(controller);
        this.loader.setLocation(getClass().getClassLoader().getResource("doodleinator.fxml"));
    }


    /**
     * Loads a scene from a Whack-a-Mole GUI layout.
     *
     * @return the layout
     */
    @Override
    public Scene load() throws IllegalStateException {
        try {
            return this.loader.load();
        } catch (IOException exc) {
            throw new IllegalStateException();
        }
    }
}