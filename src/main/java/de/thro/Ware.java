package de.thro;

/**
 * Represents an abstract Ware.
 */
public abstract class Ware {

    // Instanzenzähler
    private static int COUNTER = 0;

    // seriennr
    private int snr;

    /**
     * Erzeugt eine Waren-Instanz
     */
    public Ware() {
        this.snr = COUNTER++;
    }

    /**
     * Gibt die Seriennummer zurück
     *
     * @return Seriennummer
     */
    public int getSnr() {
        return snr;
    }

    public abstract int accept(LagerplatzVisitor visitor);

    /**
     * Stringausgabe der Ware.
     *
     * @return Ware als Sring
     */
    @Override
    public String toString() {
        return this.getClass().getName() + "{" +
                "snr=" + snr +
                '}';
    }
}
