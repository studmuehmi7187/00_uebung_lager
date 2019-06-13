package de.thro;

public class WareMinus30 extends Ware {

    @Override
    public int accept(LagerplatzVisitor visitor)
    {
        return visitor.storeToLagerplatz(this);
    }
}
