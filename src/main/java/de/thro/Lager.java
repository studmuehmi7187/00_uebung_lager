package de.thro;

import java.util.*;

public class Lager implements ILager, LagerplatzVisitor{

    /**
     * Verschiedene Lagerplatztype
     */
    private enum LagerplatzType {
        RAUMTEMP,
        GEKUEHLT,
        MINUS_18,
        MINUS_30
    }

    /**
     * Interne Hilfsklasse zum ablegen der Ware
     */
    private class LagerplatzElement {

        private Ware ware;
        private LagerplatzType lagerplatz;

        LagerplatzElement(Ware w, LagerplatzType lagerplatz) {
            this.ware = w;
            this.lagerplatz = lagerplatz;
        }
    }

    // wir legen virtuell alles in einer Map ab, dabei ist der Key die SNR der Ware
    private Map<Integer,  LagerplatzElement> lagerplaetze;

    // hier speichern wir die verbleibenden Lagerplätze pro Typ
    private Map<LagerplatzType, Integer> remaining;

    /**
     * Erzuegt eine Lager Instanz
     *
     * @param raumtemp Anzahl der Lagerplätze bei Raumtemperatur
     * @param gekuehlt Anzahl der Lagerplätze für gekühlt
     * @param minus18 Anzahl der Lagerplätze bei minus 18 Grad
     * @param minus30 Anzahl der Lagerplätze bei minus 30 Grad
     */
    public Lager(int raumtemp, int gekuehlt, int minus18, int minus30) {
        this.lagerplaetze = new HashMap<>(raumtemp+gekuehlt+minus18+minus30);
        this.remaining = new HashMap<>();

        // initalisiert die verbleibenden Lagerplaetze mit der intitialen Größe
        this.remaining.put(LagerplatzType.RAUMTEMP, raumtemp);
        this.remaining.put(LagerplatzType.GEKUEHLT, gekuehlt);
        this.remaining.put(LagerplatzType.MINUS_18, minus18);
        this.remaining.put(LagerplatzType.MINUS_30, minus30);
    }

    /**
     * Ablegen der Ware.
     *
     * @param ware Die Ware selbst
     * @return Gibt 0 zurück wenn es geklappt hat, sonst -1
     */
    @Override
    public int store(Ware ware) {
        return ware.accept(this);
    }

    /**
     *  Löscht die Ware mt der Seriennummer (snr) as dem Lager
     *
     * @param snr Seriennummer der Ware, die gelöscht werden soll
     * @return Gibt die referenz auf die Ware zurück, sonst null
     */
    @Override
    public Ware remove(int snr) {
        if (this.lagerplaetze.containsKey(snr)) {
            LagerplatzElement le = this.lagerplaetze.remove(snr);
            int lagerplatzRemaining = this.remaining.get(le.lagerplatz);
            this.remaining.put(le.lagerplatz, lagerplatzRemaining + 1);
            return le.ware;
        }
        return null;
    }

    /**
     * Gibt die Inventarliste des Lagers aus.
     */
    @Override
    public void inventar() {
        System.out.println("Lagerplatz:");
        System.out.println("-----------");
        for (LagerplatzElement le : this.lagerplaetze.values()) {
            System.out.println(le.ware + " ---- " + le.lagerplatz);
        }
        System.out.println();
    }

    /**
     * Gibt die Utilization der einzelnen Lagerplätze aus.
     */
    @Override
    public void utilization() {
        System.out.println("Utilization:");
        System.out.println("------------");
        for (LagerplatzType lp : this.remaining.keySet()) {
            System.out.println(lp + " : " + this.remaining.get(lp));
        }
        System.out.println();
    }

    public int storeToLagerplatz(WareRaumTemp ware) {
        return checkLager(ware, LagerplatzType.RAUMTEMP);
    }

    public int storeToLagerplatz(WareGekuehlt ware) {
        return checkLager(ware, LagerplatzType.GEKUEHLT);
    }

    public int storeToLagerplatz(WareMinus18 ware) {
        return checkLager(ware, LagerplatzType.MINUS_18);
    }

    public int storeToLagerplatz(WareMinus30 ware) {
        return checkLager(ware, LagerplatzType.MINUS_30);
    }

    private int checkLager(Ware ware, LagerplatzType type) {
        int lagerplatzRemaining = this.remaining.get(type);
        if (lagerplatzRemaining > 0) {
            lagerplaetze.put(ware.getSnr(), new LagerplatzElement(ware, type));
            this.remaining.put(type, (lagerplatzRemaining - 1));
            return 0;
        }
        return -1;
    }
}
