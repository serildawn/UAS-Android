package id.ac.polinema.dtsfit;

import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class Application extends android.app.Application {

    private static SharedPreferences preferences;
    private static Profile profile;

    @Override
    public void onCreate() {
        super.onCreate();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        profile = new Profile(preferences);
    }

    public static SharedPreferences providePreferences() {
        return preferences;
    }

    public static Profile provideProfile() {
        return profile;
    }
}
