package pl.pg.s180514.po.p3.organisms.animals;

import pl.pg.s180514.po.p3.Position;
import pl.pg.s180514.po.p3.World;
import pl.pg.s180514.po.p3.organisms.Organism;

import java.util.concurrent.ThreadLocalRandom;

public class Antelope extends Animal {
    public static final int startingRate = 1;

    public Antelope(Position position, World world) {
        super(4, 4, position, world);
    }

    @Override
    public Organism reproduce() {
        return new Antelope(position, world);
    }

    @Override
    public void action() {
        var choices = position.getAdjacentPositions();
        Position dest = choices.get(ThreadLocalRandom.current().nextInt(choices.size()));

        choices = dest.getAdjacentPositions();
        dest = choices.get(ThreadLocalRandom.current().nextInt(choices.size()));

        if (!dest.equals(position))
            world.moveAnimal(this, dest);
    }

    @Override
    public void collision(Animal intruder) {
        if (intruder instanceof Antelope) {
            super.collision(intruder);
            return;
        }
        if (ThreadLocalRandom.current().nextInt(2) == 0) {
            var emptyTiles = world.getEmptyAdjacentFields(position);
            if (!emptyTiles.isEmpty()) {
                Position oldPosition = position;
                world.moveAnimal(this, emptyTiles.get(ThreadLocalRandom.current().nextInt(emptyTiles.size())));
                world.moveAnimal(intruder, oldPosition);
                return;
            }
        }
        super.collision(intruder);
    }

    @Override
    public String getIcon() {
        return "A";
    }

    @Override
    public String getName() {
        return "Antylopa";
    }
}
