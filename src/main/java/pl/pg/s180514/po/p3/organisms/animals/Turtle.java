package pl.pg.s180514.po.p3.organisms.animals;

import pl.pg.s180514.po.p3.Position;
import pl.pg.s180514.po.p3.World;
import pl.pg.s180514.po.p3.organisms.Organism;

import java.util.concurrent.ThreadLocalRandom;

public class Turtle extends Animal {
    public static final int startingRate = 1;

    public Turtle(Position position, World world) {
        super(1, 2, position, world);
    }

    @Override
    public Organism reproduce() {
        return new Turtle(position, world);
    }

    @Override
    public void action() {
        if (ThreadLocalRandom.current().nextInt(4) == 0) {
            super.action();
        }
    }

    @Override
    public void collision(Animal intruder) {
        if (intruder.getStrength() >= 5 || intruder instanceof Turtle) {
            super.collision(intruder);
        }
    }

    @Override
    public String getIcon() {
        return "Ż";
    }


    @Override
    public String getName() {
        return "Zółw";
    }
}
