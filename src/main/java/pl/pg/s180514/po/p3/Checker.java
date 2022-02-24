package pl.pg.s180514.po.p3;

import pl.pg.s180514.po.p3.organisms.Organism;

@FunctionalInterface
public interface Checker {
    boolean check(Organism org);
}
