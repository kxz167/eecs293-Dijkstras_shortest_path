package test;

import org.junit.Test;
import static org.junit.Assert.*;

import interest.*;
import optimizations.*;

public class DestinationTest{
    @Test
    public void testInitiation(){
        Destination sea = Destination.of("Seattle");
        Destination port = Destination.of("Portland");
        Destination los = Destination.of("Los Angelas");

        assertEquals("Seattle", sea.getName());
        assertEquals(null, port.getLowestCost());
        assertEquals(null, los.getLowestCost());

        assertFalse(sea.isCostKnown());
        assertTrue(sea.getLinks().isEmpty());
    }

    @Test
    public void testAugmentation(){
        Destination sea = Destination.of("Seattle");
        Destination port = Destination.of("Portland");

        Gas emptyTank = Gas.of(0);
        Gas halfTank = Gas.of(10);

        Drive seaToPort = Drive.of(halfTank, sea, port);

        sea.setLowestCost(emptyTank);
        assertEquals(emptyTank, sea.getLowestCost());

        port.setPreviousNode(sea);
        assertEquals(sea, port.getPreviousNode());

        sea.addLink(seaToPort);
        assertTrue(sea.getLinks().contains(seaToPort));
    }

    @Test
    public void testBadData(){
        Destination sea = Destination.of("Seattle");
        Destination port = Destination.of("Portland");

        Gas halfTank = Gas.of(10);

        Drive seaToPort = Drive.of(halfTank, sea, port);
        //Incorect starting location
        port.addLink(seaToPort);
        assertTrue(port.getLinks().isEmpty());

        //Bad link
        port.addLink(null);
        assertTrue(port.getLinks().isEmpty());
    }

    @Test
    public void testCompareTo(){
        Destination sea = Destination.of("Seattle");
        Destination port = Destination.of("Portland");
        
        Gas emptyTank = Gas.of(0);
        Gas halfTank = Gas.of(10);
        Gas nullTank = null;

        //Not an instance and is null
        assertEquals(-1, sea.compareTo(null));
        //Is an instance and is null
        assertEquals(-1, sea.compareTo(port));
        
        sea.setLowestCost(emptyTank);
        port.setLowestCost(halfTank);

        //Not an instance and not null
        assertEquals(-1, sea.compareTo(null));
        //Is an instance and not null
        assertEquals(-10, sea.compareTo(port));
        assertEquals(10, port.compareTo(sea));
    }
}