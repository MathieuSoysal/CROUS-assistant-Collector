package io.github.mathieusoysal.residence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import io.github.mathieusoysal.residence.Transport;
import io.github.mathieusoysal.residence.TransportKind;
import io.github.mathieusoysal.residence.TransportUnitOfMeasure;

class TransportTest {

    @Test
    void testEquals() {
        Transport transport1 = new Transport(TransportKind.BUS, "Bus to downtown", 10, TransportUnitOfMeasure.ON_FOOT);
        Transport transport2 = new Transport(TransportKind.BUS, "Bus to downtown", 10, TransportUnitOfMeasure.ON_FOOT);
        Transport transport3 = new Transport(TransportKind.TRAIN, "Train to downtown", 10,
                TransportUnitOfMeasure.ON_FOOT);

        assertEquals(transport1, transport2);
        assertNotEquals(transport1, transport3);
    }

    @Test
    void testGetters() {
        Transport transport = new Transport(TransportKind.BUS, "Bus to downtown", 10, TransportUnitOfMeasure.ON_FOOT);

        assertEquals(TransportKind.BUS, transport.getKind());
        assertEquals("Bus to downtown", transport.getDescription());
        assertEquals(10, transport.getDistance());
        assertEquals(TransportUnitOfMeasure.ON_FOOT, transport.getUnitOfMeasure());
    }

}