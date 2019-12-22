package id.ac.polinema.dtsfit;

import android.content.SharedPreferences;

public class Profile {

    private SharedPreferences preferences;

    public Profile(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void setName(String name) {
        preferences.edit()
                .putString(Constant.KEY_NAME, name)
                .apply();
    }

    public String getName() {
        return preferences.getString(Constant.KEY_NAME, null);
    }

    public void setGender(String gender) {
        preferences.edit()
                .putString(Constant.KEY_GENDER, gender)
                .apply();
    }

    public String getGender() {
        return preferences.getString(Constant.KEY_GENDER, null);
    }

    public void setHeight(int height) {
        preferences.edit()
                .putInt(Constant.KEY_HEIGHT, height)
                .apply();
    }

    public int getHeight() {
        return preferences.getInt(Constant.KEY_HEIGHT, 0);
    }

    public void setWeight(int weight) {
        preferences.edit()
                .putInt(Constant.KEY_WEIGHT, weight)
                .apply();
    }

    public int getWeight() {
        return preferences.getInt(Constant.KEY_WEIGHT, 0);
    }

    public void setAge(int age) {
        preferences.edit()
                .putInt(Constant.KEY_AGE, age)
                .apply();
    }

    public int getAge() {
        return preferences.getInt(Constant.KEY_AGE, 0);
    }

    public void setActivity(int activity) {
        preferences.edit()
                .putInt(Constant.KEY_ACTIVITY, activity)
                .apply();
    }

    public int getActivity() {
        return preferences.getInt(Constant.KEY_ACTIVITY, 0);
    }

    public void setBmr(float bmr) {
        preferences.edit()
                .putFloat(Constant.KEY_BMR, bmr)
                .apply();
    }

    public float getBmr() {
        return preferences.getFloat(Constant.KEY_BMR, 0);
    }
}

