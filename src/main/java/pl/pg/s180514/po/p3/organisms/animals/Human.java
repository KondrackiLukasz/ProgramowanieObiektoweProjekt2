package pl.pg.s180514.po.p3.organisms.animals;

import pl.pg.s180514.po.p3.Position;
import pl.pg.s180514.po.p3.World;
import pl.pg.s180514.po.p3.organisms.Organism;

public class Human extends Animal {
    public static final int startingRate = 0;

    private char command = 'n';
    private int cooldown = -5;

    public Human(Position position, World world) {
        super(4, 5, position, world);
    }

    @Override
    public Organism reproduce() {
        return null;
    }


    @Override
    public void action() {
        switch (command) {
            case 'l' -> world.moveAnimal(this, new Position(position.getX() - 1, position.getY()));
            case 'u' -> world.moveAnimal(this, new Position(position.getX(), position.getY() - 1));
            case 'r' -> world.moveAnimal(this, new Position(position.getX() + 1, position.getY()));
            case 'd' -> world.moveAnimal(this, new Position(position.getX(), position.getY() + 1));
            case 's' -> cooldown = cooldown <= -5 ? 5 : -5;
        }

        if (cooldown > 0) {
            world.getCommentator().addPowerComment();
            specialAbility();
        }

        if (cooldown > -5) {
            cooldown--;
            if (cooldown == -5)
                world.getCommentator().addPowerReadyComment();
        }

        command = 'n';
    }

    private void specialAbility() {
        for (var org : world.getNeighbours(position)) {
            world.deleteOrganism(org.getPosition());
        }
    }

    @Override
    public String getIcon() {
        return "H";
    }


    @Override
    public String getName() {
        return "Człowiek";
    }

    public void setCommand(char command) {
        this.command = command;
    }
}
