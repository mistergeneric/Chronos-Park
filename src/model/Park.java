package model;

import locations.Location;
import locations.Ride;

import java.util.*;

//park is the graph
//the best directed weighted graph with the simplest implementation i found here https://stackoverflow.com/questions/41351353/weighted-directed-graph-implementation-in-java-bellman-ford
public class Park {
    private HashMap<Location, List<Route>> adjacencyMap;
    private int edges;
    private List<Ride> allRides;
    private List<Location> allLocations;
    private LinkedList<Route>[] adjacencylist;


    public Park() {
        adjacencyMap = new HashMap<>();
        allRides = new ArrayList<>();
        allLocations = new ArrayList<>();
        edges = 0;
    }

    public HashMap<Location, List<Route>> getAdjacencyMap() {
        return adjacencyMap;
    }

    public void setAdjacencyMap(HashMap<Location, List<Route>> adjacencyMap) {
        this.adjacencyMap = adjacencyMap;
    }

    public int getEdges() {
        return edges;
    }

    public void setEdges(int edges) {
        this.edges = edges;
    }

    public LinkedList<Route>[] getAdjacencylist() {
        return adjacencylist;
    }

    public void setAdjacencylist(LinkedList<Route>[] adjacencylist) {
        this.adjacencylist = adjacencylist;
        for (int i = 0; i <allLocations.size() ; i++) {
            this.adjacencylist[i] = new LinkedList<>();
        }
     }

    public List<Ride> getAllRides() {
        return allRides;
    }

    public void setAllRides(List<Ride> allRides) {
        this.allRides = allRides;
    }

    public List<Location> getAllLocations() {
        return allLocations;
    }

    public void setAllLocations(List<Location> allLocations) {
        this.allLocations = allLocations;
    }

    private List<Route> addRideToPark(Location ride) {
        List<Route> routes = new ArrayList<>();
        this.adjacencyMap.put(ride, routes);
        return routes;
    }

    public void addRoute(Location source, Location destination, double weight) {

        List<Route> sourceRoutes = this.adjacencyMap.get(source);
        List<Route> destinationRoutes = this.adjacencyMap.get(destination);

        if (sourceRoutes == null) {
            sourceRoutes = this.addRideToPark(source);
        }
        if (destinationRoutes == null) {
            this.addRideToPark(destination);
        }

        Route routeToAdd = new Route(source, destination, weight);
        sourceRoutes.add(routeToAdd);
        edges++;
        this.adjacencyMap.put(source, sourceRoutes);

        adjacencylist[allLocations.indexOf(destination)].addFirst(routeToAdd); //for undirected graph
    }

    /*
    this is a nightmare, bellmanFord shortest path
     */
    public void shortestPathFromEntrance(Location start, List<Location> recommended) {
        //this stores our distances from source
        double[] distance = new double[this.getAllLocations().size()];

        Location[] previous = new Location[this.getAllLocations().size()];
        for (int i = 0; i < this.getAllLocations().size(); i++) {
            distance[i] = Double.MAX_VALUE / 2;
        }

        distance[getAllLocations().indexOf(start)] = 0;

        /*
        The hard part is transforming bellman ford's edge relaxation below into using objects as pretty much everything
        I've seen uses ints, this is what is happening below
         */

        for (int i = 1; i < this.getAllLocations().size() - 1; i++) {
            for (int j = 0; j < this.getAllLocations().size() - 1; j++) {
                Iterator<Route> iterator = this.adjacencyMap.get(this.getAllLocations().get(j)).iterator();
                while (iterator.hasNext()) {
                    Route route = iterator.next();

                    int sourceIndex = this.getAllLocations().indexOf(route.getSource());
                    int destinationIndex = this.getAllLocations().indexOf(route.getDestination());

                    if (distance[sourceIndex] + route.getWeight() < distance[destinationIndex]) {
                        distance[destinationIndex] = distance[sourceIndex] + route.getWeight();
                        previous[destinationIndex] = route.getDestination();
                    }
                }
            }
        }
        System.out.println("Distance in (m) from entrance");
        for (int i = 0; i < this.getAllLocations().size(); i++) {
            if (previous[i] != null || distance[i] != 0.0) {
                if(recommended.contains(previous[i])) {
                    System.out.println(i + "\t   " + distance[i] + " " + previous[i]);
                }
            }
        }
    }

    //https://algorithms.tutorialhorizon.com/prims-minimum-spanning-tree-mst-using-adjacency-list-and-min-heap/
    /*
    The following methods are from the above implementation, modified so they work with my implementation
     */

    public void primMST() {


        boolean[] inPriorityQueue = new boolean[allLocations.size()];
        Results[] resultSet = new Results[allLocations.size()];
        double[] key = new double[allLocations.size()];

        HeapNode[] heapNodes = new HeapNode[allLocations.size()];
        for (int i = 0; i < allLocations.size(); i++) {
            heapNodes[i] = new HeapNode();
            heapNodes[i].vertex = allLocations.get(i);
            heapNodes[i].key = Integer.MAX_VALUE;
            resultSet[i] = new Results();
            resultSet[i].parent = null;
            inPriorityQueue[i] = true;
            key[i] = Integer.MAX_VALUE;
        }

        heapNodes[0].key = 0;

        PriorityQueue<HeapNode> pq = new PriorityQueue<HeapNode>(allLocations.size(),
                new Comparator<HeapNode>() {
                    @Override
                    public int compare(HeapNode o1, HeapNode o2) {
                        return (int) (o1.key -o2.key);
                    }
                });        //add all the vertices to priority queue
        for (int i = 0; i < allLocations.size(); i++) {
            pq.offer(heapNodes[i]);
        }

        while (!pq.isEmpty()) {
            HeapNode extractedNode = pq.poll();

            Location extractedVertex = extractedNode.vertex;

            LinkedList<Route> list = adjacencylist[allLocations.indexOf(extractedVertex)];
            for (int i = 0; i < list.size(); i++) {
                Route route = list.get(i);
                if (inPriorityQueue[allLocations.indexOf(route.getDestination())]) {
                    inPriorityQueue[allLocations.indexOf(extractedVertex)] = false;
                    Location destination = route.getDestination();
                    double newKey = route.getWeight();
                    if (key[allLocations.indexOf(destination)] > newKey) {
                        decreaseKey(pq, newKey, destination);
                        resultSet[allLocations.indexOf(destination)].parent = extractedVertex;
                        resultSet[allLocations.indexOf(destination)].weight = newKey;
                        key[allLocations.indexOf(destination)] = newKey;
                    }
                }
            }
        }
        //print mst
        printMST(resultSet);
    }

    public void decreaseKey(PriorityQueue<HeapNode> pq, double newKey, Location vertex){

        Iterator it = pq.iterator();

        while (it.hasNext()) {
            HeapNode heapNode = (HeapNode) it.next();
            if(heapNode.vertex==vertex) {
                pq.remove(heapNode);
                heapNode.key = newKey;
                pq.offer(heapNode);
                break;
            }
        }
    }
    public void printMST(Results[] resultSet){
        int total_min_weight = 0;
        for (int i = 0; i < allLocations.size() - 1; i++) {
                System.out.println("Edge: " + allLocations.get(i) + " - " + resultSet[i + 1].parent +
                        " Distance: " + resultSet[i].weight);
                total_min_weight += resultSet[i].weight;
        }
    }


}
