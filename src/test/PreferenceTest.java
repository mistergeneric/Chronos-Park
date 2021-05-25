//andrew ncneill
package test;

import model.Preference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PreferenceTest {

    @Test
    public void getPreferences() {
        Preference preference = new Preference(true, "Test String");
        Assertions.assertTrue(preference.isRideRecommended());
        Assertions.assertEquals("Test String", preference.getPreferenceString());
    }
}