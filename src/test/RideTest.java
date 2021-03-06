//andrew ncneill
package test;

import locations.Ride;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RideTest {
    @Test
    public void rideTest() {
        Ride ride = new Ride("test", 0, 5, false, 1, 2, false, false, false, false);
        assertEquals("test", ride.getName());
        assertEquals(0, ride.getMinimumHeight());
        assertEquals(5, ride.getMaximumHeight());
        assertFalse(ride.isWheelchair());
        assertEquals(1, ride.getSmallestNumberCanRide());
        assertEquals(2, ride.getBiggestNumberCanRide());
        assertFalse(ride.isAdrenaline());
        assertFalse(ride.isWater());
        assertFalse(ride.isHorror());

    }

    @Test
    void getPreference() {
        Ride ride = new Ride("test", 0, 5, true, 1, 2, false, false, false, false);
        assertFalse(ride.getPreference("water"));
        assertFalse(ride.getPreference("horror"));
        assertTrue(ride.getPreference("wheelchair"));

    }
}