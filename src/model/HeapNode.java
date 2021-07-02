package model;

import locations.Location;

//    //https://algorithms.tutorialhorizon.com/prims-minimum-spanning-tree-mst-using-adjacency-list-and-min-heap/ part of prim mst heap node implementation, methods in park class
//modified to work with my code
public class HeapNode {

    Location vertex;
    double key;

    public Location getVertex() {
        return vertex;
    }

    public void setVertex(Location vertex) {
        this.vertex = vertex;
    }

    public double getKey() {
        return key;
    }


    public void setKey(double key) {
        this.key = key;
    }
}
