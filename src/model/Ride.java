//andrew ncneill
package model;

public class Ride {
    private String name;
    private float minimumHeight;
    private float maximumHeight;
    private boolean wheelchair;
    private int smallestNumberCanRide;
    private int biggestNumberCanRide;
    private boolean adrenaline;
    private boolean kidFriendly;
    private boolean horror;
    private String theme;
    private boolean water;

    public Ride(String name, float minimumHeight, float maximumHeight, boolean wheelchair, int smallestNumberCanRide, int biggestNumberCanRide, boolean adrenaline, boolean kidFriendly, boolean horror, String theme, boolean water) {
        this.name = name;
        this.minimumHeight = minimumHeight;
        this.maximumHeight = maximumHeight;
        this.wheelchair = wheelchair;
        this.smallestNumberCanRide = smallestNumberCanRide;
        this.biggestNumberCanRide = biggestNumberCanRide;
        this.adrenaline = adrenaline;
        this.kidFriendly = kidFriendly;
        this.horror = horror;
        this.theme = theme;
        this.water = water;
    }

    public Ride(String name, float minimumHeight, float maximumHeight, boolean wheelchair, int smallestNumberCanRide, int biggestNumberCanRide, boolean adrenaline, boolean kidFriendly, boolean horror, boolean water) {
        this.name = name;
        this.minimumHeight = minimumHeight;
        this.maximumHeight = maximumHeight;
        this.wheelchair = wheelchair;
        this.smallestNumberCanRide = smallestNumberCanRide;
        this.biggestNumberCanRide = biggestNumberCanRide;
        this.adrenaline = adrenaline;
        this.kidFriendly = kidFriendly;
        this.horror = horror;
        this.water = water;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMinimumHeight() {
        return minimumHeight;
    }

    public void setMinimumHeight(float minimumHeight) {
        this.minimumHeight = minimumHeight;
    }

    public float getMaximumHeight() {
        return maximumHeight;
    }

    public void setMaximumHeight(float maximumHeight) {
        this.maximumHeight = maximumHeight;
    }

    public boolean isWheelchair() {
        return wheelchair;
    }

    public void setWheelchair(boolean wheelchair) {
        this.wheelchair = wheelchair;
    }

    public int getSmallestNumberCanRide() {
        return smallestNumberCanRide;
    }

    public void setSmallestNumberCanRide(int smallestNumberCanRide) {
        this.smallestNumberCanRide = smallestNumberCanRide;
    }

    public int getBiggestNumberCanRide() {
        return biggestNumberCanRide;
    }

    public void setBiggestNumberCanRide(int biggestNumberCanRide) {
        this.biggestNumberCanRide = biggestNumberCanRide;
    }

    public boolean isAdrenaline() {
        return adrenaline;
    }

    public void setAdrenaline(boolean adrenaline) {
        this.adrenaline = adrenaline;
    }

    public boolean isKidFriendly() {
        return kidFriendly;
    }

    public void setKidFriendly(boolean kidFriendly) {
        this.kidFriendly = kidFriendly;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public boolean isHorror() {
        return horror;
    }

    public void setHorror(boolean horror) {
        this.horror = horror;
    }

    public boolean getPreference(String type) {
        switch (type) {
            case "wheelchair":
                return isWheelchair();
            case "water":
                return isWater();
            case "horror":
                return isHorror();
            case "kids":
                return isKidFriendly();
            case "adrenaline":
                return isAdrenaline();
        }
        return false;
    }
}
