package pl.pg.s180514.po.p3.organisms.animals;

import pl.pg.s180514.po.p3.Position;
import pl.pg.s180514.po.p3.World;
import pl.pg.s180514.po.p3.organisms.Organism;

import java.util.concurrent.ThreadLocalRandom;

public class Fox extends Animal {
    public static final int startingRate = 1;

    public Fox(Position position, World world) {
        super(7, 3, position, world);
    }

    @Override
    public Organism reproduce() {
        return new Fox(position, world);
    }

    @Override
    public void action() {
        var choices = position.getAdjacentPositions();

        Position dest = choices.get(ThreadLocalRandom.current().nextInt(choices.size()));

        if (world.getOrganism(dest) == null || world.getOrganism(dest).getStrength() <= getStrength()) {
            world.moveAnimal(this, dest);
        }
    }

    @Override
    public String getIcon() {
        return "L";
    }


    @Override
    public String getName() {
        return "Lis";
    }
}
