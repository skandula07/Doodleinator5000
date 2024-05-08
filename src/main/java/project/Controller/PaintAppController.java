package project.Controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.View.GuiView;
import project.View.PaintAppView;

public class PaintAppController extends Application {

    @FXML
    private ColorPicker colorpicker;
    @FXML
    private Button brush;
    @FXML
    private TextField brushsize;
    @FXML
    private Canvas canvas;
    @FXML
    private CheckBox eraser;

    private boolean brushSelected = false;
    private GraphicsContext brushTool;

    private GuiView view;
    private Stage primaryStage;

    public PaintAppController() {
        this.primaryStage = new Stage();

    }

    @Override
    public void start(Stage stage) throws Exception {
        this.view = new PaintAppView(this);
        try {
            primaryStage.setScene(this.view.load());
            setBrushAction();
            primaryStage.show();
        } catch (NullPointerException e) {
            System.err.println("it was null!");
        }
    }


    public void setBrushAction() {
        brushTool = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged( e-> {
            double size = Double.parseDouble(brushsize.getText());
            double x = e.getX() - size/2;
            double y = e.getY() - size/2;

            if (!eraser.isSelected()) {
                brushTool.setFill(colorpicker.getValue());
                brushTool.fillRect(x, y, size, size);
            }
            else {
                brushTool.clearRect(x, y, size, size);
            }
        });
    }


    public ColorPicker getColorpicker(){
        return this.colorpicker;
    }

    public Button getBrush(){
        return this.brush;
    }
    public TextField getBrushsize(){
        return this.brushsize;
    }

    public Canvas getCanvas(){
        return this.getCanvas();
    }

    public GraphicsContext getBrushTool(){
        return this.getBrushTool();
    }

    public CheckBox getEraser(){
        return eraser;
    }


}
