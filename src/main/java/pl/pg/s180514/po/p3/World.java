package pl.pg.s180514.po.p3;

import pl.pg.s180514.po.p3.organisms.Organism;
import pl.pg.s180514.po.p3.organisms.animals.*;
import pl.pg.s180514.po.p3.organisms.plants.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class World implements Serializable {
    private static final int maxX = Position.maxX;
    private static final int maxY = Position.maxY;
    private Organism[][] map = new Organism[maxX][maxY];
    private PriorityQueue<Organism> turnOrder = new PriorityQueue<>();
    private Commentator commentator;

    public World() {
        populateMap();
    }

    public void fillTurnOrder() {
        turnOrder = new PriorityQueue<>();
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                if (map[i][j] != null) {
                    turnOrder.add(map[i][j]);
                }
            }
        }
    }

    public boolean deleteOrganism(Position pos) {
        if (map[pos.getX()][pos.getY()] == null)
            return false;

        commentator.addDeathComment(map[pos.getX()][pos.getY()]);
        turnOrder.remove(map[pos.getX()][pos.getY()]);
        map[pos.getX()][pos.getY()] = null;
        return true;
    }

    public void moveAnimal(Animal an, Position dest) {
        if (dest.getX() < 0 || dest.getX() >= maxX || dest.getY() < 0 || dest.getY() >= maxY)
            return;

        if (map[dest.getX()][dest.getY()] == null) {
            map[dest.getX()][dest.getY()] = an;
            map[an.getPosition().getX()][an.getPosition().getY()] = null;
            an.setPosition(dest);
        } else {
            map[dest.getX()][dest.getY()].collision(an);
        }
    }

    public void moveWinnerDeleteLoser(Organism winner, Organism loser) {
        commentator.addDeathComment(loser);
        Position posToDel = winner.getPosition();
        winner.setPosition(loser.getPosition());
        loser.setPosition(posToDel);

        map[winner.getPosition().getX()][winner.getPosition().getY()] = winner;

        map[posToDel.getX()][posToDel.getY()] = null;
        turnOrder.remove(loser);
    }

    public List<Position> getEmptyAdjacentFields(Position position) {
        ArrayList<Position> result = new ArrayList<>();

        for (Position pos : position.getAdjacentPositions()) {
            if (map[pos.getX()][pos.getY()] == null)
                result.add(pos);
        }

        return result;
    }

    public boolean addOrganism(Organism org, Position pos) {
        if (map[pos.getX()][pos.getY()] == null && org != null) {
            map[pos.getX()][pos.getY()] = org;
            org.setPosition(pos);
            return true;
        } else {
            return false;
        }
    }

    public List<Organism> getNeighbours(Position pos) {
        List<Organism> neighbours = new ArrayList<>();
        for (Position p : pos.getAdjacentPositions()) {
            if (map[p.getX()][p.getY()] != null)
                neighbours.add(map[p.getX()][p.getY()]);
        }
        return neighbours;
    }

    public Organism getOrganism(Position pos) {
        return map[pos.getX()][pos.getY()];
    }

    public void assignRandomPlace(Organism org) {
        boolean emptyTile = false;
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                if (map[i][j] == null) {
                    emptyTile = true;
                    break;
                }
            }
            if (emptyTile)
                break;
        }
        if (!emptyTile)
            return;

        while (true) {
            int x = ThreadLocalRandom.current().nextInt(maxX);
            int y = ThreadLocalRandom.current().nextInt(maxY);
            if (addOrganism(org, new Position(x, y)))
                break;
        }
    }

    public void populateMap() {
        int totalTiles = maxX * maxY - 1; //One reserved for Human
        assignRandomPlace(new Human(new Position(0, 0), this));
        int mapFractionToPopulate = 10;
        int tilesToPopulate = totalTiles / mapFractionToPopulate;

        int totalRateSum = Antelope.startingRate + Fox.startingRate + Sheep.startingRate + Turtle.startingRate + Wolf.startingRate + Dandelion.startingRate + Grass.startingRate
                + Guarana.startingRate + SosnowskiBorscht.startingRate + WolfBerries.startingRate + CyberSheep.startingRate;

        int antelopeRange = Antelope.startingRate;
        int foxRange = antelopeRange + Fox.startingRate;
        int sheepRange = foxRange + Sheep.startingRate;
        int turtleRange = sheepRange + Turtle.startingRate;
        int wolfRange = turtleRange + Wolf.startingRate;
        int dandelionRange = wolfRange + Dandelion.startingRate;
        int grassRange = dandelionRange + Grass.startingRate;
        int guaranaRange = grassRange + Guarana.startingRate;
        int sosnowskiRange = guaranaRange + SosnowskiBorscht.startingRate;
        int berryRange = sosnowskiRange + WolfBerries.startingRate;
        int cyberSheepRange = berryRange + CyberSheep.startingRate;

        for (int i = 0, rnd; i < tilesToPopulate; i++) {
            rnd = ThreadLocalRandom.current().nextInt(totalRateSum);
            if (rnd < antelopeRange) assignRandomPlace(new Antelope(new Position(0, 0), this));
            else if (rnd < foxRange) assignRandomPlace(new Fox(new Position(0, 0), this));
            else if (rnd < sheepRange) assignRandomPlace(new Sheep(new Position(0, 0), this));
            else if (rnd < turtleRange) assignRandomPlace(new Turtle(new Position(0, 0), this));
            else if (rnd < wolfRange) assignRandomPlace(new Wolf(new Position(0, 0), this));
            else if (rnd < dandelionRange) assignRandomPlace(new Dandelion(new Position(0, 0), this));
            else if (rnd < grassRange) assignRandomPlace(new Grass(new Position(0, 0), this));
            else if (rnd < guaranaRange) assignRandomPlace(new Guarana(new Position(0, 0), this));
            else if (rnd < sosnowskiRange) assignRandomPlace(new SosnowskiBorscht(new Position(0, 0), this));
            else if (rnd < berryRange) assignRandomPlace(new WolfBerries(new Position(0, 0), this));
            else if (rnd < cyberSheepRange) assignRandomPlace(new CyberSheep(new Position(0, 0), this));
        }
    }


    public Position findOrganism(Checker condition) {

        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                if (map[i][j] != null) {
                    if (condition.check(map[i][j])) {
                        return new Position(i, j);
                    }
                }
            }
        }

        return null;
    }

    public void save() {
        try (OutputStream os = new FileOutputStream("src/main/resources/saved");
             ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try (InputStream is = new FileInputStream("src/main/resources/saved");
             ObjectInputStream ois = new ObjectInputStream(is)) {
            map = (Organism[][]) ois.readObject();
            for (int i = 0; i < maxX; i++)
                for (int j = 0; j < maxY; j++)
                    if (map[i][j] != null)
                        map[i][j].setWorld(this);

            fillTurnOrder();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public List<Organism> getAllOrganisms() {
        ArrayList<Organism> organisms = new ArrayList<>();

        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                if (map[i][j] != null) {
                    organisms.add(map[i][j]);
                }
            }
        }

        return organisms;
    }

    public void makeTurn() {
        fillTurnOrder();
        while (!turnOrder.isEmpty()) {
            Organism org = turnOrder.poll();
            org.incAge();
            org.action();
        }
    }

    public Human getHuman() {
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                if (map[i][j] instanceof Human) {
                    return (Human) map[i][j];
                }
            }
        }
        return null;
    }

    public Commentator getCommentator() {
        return commentator;
    }

    public void setCommentator(Commentator commentator) {
        this.commentator = commentator;
    }
}
