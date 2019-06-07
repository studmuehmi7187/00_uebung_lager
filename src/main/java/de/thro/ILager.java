package de.thro;

public interface ILager {

    /**
     * Ablegen der Ware.
     *
     * @param ware Die Ware selbst
     * @return Gibt 0 zurück wenn es geklappt hat, sonst -1
     */
    int store(Ware ware);

    /**
     *  Löscht die Ware mt der Seriennummer (snr) as dem Lager
     *
     * @param snr Seriennummer der Ware, die gelöscht werden soll
     * @return Gibt die referenz auf die Ware zurück, sonst null
     */
    Ware remove(int snr);

    /**
     * Gibt die Inventarliste des Lagers aus.
     */
    void inventar();

    /**
     * Gibt die Utilization der einzelnen Lagerplätze aus.
     */
    void utilization();
}
