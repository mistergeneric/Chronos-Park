package model;

import locations.Location;
import locations.Ride;

//route is the edge
public class Route {
    private Location source;
    private Location destination;
    private double weight; //weight is the metres to travel


    public Route(Location source, Location destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;


    }

    public Location getSource() {
        return source;
    }

    public Location getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }

}
