package test;

import org.junit.Test;
import static org.junit.Assert.*;

import interest.*;
import optimizations.*;

public class GasTest{
    @Test
    public void testInitialization(){
        Gas emptyTank = Gas.of(0);
        Gas halfTank = Gas.of(10);
        Gas fullTank = Gas.of(20);

        assertEquals(0, (int)emptyTank.getWeight());
        assertEquals(10, (int)halfTank.getWeight());
        assertEquals(20, (int)fullTank.getWeight());
    }

    @Test
    public void testBadData(){
        Gas errorTank = Gas.of(-324);

        assertEquals(0, (int)errorTank.getWeight());
    }

    @Test
    public void testWeightSum(){
        Gas emptyTank = Gas.of(0);
        Gas halfTank = Gas.of(10);
        Gas fullTank = Gas.of(20);

        assertEquals(30, (int)halfTank.weightSumWith(fullTank).getWeight());
        assertEquals(10, (int)emptyTank.weightSumWith(halfTank).getWeight());
    }

    @Test
    public void testCompareTo(){
        Gas emptyTank = Gas.of(0);
        Gas halfTank = Gas.of(10);
        Gas fullTank = Gas.of(20);

        //Branch test not weight
        assertEquals(-1,(int) emptyTank.compareTo(null));

        //Branch test return difference
        assertEquals(-10,(int) emptyTank.compareTo(halfTank));


    }
}