package io.muic.ooc.fab;

import java.util.HashMap;
import java.util.Map;

public class AnimalFactory {

    private static final Map<AnimalSpecies, Class> animalClassMap = new HashMap<AnimalSpecies, Class>() {{
        AnimalSpecies[] animalSpecies = AnimalSpecies.values();
        for (int i = 0; i < animalSpecies.length; i++) {
            put(animalSpecies[i], animalSpecies[i].getAnimalClass());
        }
    }};

    public static Animal createAnimal(AnimalSpecies animalSpecies, Field field, Location location) {
        Class animalClass = animalClassMap.get(animalSpecies);
        return createAnimal(animalClass, field, location);
    }

    public static Animal createAnimal(Class animalClass, Field field, Location location) {
        if (animalClass != null) {
            try {
                Animal animal = (Animal) animalClass.newInstance();
                animal.initialize(true, field, location);
                return animal;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException("Unknown Species");
    }

}
