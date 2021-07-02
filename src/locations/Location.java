package locations;

public class Location {

    private String name;

    public Location(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEntrance() {
        if(name.equals("Entrance")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
