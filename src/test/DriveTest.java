package test;

import org.junit.Test;
import static org.junit.Assert.*;

import interest.*;
import optimizations.*;

public class DriveTest{
    @Test
    public void testInitiation(){
        Destination sea = Destination.of("Seattle");
        Destination port = Destination.of("Portland");

        Gas halfTank = Gas.of(10);
        Gas fullTank = Gas.of(20);

        Drive seaToPort = Drive.of(halfTank, sea, port);

        assertEquals(halfTank, seaToPort.getCost());
        assertEquals(port, seaToPort.getTargetNode());
        assertEquals(sea, seaToPort.getSourceNode());

        assertFalse(seaToPort.isTakeable(fullTank));
    }

    @Test(expected = NullPointerException.class)
    public void testBadCost(){
        Destination sea = Destination.of("Seattle");
        Destination port = Destination.of("Portland");

        Gas halfTank = Gas.of(10);
        
        Drive nullTest = Drive.of(null, sea, port);
    }

    @Test(expected = NullPointerException.class)
    public void testBadSource(){
        Destination sea = Destination.of("Seattle");
        Destination port = Destination.of("Portland");

        Gas halfTank = Gas.of(10);
        
        Drive nullTest = Drive.of(halfTank, null, port);
    }

    @Test(expected = NullPointerException.class)
    public void testBadTarget(){
        Destination sea = Destination.of("Seattle");
        Destination port = Destination.of("Portland");

        Gas halfTank = Gas.of(10);
        
        Drive nullTest = Drive.of(halfTank, sea, null);
    }

    @Test
    public void testIsTakeable(){
        Destination sea = Destination.of("Seattle");
        Destination port = Destination.of("Portland");

        Gas emptyTank = Gas.of(0);
        Gas halfTank = Gas.of(10);
        Gas fullTank = Gas.of(20);

        Drive seaToPort = Drive.of(halfTank, sea, port);

        assertFalse(seaToPort.isTakeable(fullTank));
        assertTrue(seaToPort.isTakeable(emptyTank));
    }
}