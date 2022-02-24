package pl.pg.s180514.po.p3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Position implements Serializable {
    public static final int maxX = 20;
    public static final int maxY = 20;
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Position> getAdjacentPositions() {
        ArrayList<Position> result = new ArrayList<>();

        if (x > 0)
            result.add(new Position(x - 1, y));
        if (x < maxX - 1)
            result.add(new Position(x + 1, y));
        if (y > 0)
            result.add(new Position(x, y - 1));
        if (y < maxY - 1)
            result.add(new Position(x, y + 1));


        return result;
    }
}
