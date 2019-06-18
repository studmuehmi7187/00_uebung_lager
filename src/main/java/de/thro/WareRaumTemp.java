package de.thro;

public class WareRaumTemp extends WareGekuehlt {

    @Override
    public int accept(LagerplatzVisitor visitor) {
        if (visitor.storeToLagerplatz(this) == -1)
            return super.accept(visitor);
        return 0;
    }
}
