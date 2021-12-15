package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class App extends Application implements IPositionChangeObserver {
    private AbstractWorldMap map;
    private HashMap<IMapElement, GuiElementBox> guiElements = new HashMap<>();
    private GridPane grid;
    private Thread engineThread;
    private SimulationEngine simulationEngine;

    @Override
    public void init() throws Exception {
        try {
            String[] args = getParameters().getRaw().toArray(new String[0]);
            ArrayList<MoveDirection> directions = new OptionsParser().parse(args);
            IWorldMap map = new GrassField(10);
            this.map = (AbstractWorldMap) map;
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};

            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setGridLinesVisible(true);
            this.grid = grid;

            SimulationEngine engine = new SimulationEngine(map, positions);
            this.simulationEngine = engine;
            addElements();
            Thread engineThread = new Thread(engine);
            this.engineThread = engineThread;
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e);
        }
    }

    public void start(Stage primaryStage)
    {
        TextField input = new TextField();
        Button startBtn = new Button("START");
        VBox controlPanel = new VBox(10,input,startBtn);
        controlPanel.setAlignment(Pos.CENTER);
        HBox wrapper = new HBox(20,controlPanel,grid);

        createGrid();
        configurePanel(startBtn, input);

        Scene scene = new Scene(wrapper,800,800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void configurePanel(Button startBtn, TextField input)
    {
        startBtn.setOnAction((event) -> {
            String[] newDirections = input.getText().split(" ");
            try {
                simulationEngine.setMoveDirections(new OptionsParser().parse(newDirections));
                Thread newThread = new Thread(simulationEngine);
                newThread.start();
            }
            catch (IllegalArgumentException e)
            {
                System.out.println(e);
            }

        });
    }


    private void addElements()
    {
        Map<Vector2d,IMapElement> impassable = map.getImpassable();
        Map<Vector2d, IMapElement> passable = map.getPassable();
        GuiElementBox tmp;

        for (IMapElement element : impassable.values())
        {
            ((Animal)element).addObserver(this);
            tmp = new GuiElementBox(element);
            guiElements.put(element,tmp);
        }

        for (IMapElement element : passable.values())
        {
            tmp = new GuiElementBox(element);
            guiElements.put(element,tmp);
        }
    }

    private void createGrid()
    {
        createBorder(40);
        addObjects();
    }

    private void addObjects()
    {
        GuiElementBox tmp;
        int alignY = map.getUpperRight().y+1;
        int alignX = map.getLowerLeft().x-1;
        for (IMapElement element : guiElements.keySet())
        {
            Vector2d position = element.getPosition();
            tmp = guiElements.get(element);
            tmp.updateElement();
            guiElements.put(element,tmp);
            grid.add(tmp.getVbox(),position.x - alignX,alignY - position.y,1,1);
            GridPane.setHalignment(tmp.getVbox(), HPos.CENTER);
        }
    }

    private void createBorder(int size)
    {
        grid.getColumnConstraints().add(new ColumnConstraints(size));
        grid.getRowConstraints().add(new RowConstraints(size));
        Label tmp = new Label("y\\x");
        grid.add(tmp,0,0,1,1);
        GridPane.setHalignment(tmp, HPos.CENTER);

        int iterator = 1;

        for (Integer i = map.getUpperRight().y; i >= map.getLowerLeft().y;i--)
        {
            tmp = new Label(i.toString());
            grid.add(tmp,0,iterator,1,1);
            iterator++;
            GridPane.setHalignment(tmp, HPos.CENTER);
            grid.getRowConstraints().add(new RowConstraints(size));
        }

        iterator = 1;
        for (Integer i = map.getLowerLeft().x; i <= map.getUpperRight().x;i++)
        {
            tmp = new Label(i.toString());
            grid.add(tmp,iterator,0,1,1);
            iterator++;
            GridPane.setHalignment(tmp, HPos.CENTER);
            grid.getColumnConstraints().add(new ColumnConstraints(size));
        }
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Platform.runLater(() ->
        {
            Node node = grid.getChildren().get(0);
            grid.getChildren().clear();
            grid.getChildren().add(0,node);
            grid.getColumnConstraints().clear();
            grid.getRowConstraints().clear();
            createGrid();
        });
    }
}
