package pl.pg.s180514.po.p3.organisms.animals;

import pl.pg.s180514.po.p3.Position;
import pl.pg.s180514.po.p3.World;
import pl.pg.s180514.po.p3.organisms.Organism;

public class Wolf extends Animal {
    public static final int startingRate = 1;

    public Wolf(Position position, World world) {
        super(5, 9, position, world);
    }

    @Override
    public Organism reproduce() {
        return new Wolf(position, world);
    }


    @Override
    public String getIcon() {
        return "W";
    }


    @Override
    public String getName() {
        return "Wilk";
    }
}
