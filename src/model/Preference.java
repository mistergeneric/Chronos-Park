package model;

public class Preference {
    private boolean isRideRecommended;
    private String preferenceString;

    public Preference(boolean isRideRecommended, String preferenceString) {
        this.isRideRecommended = isRideRecommended;
        this.preferenceString = preferenceString;
    }

    public boolean isRideRecommended() {
        return isRideRecommended;
    }

    public String getPreferenceString() {
        return preferenceString;
    }

}
