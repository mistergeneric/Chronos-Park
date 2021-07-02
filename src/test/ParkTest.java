package test;

import locations.Location;
import model.Park;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Utils;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertTrue;


class ParkTest {
     Park park;

     @BeforeEach
     public void setUp() {
         park =  Utils.generatePark();
     }

     @Test
     public void addRoute() {
         Location testLocation = new Location("Yes");
         park.addRoute(park.getAllLocations().get(0), testLocation, 10);

         assertTrue(park.getAllLocations().contains(testLocation));
     }
 }
