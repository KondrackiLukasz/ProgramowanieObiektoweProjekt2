package pl.pg.s180514.po.p3.organisms.plants;

import pl.pg.s180514.po.p3.Position;
import pl.pg.s180514.po.p3.World;
import pl.pg.s180514.po.p3.organisms.Organism;

public class Guarana extends Plant {
    public static final int startingRate = 1;

    public Guarana(Position position, World world) {
        super(0, position, world);
    }

    @Override
    public Organism reproduce() {
        return new Guarana(position, world);
    }

    @Override
    public String getIcon() {
        return "G";
    }



    @Override
    public String getName() {
        return "Guarana";
    }
}
