package pl.pg.s180514.po.p3.organisms.plants;

import pl.pg.s180514.po.p3.Position;
import pl.pg.s180514.po.p3.World;
import pl.pg.s180514.po.p3.organisms.Organism;
import pl.pg.s180514.po.p3.organisms.animals.Animal;

public class Dandelion extends Plant {
    public static final int startingRate = 1;

    public Dandelion(Position position, World world) {
        super(0, position, world);
    }

    @Override
    public void action() {
        for (int i = 0; i < 3; i++) {
            super.action();
        }
    }

    @Override
    public void collision(Animal intruder) {
        intruder.setStrength(intruder.getStrength() + 3);
        super.collision(intruder);
    }

    @Override
    public Organism reproduce() {
        return new Dandelion(position, world);
    }

    @Override
    public String getIcon() {
        return "M";
    }


    @Override
    public String getName() {
        return "Mlecz";
    }
}
