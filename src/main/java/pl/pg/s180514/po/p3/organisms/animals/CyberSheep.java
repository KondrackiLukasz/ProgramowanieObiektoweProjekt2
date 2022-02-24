package pl.pg.s180514.po.p3.organisms.animals;

import pl.pg.s180514.po.p3.Position;
import pl.pg.s180514.po.p3.World;
import pl.pg.s180514.po.p3.organisms.Organism;
import pl.pg.s180514.po.p3.organisms.plants.SosnowskiBorscht;

public class CyberSheep extends Sheep {
    public static final int startingRate = 1;

    public CyberSheep(Position position, World world) {
        super(position, world);
        this.strength = 11;
        this.initiative = 4;
    }

    @Override
    public void action() {
        Position borschtPosition = world.findOrganism(org -> org instanceof SosnowskiBorscht);
        if (borschtPosition == null) {
            super.action();
        } else {
            if (borschtPosition.getX() > position.getX()) {
                world.moveAnimal(this, new Position(position.getX() + 1, position.getY()));
            } else if (borschtPosition.getX() < position.getX()) {
                world.moveAnimal(this, new Position(position.getX() - 1, position.getY()));
            } else if (borschtPosition.getY() > position.getY()) {
                world.moveAnimal(this, new Position(position.getX(), position.getY() + 1));
            } else if (borschtPosition.getY() < position.getY()) {
                world.moveAnimal(this, new Position(position.getX(), position.getY() - 1));
            }
        }
    }

    @Override
    public Organism reproduce() {
        return new CyberSheep(position, world);
    }

    @Override
    public String getIcon() {
        return "C";
    }



    @Override
    public String getName() {
        return "Cyberowca";
    }
}
