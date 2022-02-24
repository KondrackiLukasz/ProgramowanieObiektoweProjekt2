package pl.pg.s180514.po.p3.organisms;

import pl.pg.s180514.po.p3.Position;
import pl.pg.s180514.po.p3.World;
import pl.pg.s180514.po.p3.organisms.animals.Animal;

import java.io.Serializable;

public abstract class Organism implements Comparable<Organism>, Serializable {
    protected int initiative;
    protected int strength;
    protected int age = 0;
    protected Position position;
    protected World world;

    public Organism(int initiative, int strength, Position position, World world) {
        this.initiative = initiative;
        this.strength = strength;
        this.position = position;
        this.world = world;
    }

    @Override
    public int compareTo(Organism o) {
        return initiative == o.initiative ? o.age - age : o.initiative - initiative;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAge() {
        return age;
    }

    public void incAge() {
        this.age++;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public abstract void action();

    public void collision(Animal intruder) {
        if (intruder.getStrength() < strength) {
            world.deleteOrganism(intruder.getPosition());
        } else {
            world.moveWinnerDeleteLoser(intruder, this);
        }
    }

    public abstract Organism reproduce();

    public abstract String getIcon();
    public abstract String getName();
}
