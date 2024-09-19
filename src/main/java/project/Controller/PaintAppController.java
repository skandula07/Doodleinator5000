package project.Controller;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextField;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import project.View.GuiView;
import project.View.PaintAppView;

import javax.imageio.ImageIO;

import java.awt.image.RenderedImage;
import java.io.*;
import java.util.ArrayList;

public class PaintAppController extends Application {

    @FXML
    private ColorPicker linecolor;
    @FXML
    private ColorPicker fillcolor;
    @FXML
    private ToggleButton brush;
    @FXML
    private TextField strokeSize;
    @FXML
    private Canvas canvas;
    @FXML
    private StackPane stackPane;

    @FXML
    private CheckBox eraser;

    @FXML
    private ToggleButton saveButton;

    @FXML
    private ToggleButton openButton;
    @FXML
    private ToggleButton clearButton;

    private ArrayList<ToggleButton> toolButtons;
    @FXML
    private ToggleButton elipse;
    @FXML
    private ToggleButton line;
    @FXML
    private ToggleButton box;
    private boolean brushSelected = false;
    private GraphicsContext gc;

    private GuiView view;
    private Stage primaryStage;

    private double stroke;


    public PaintAppController() {
        this.primaryStage = new Stage();

    }

    public void initToolButtonsList(){

//        this.stroke = 2.0;

        this.toolButtons = new ArrayList<>();
        toolButtons.add(brush);
        toolButtons.add(line);
        toolButtons.add(box);
        toolButtons.add(elipse);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.view = new PaintAppView(this);
        try {
            primaryStage.setScene(this.view.load());
            gc = canvas.getGraphicsContext2D();

            initToolButtonsList();
            setDrawingActions();
            setClearButton();
            setSaveButton();
            setOpenButton();
            primaryStage.show();
        } catch (NullPointerException e) {
//            System.err.println("it was null!");
            e.printStackTrace();
        }
    }

    private void setOpenButton() {
        openButton.setOnAction(event -> {
            FileChooser openFile = new FileChooser();
            openFile.setTitle("Open File");
            File file = openFile.showOpenDialog(primaryStage);

            if (file != null) {
                try {
                    InputStream io = new FileInputStream(file);
                    javafx.scene.image.Image img = new Image(io);
                    gc.drawImage(img, 0, 0);
                } catch (IOException ex) {
                    System.out.println("Error!");
                }
            }

        });
    }

    public void setClearButton() {
        clearButton.setOnMouseClicked(
                e -> {
                    gc.clearRect(0, 0,
                            canvas.getWidth(), canvas.getWidth());
                });
    }


    public void setDrawingActions() {
//        this.stroke = Double.parseDouble(this.strokeSize.getText());
        canvas.setOnMouseDragged( e-> {
            if (brush.isSelected()) {
                double size = Double.parseDouble(this.strokeSize.getText());
                double x = e.getX() - size / 2;
                double y = e.getY() - size / 2;

                if (!eraser.isSelected()) {
                    gc.setFill(linecolor.getValue());
                    gc.fillRect(x, y, size, size);
                } else {
                    gc.clearRect(x, y, size, size);
                }
            } });


        Ellipse elps = new Ellipse();
        Rectangle rect = new Rectangle();
        Line ln = new Line();

        canvas.setOnMousePressed(e -> {
            if (elipse.isSelected()) {

                gc.setLineWidth(Double.parseDouble(this.strokeSize.getText()));

                gc.setStroke(linecolor.getValue());
//                gc.setFill(fillcolor.getValue());
                elps.setCenterX(e.getX());
                elps.setCenterY(e.getY());
            } else if (box.isSelected()) {
                gc.setLineWidth(Double.parseDouble(this.strokeSize.getText()));

                gc.setStroke(linecolor.getValue());
                rect.setX(e.getX());
                rect.setY(e.getY());
            } else if (line.isSelected()) {
                gc.setLineWidth(Double.parseDouble(this.strokeSize.getText()));

                gc.setStroke(linecolor.getValue());
                ln.setStartX(e.getX());
                ln.setStartY(e.getY());
            }
        });

        canvas.setOnMouseReleased(e -> {
            if (elipse.isSelected()) {
                elps.setRadiusX(Math.abs(e.getX() - elps.getCenterX()));
                elps.setRadiusY(Math.abs(e.getY() - elps.getCenterY()));

                if(elps.getCenterX() > e.getX()) {
                    elps.setCenterX(e.getX());
                }
                if(elps.getCenterY() > e.getY()) {
                    elps.setCenterY(e.getY());
                }

                gc.strokeOval(elps.getCenterX(), elps.getCenterY(), elps.getRadiusX(), elps.getRadiusY());


            } else if (box.isSelected()) {
                rect.setWidth(Math.abs((e.getX() - rect.getX())));
                rect.setHeight(Math.abs((e.getY() - rect.getY())));

                if(rect.getX() > e.getX()) {
                    rect.setX(e.getX());
                }

                if(rect.getY() > e.getY()) {
                    rect.setY(e.getY());
                }
                gc.strokeRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

            } else if (line.isSelected()) {
                ln.setEndX(e.getX());
                ln.setEndY(e.getY());
                gc.strokeLine(ln.getStartX(), ln.getStartY(), ln.getEndX(), ln.getEndY());
            }
        });
    }


    public void setSaveButton() {
        saveButton.setOnAction(event -> {

//            FileChooser saveFile = new FileChooser();
//            saveFile.setTitle("Save File!");
//            File file = saveFile.showSaveDialog(primaryStage);
//
//
//            if (file != null) {
//
//                try {
//
//                    WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
//                    canvas.snapshot(null, writableImage);
//                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
//                    ImageIO.write(renderedImage, "png", file);
//
//                    System.out.println(file.exists());
//                    System.out.println("canvas saved at" + file);
//
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }



                FileChooser saveFile = new FileChooser();
            saveFile.setTitle("Save Image");
            saveFile.setInitialFileName("image.png");

                File file = saveFile.showSaveDialog(this.primaryStage);

            if (!file.getName().contains(".png")) {
                file = new File(file.getName()+".png");
            }

            if (file != null) {
                try {

                    int width = (int)this.canvas.getWidth();
                    int height = (int)this.canvas.getHeight();
                    WritableImage writableImage = new WritableImage(width, height);
                    canvas.snapshot(null, writableImage);
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                    ImageIO.write(renderedImage, "png", file);
                } catch (IOException ex) {
                    System.out.println("Error!");
                }
            }




        });





    }



    public Canvas getCanvas() {
        return this.getCanvas();
    }

    public GraphicsContext getGc() {
        return this.getGc();
    }

}
