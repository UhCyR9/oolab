package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Map;


public class App extends Application {
    public void start(Stage primaryStage)
    {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        ArrayList<MoveDirection> directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        Vector2d lowerLeft = ((AbstractWorldMap)map).getLowerLeft();
        Vector2d upperRight = ((AbstractWorldMap)map).getUpperRight();

        GridPane grid = createGrid(lowerLeft,upperRight, map);

        Scene scene = new Scene(grid,800,800);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGrid(Vector2d lowerLeft, Vector2d upperRight, IWorldMap map)
    {
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        createBorder(grid,lowerLeft,upperRight, 20);
        addObjects(grid,map);
        grid.setAlignment(Pos.CENTER);
        return grid;
    }

    private void addObjects(GridPane grid, IWorldMap map)
    {
        Map<Vector2d,IMapElement> impassable = ((AbstractWorldMap)map).getImpassable();
        Map<Vector2d, IMapElement> passable = ((AbstractWorldMap)map).getPassable();

        Label tmp;
        int alignY = (((AbstractWorldMap) map).getUpperRight().y)+1;
        int alignX = (((AbstractWorldMap) map).getLowerLeft().x)-1;
        for (IMapElement element : impassable.values())
        {
            Vector2d position = element.getPosition();
            tmp = new Label(element.toString());
            grid.add(tmp,position.x - alignX,alignY - position.y,1,1);
            GridPane.setHalignment(tmp, HPos.CENTER);
        }

        for (IMapElement element : passable.values())
        {
            Vector2d position = element.getPosition();
            tmp = new Label(element.toString());
            grid.add(tmp,position.x - alignX,alignY - position.y,1,1);
            GridPane.setHalignment(tmp, HPos.CENTER);
        }
    }

    private void createBorder(GridPane grid, Vector2d lowerLeft, Vector2d upperRight, int size)
    {
        grid.getColumnConstraints().add(new ColumnConstraints(size));
        grid.getRowConstraints().add(new RowConstraints(size));
        Label tmp = new Label("y\\x");
        grid.add(tmp,0,0,1,1);
        GridPane.setHalignment(tmp, HPos.CENTER);

        int iterator = 1;

        for (Integer i = upperRight.y; i >= lowerLeft.y;i--)
        {
            tmp = new Label(i.toString());
            grid.add(tmp,0,iterator,1,1);
            iterator++;
            GridPane.setHalignment(tmp, HPos.CENTER);
            grid.getRowConstraints().add(new RowConstraints(size));
        }

        iterator = 1;
        for (Integer i = lowerLeft.x; i <= upperRight.x;i++)
        {
            tmp = new Label(i.toString());
            grid.add(tmp,iterator,0,1,1);
            iterator++;
            GridPane.setHalignment(tmp, HPos.CENTER);
            grid.getColumnConstraints().add(new ColumnConstraints(size));
        }
    }

}
