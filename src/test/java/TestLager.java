import de.thro.*;
import org.junit.Assert;
import org.junit.Test;

public class TestLager {

    @Test
    public void storeLagerTest() {
        Lager lager = new Lager(2,3,2,1);

        Assert.assertEquals(0, lager.store(new WareRaumTemp()));
        Assert.assertEquals(0, lager.store(new WareRaumTemp()));
        Assert.assertEquals(0, lager.store(new WareGekuehlt()));
        Assert.assertEquals(0, lager.store(new WareRaumTemp()));
        Assert.assertEquals(0, lager.store(new WareGekuehlt()));
        Assert.assertEquals(-1, lager.store(new WareRaumTemp()));

        lager.inventar();
        lager.utilization();

        Assert.assertEquals(0, lager.store(new WareMinus30()));
        Assert.assertEquals(0, lager.store(new WareMinus18()));
        Assert.assertEquals(-1, lager.store(new WareMinus30()));
        Assert.assertEquals(0, lager.store(new WareMinus18()));
        Assert.assertEquals(-1, lager.store(new WareMinus18()));

        lager.inventar();
        lager.utilization();
    }

    @Test
    public void removeFromLagerTest() {
        Lager lager = new Lager(2,3,1,1);

        Ware w1 = new WareGekuehlt();
        lager.store(w1);
        Ware w2 = new WareMinus30();
        lager.store(w2);

        lager.inventar();
        lager.utilization();

        Ware w  = lager.remove(w1.getSnr());

        Assert.assertTrue(w instanceof  WareGekuehlt);

        w = lager.remove(w2.getSnr());
        Assert.assertTrue(w instanceof  WareMinus30);

        w = lager.remove(3);

        Assert.assertNull(w);

        lager.inventar();
        lager.utilization();
    }
}
