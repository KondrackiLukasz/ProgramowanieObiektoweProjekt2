package pl.pg.s180514.po.p3.organisms.animals;

import pl.pg.s180514.po.p3.Position;
import pl.pg.s180514.po.p3.organisms.Organism;
import pl.pg.s180514.po.p3.World;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Organism {
    public Animal(int initiative, int strength, Position position, World world) {
        super(initiative, strength, position, world);
    }

    @Override
    public void action() {
        List<Position> choices = position.getAdjacentPositions();

        Position destination = choices.get(ThreadLocalRandom.current().nextInt(choices.size()));
        world.moveAnimal(this, destination);
    }

    @Override
    public void collision(Animal intruder) {
        if (this.getClass() == intruder.getClass()) {
            List<Position> emptyTiles = world.getEmptyAdjacentFields(position);
            if (!emptyTiles.isEmpty()) {
                var newOrg = this.reproduce();
                world.getCommentator().addBirthComment(newOrg);
                world.addOrganism(newOrg, emptyTiles.get(ThreadLocalRandom.current().nextInt(emptyTiles.size())));
            }
        } else {
            super.collision(intruder);
        }
    }
}
