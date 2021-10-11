package mx.tec.a01730344.adam;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class User {
    private static final String FILENAME = "prueba3.xml";
    private Properties profiles = new Properties();
    Context context;

    public User(Context context) {
        this.context = context;
    }

    public void saveUser(String username, int image, int mini){
        loadProfiles();
        int users = Integer.parseInt(profiles.getProperty("userCount"));
        users = users + 1;
        String numUser = "user" + String.valueOf(users);
        profiles.setProperty("userCount", String.valueOf(users));
        profiles.setProperty(numUser, username);
        profiles.setProperty(numUser+"image", String.valueOf(image));
        profiles.setProperty(numUser+"mini", String.valueOf(mini));
        saveProfiles();
    }

    public void modifyUser(String userNumber, String username, int image, int mini) {
        loadProfiles();
        profiles.setProperty(userNumber, username);
        profiles.setProperty(userNumber+"image", String.valueOf(image));
        profiles.setProperty(userNumber+"mini", String.valueOf(mini));
        saveProfiles();
    }

    private void saveProfiles() {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            profiles.storeToXML(fos, null);
            fos.close();
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }


    public void loadProfiles() {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            profiles.loadFromXML(fis);
            fis.close();
        }
        catch (FileNotFoundException fnfe){
            profiles.setProperty("userCount","0");
            profiles.setProperty("currentUser", "");
            profiles.setProperty("currentUserImage", "");
            profiles.setProperty("currentUserMini", "");
            profiles.setProperty("currentUserNumber", "");
            saveProfiles();
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void minusCount(){
        Log.d("USER", "username: " + profiles.getProperty("user1"));
        Log.d("USER", "minusCount: " + profiles.getProperty("userCount"));
        loadProfiles();
        try{
            int users = Integer.parseInt(profiles.getProperty("userCount"));
            users = users - 1;
            profiles.setProperty("userCount", String.valueOf(users));
            saveProfiles();
            Log.d("USER", "minusCount: " + profiles.getProperty("userCount"));
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    public String getUsername(String user){
        return profiles.getProperty(user);
    }

    public int getImage(String image){
        return Integer.parseInt(profiles.getProperty(image));
    }

    public int getCount(){
        return Integer.parseInt(profiles.getProperty("userCount"));
    }

    public void deleteUser(String user) {
        loadProfiles();

        String user3, user3Image, user3Mini, user2, user2Image, user2Mini;
        switch (user) {
            case "user1":
                user2 = profiles.getProperty("user2");
                user3 = profiles.getProperty("user3");

                if(user2 == null) {
                    profiles.remove("user1");
                    profiles.remove("user1image");
                    profiles.remove("user1mini");
                } else if(user3 == null) {
                    user2Image = profiles.getProperty("user2image");
                    user2Mini = profiles.getProperty("user2mini");
                    profiles.setProperty("user1", user2);
                    profiles.setProperty("user1image", user2Image);
                    profiles.setProperty("user1mini", user2Mini);

                    profiles.remove("user2");
                    profiles.remove("user2image");
                    profiles.remove("user2mini");
                } else {
                    user2Image = profiles.getProperty("user2image");
                    user2Mini = profiles.getProperty("user2mini");
                    profiles.setProperty("user1", user2);
                    profiles.setProperty("user1image", user2Image);
                    profiles.setProperty("user1mini", user2Mini);

                    user3Image = profiles.getProperty("user3image");
                    user3Mini = profiles.getProperty("user3mini");
                    profiles.setProperty("user2",user3);
                    profiles.setProperty("user2image", user3Image);
                    profiles.setProperty("user2mini", user3Mini);

                    profiles.remove("user3");
                    profiles.remove("user3image");
                    profiles.remove("user3mini");
                }
                break;

            case "user2":
                user3 = profiles.getProperty("user3");

                if(user3 == null) {
                    profiles.remove("user2");
                    profiles.remove("user2image");
                    profiles.remove("user2mini");
                } else {
                    user3Image = profiles.getProperty("user3image");
                    user3Mini = profiles.getProperty("user3mini");

                    profiles.setProperty("user2",user3);
                    profiles.setProperty("user2image", user3Image);
                    profiles.setProperty("user2mini", user3Mini);

                    profiles.remove("user3");
                    profiles.remove("user3image");
                    profiles.remove("user3mini");
                }
                break;

            default:
                profiles.remove("user3");
                profiles.remove("user3image");
                profiles.remove("user3mini");
                break;
        }
        saveProfiles();
    }

    public void setCurrentUser(String user, int image, int mini, String number){
        profiles.setProperty("currentUserNumber", number);
        profiles.setProperty("currentUser", user);
        profiles.setProperty("currentUserImage", String.valueOf(image));
        profiles.setProperty("currentUserMini", String.valueOf(mini));
        saveProfiles();

    }

    public String getCurrentUser(){
        return getUsername("currentUser");
    }

    public int getCurrentUserImage(){
        return getImage("currentUserImage");
    }

    public int getCurrentUserMini(){
        return getImage("currentUserMini");
    }

    public String getCurrentUserNumber(){
        return getUsername("currentUserNumber");
    }

    // results.setProperty(selected, Integer.toString(count));

}
