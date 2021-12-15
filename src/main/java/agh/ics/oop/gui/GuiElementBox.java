package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private VBox vbox;
    private Label label;
    private ImageView imageView;
    private IMapElement element;

    public GuiElementBox(IMapElement element)
    {
        this.element = element;
        Image image = null;
        try {
            image = new Image(new FileInputStream(element.getImage()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        this.imageView = imageView;
        Label label = new Label(element.getName());
        this.label = label;
        VBox vbox = new VBox(imageView, label);
        vbox.setAlignment(Pos.CENTER);
        this.vbox = vbox;
    }

    public VBox getVbox() {
        return vbox;
    }

    public void updateElement()
    {
        label.setText(element.getName());

        Image image = null;
        try {
            image = new Image(new FileInputStream(element.getImage()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        imageView.setImage(image);
    }
}
