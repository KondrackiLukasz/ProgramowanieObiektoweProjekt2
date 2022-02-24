package pl.pg.s180514.po.p3.organisms.plants;

import pl.pg.s180514.po.p3.Position;
import pl.pg.s180514.po.p3.World;
import pl.pg.s180514.po.p3.organisms.Organism;
import pl.pg.s180514.po.p3.organisms.animals.Animal;

public class WolfBerries extends Plant {
    public static final int startingRate = 1;

    public WolfBerries(Position position, World world) {
        super(99, position, world);
    }

    @Override
    public Organism reproduce() {
        return new WolfBerries(position, world);
    }

    @Override
    public void collision(Animal intruder) {
        world.deleteOrganism(intruder.getPosition());

        if (intruder.getStrength() >= strength) {
            world.deleteOrganism(position);
        }

    }

    @Override
    public String getIcon() {
        return "J";
    }


    @Override
    public String getName() {
        return "Wilcza jagoda";
    }
}
