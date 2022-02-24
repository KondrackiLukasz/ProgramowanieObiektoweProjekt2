package pl.pg.s180514.po.p3.organisms.plants;

import pl.pg.s180514.po.p3.Position;
import pl.pg.s180514.po.p3.organisms.Organism;
import pl.pg.s180514.po.p3.World;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Plant extends Organism {
    public Plant(int strength, Position position, World world) {
        super(0, strength, position, world);
    }

    @Override
    public void action() {
        if (ThreadLocalRandom.current().nextInt(20) == 0) {
            List<Position> emptyTiles = world.getEmptyAdjacentFields(position);
            if (!emptyTiles.isEmpty()) {
                world.addOrganism(this.reproduce(), emptyTiles.get(ThreadLocalRandom.current().nextInt(emptyTiles.size())));
            }
        }
    }
}
