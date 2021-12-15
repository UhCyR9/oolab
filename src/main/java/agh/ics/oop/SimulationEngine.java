package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine, Runnable {
    private ArrayList<MoveDirection> moveDirections;
    private IWorldMap map;
    private Vector2d[] animalPositions;
    private ArrayList<Animal> animals = new ArrayList<>();

    public SimulationEngine(ArrayList<MoveDirection> moveDirections, IWorldMap map, Vector2d[] animalPositions){
        this.animalPositions = animalPositions;
        this.map = map;
        this.moveDirections = moveDirections;
        for (Vector2d position : animalPositions)
        {
                animals.add(new Animal(this.map, position));
        }
    }

    public SimulationEngine(IWorldMap map, Vector2d[] animalPositions){
        this.animalPositions = animalPositions;
        this.map = map;
        for (Vector2d position : animalPositions)
        {
            animals.add(new Animal(this.map, position));
        }
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void setMoveDirections(ArrayList<MoveDirection> moveDirections) {
        this.moveDirections = moveDirections;
    }

    @Override
    public void run() {
        int i = 0;
        for(MoveDirection move :moveDirections)
        {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i == animals.size())
            {
                i = 0;
            }
            animals.get(i).move(move);
            i += 1;
        }

    }
}
