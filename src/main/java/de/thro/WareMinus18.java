package de.thro;

public class WareMinus18 extends WareMinus30 {

    @Override
    public int accept(LagerplatzVisitor visitor)
    {
        if (visitor.storeToLagerplatz(this) == -1)
            return super.accept(visitor);
        return 0;
    }
}
