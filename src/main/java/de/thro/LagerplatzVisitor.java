package de.thro;

public interface LagerplatzVisitor {

    int storeToLagerplatz(WareRaumTemp ware);
    int storeToLagerplatz(WareGekuehlt ware);
    int storeToLagerplatz(WareMinus18 ware);
    int storeToLagerplatz(WareMinus30 ware);

}
