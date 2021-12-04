package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine {
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

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    @Override
    public void run() {
        int i = 0;
        for(MoveDirection move :moveDirections)
        {
            if(i == animals.size())
            {
                i = 0;
            }
            animals.get(i).move(move);
            i += 1;
        }

    }
}
