package de.thro;

public class WareGekuehlt extends Ware {

    @Override
    public int accept(LagerplatzVisitor visitor)
    {
        return visitor.storeToLagerplatz(this);
    }
}
