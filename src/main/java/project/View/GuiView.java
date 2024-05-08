package project.View;

import javafx.scene.Scene;

public interface GuiView {
    /**
     * Loads a scene from a Whack-a-Mole GUI layout.
     *
     * @return the layout
     */
    Scene load() throws IllegalStateException;
}
