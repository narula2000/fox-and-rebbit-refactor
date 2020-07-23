package io.muic.ooc.fab;

import java.awt.*;

public enum AnimalSpecies {

    RABBIT(0.1, Rabbit.class, Color.GREEN, 8),
    FOX(0.1, Fox.class, Color.PINK, 20);

    private final double breedingProbability;

    private final Class animalClass;

    private final Color color;

    private final int foodValue;

    AnimalSpecies(double breedingProbability, Class animalClass, Color color, int foodValue) {
        this.breedingProbability = breedingProbability;
        this.animalClass = animalClass;
        this.color = color;
        // adding the food value for each animals for solving dependence problem //
        this.foodValue = foodValue;
    }

    public double getBreedingProbability() {
        return breedingProbability;
    }

    public Class getAnimalClass() {
        return animalClass;
    }

    public Color getColor() {
        return color;
    }

    public int getFoodValue() {
        return foodValue;
    }
}
