package pl.pg.s180514.po.p3.organisms.animals;

import pl.pg.s180514.po.p3.Position;
import pl.pg.s180514.po.p3.World;
import pl.pg.s180514.po.p3.organisms.Organism;

public class Sheep extends Animal{
    public static final int startingRate = 1;

    public Sheep(Position position, World world) {
        super(4, 4, position, world);
    }

    @Override
    public Organism reproduce() {
        return new Sheep(position, world);
    }



    @Override
    public String getName() {
        return "Owca";
    }

    @Override
    public String getIcon() {
        return "O";
    }
}
