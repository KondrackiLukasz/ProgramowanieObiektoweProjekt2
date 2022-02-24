package pl.pg.s180514.po.p3.organisms.plants;

import pl.pg.s180514.po.p3.Position;
import pl.pg.s180514.po.p3.World;
import pl.pg.s180514.po.p3.organisms.Organism;
import pl.pg.s180514.po.p3.organisms.animals.Animal;
import pl.pg.s180514.po.p3.organisms.animals.CyberSheep;

import java.util.ArrayList;
import java.util.List;

public class SosnowskiBorscht extends Plant {
    public static final int startingRate = 1;

    public SosnowskiBorscht(Position position, World world) {
        super(10, position, world);
    }

    @Override
    public Organism reproduce() {
        return new SosnowskiBorscht(position, world);
    }

    @Override
    public void action() {
        List<Organism> neighbours = world.getNeighbours(position);

        List<Animal> anim = new ArrayList<>();
        for (Organism org : neighbours) {
            if (org instanceof Animal)
                anim.add((Animal) org);
        }

        for (Organism org : anim) {
            if (!(org instanceof CyberSheep)) {
                world.deleteOrganism(org.getPosition());
            }
        }
    }

    @Override
    public void collision(Animal intruder) {
        if (!(intruder instanceof CyberSheep))
            world.deleteOrganism(intruder.getPosition());

        if (intruder.getStrength() >= strength) {
            world.deleteOrganism(position);
            if (intruder instanceof CyberSheep)
                world.moveAnimal(intruder, position);
        }

    }

    @Override
    public String getIcon() {
        return "S";
    }


    @Override
    public String getName() {
        return "Barszcz Sosnowskiego";
    }
}
