package project.Controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;

public class PaintBrushHandler implements EventHandler {

    private double size;
    private GraphicsContext brush;
    public PaintBrushHandler (String size, GraphicsContext brush) {
        this.size = Double.parseDouble(size);
        this.brush = brush;
    }


    @Override
    public void handle(Event event) {
//        double x = e.getX() - size/2;
//        double y = e.getY() - size/2;
//        brushTool.setFill(colorpicker.getValue());
//        brushTool.fillRect(x, y, size, size);
    }

}
