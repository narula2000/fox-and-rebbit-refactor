package io.muic.ooc.fab;


import io.muic.ooc.fab.view.SimulatorView;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

public class Simulator {


    private static final int DEFAULT_WIDTH = 120;
    private static final int DEFAULT_DEPTH = 80;

    private final List<Organism> animals;
    private final Field field;
    private int step;
    private final SimulatorView view;

    public Simulator() {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }

    public Simulator(int depth, int width) {
        if (width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be >= zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }

        animals = new ArrayList<>();
        field = new Field(depth, width);

        view = new SimulatorView(depth, width);
        AnimalSpecies[] animalTypes = AnimalSpecies.values();
        for (int i = 0; i < animalTypes.length; i++) {
            view.setColor(animalTypes[i].getAnimalClass(), animalTypes[i].getColor());
        }
        reset();
    }


    public void runLongSimulation() {
        simulate(4000);
    }


    public void simulate(int numSteps) {
        for (int step = 1; step <= numSteps && view.isViable(field); step++) {
            simulateOneStep();
        }
    }

    public void simulateOneStep() {
        step++;

        List<Organism> newAnimals = new ArrayList<>();
        for (Iterator<Organism> it = animals.iterator(); it.hasNext(); ) {
            Animal animal = (Animal) it.next();
            animal.act(newAnimals);
            if (!animal.isAlive()) {
                it.remove();
            }
        }

        animals.addAll(newAnimals);

        view.showStatus(step, field);
    }


    public void reset() {
        step = 0;
        animals.clear();
        new FieldPopulator().populate(field, animals);

        view.showStatus(step, field);
    }


    private void delay(int millisec) {
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException ie) {
        }
    }
}
