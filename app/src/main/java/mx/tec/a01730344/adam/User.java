package mx.tec.a01730344.adam;

import android.content.Context;
import android.util.Log;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class User {
    private static final String FILENAME = "prueba8.xml";
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
        profiles.setProperty(numUser+"num", numUser);
        profiles.setProperty(numUser, username);
        profiles.setProperty(numUser+"image", String.valueOf(image));
        profiles.setProperty(numUser+"mini", String.valueOf(mini));
        profiles.setProperty(numUser+"scoreR", "0");
        profiles.setProperty(numUser+"scoreC", "0");
        profiles.setProperty(numUser+"scoreF", "0");

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
            profiles.setProperty("currentUserNum", "user1");
            profiles.setProperty("currentUserImage", "");
            profiles.setProperty("currentUserMini", "");
            profiles.setProperty("currentUserNumber", "");
            profiles.setProperty("currentUserScoreR", "0");
            profiles.setProperty("currentUserScoreC", "0");
            profiles.setProperty("currentUserScoreF", "0");
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

    public void deleteUser(String user) {
        loadProfiles();

        String user3, user3Image, user3Mini, user2, user2Image, user2Mini, user3scoreR, user3scoreC, user3scoreF,user2scoreR, user2scoreC, user2scoreF;
        switch (user) {
            case "user1":
                user2 = profiles.getProperty("user2");
                user3 = profiles.getProperty("user3");

                if(user2 == null) {
                    profiles.remove("user1");
                    profiles.remove("user1image");
                    profiles.remove("user1mini");
                    profiles.remove("user1scoreR");
                    profiles.remove("user1scoreC");
                    profiles.remove("user1scoreT");
                } else if(user3 == null) {
                    user2Image = profiles.getProperty("user2image");
                    user2Mini = profiles.getProperty("user2mini");
                    user2scoreR = profiles.getProperty("user2scoreR");
                    user2scoreC = profiles.getProperty("user2scoreC");
                    user2scoreF = profiles.getProperty("user2scoreF");
                    profiles.setProperty("user1", user2);
                    profiles.setProperty("user1image", user2Image);
                    profiles.setProperty("user1mini", user2Mini);
                    profiles.setProperty("user1scoreR",user2scoreR);
                    profiles.setProperty("user1scoreC",user2scoreC);
                    profiles.setProperty("user1scoreF",user2scoreF);

                    profiles.remove("user2");
                    profiles.remove("user2image");
                    profiles.remove("user2mini");
                    profiles.remove("user2scoreR");
                    profiles.remove("user2scoreC");
                    profiles.remove("user2scoreT");
                } else {
                    user2Image = profiles.getProperty("user2image");
                    user2Mini = profiles.getProperty("user2mini");
                    user2scoreR = profiles.getProperty("user2scoreR");
                    user2scoreC = profiles.getProperty("user2scoreC");
                    user2scoreF = profiles.getProperty("user2scoreF");
                    profiles.setProperty("user1", user2);
                    profiles.setProperty("user1image", user2Image);
                    profiles.setProperty("user1mini", user2Mini);
                    profiles.setProperty("user1scoreR",user2scoreR);
                    profiles.setProperty("user1scoreC",user2scoreC);
                    profiles.setProperty("user1scoreF",user2scoreF);

                    user3Image = profiles.getProperty("user3image");
                    user3Mini = profiles.getProperty("user3mini");
                    user3scoreR = profiles.getProperty("user3scoreR");
                    user3scoreC = profiles.getProperty("user3scoreC");
                    user3scoreF = profiles.getProperty("user3scoreF");
                    profiles.setProperty("user2",user3);
                    profiles.setProperty("user2image", user3Image);
                    profiles.setProperty("user2mini", user3Mini);
                    profiles.setProperty("user2scoreR",user3scoreR);
                    profiles.setProperty("user2scoreC",user3scoreC);
                    profiles.setProperty("user2scoreF",user3scoreF);

                    profiles.remove("user3");
                    profiles.remove("user3image");
                    profiles.remove("user3mini");
                    profiles.remove("user3scoreR");
                    profiles.remove("user3scoreC");
                    profiles.remove("user3scoreT");
                }
                break;

            case "user2":
                user3 = profiles.getProperty("user3");

                if(user3 == null) {
                    profiles.remove("user2");
                    profiles.remove("user2image");
                    profiles.remove("user2mini");
                    profiles.remove("user2scoreR");
                    profiles.remove("user2scoreC");
                    profiles.remove("user2scoreT");
                } else {
                    user3Image = profiles.getProperty("user3image");
                    user3Mini = profiles.getProperty("user3mini");
                    user3scoreR = profiles.getProperty("user3scoreR");
                    user3scoreC = profiles.getProperty("user3scoreC");
                    user3scoreF = profiles.getProperty("user3scoreF");
                    profiles.setProperty("user2",user3);
                    profiles.setProperty("user2image", user3Image);
                    profiles.setProperty("user2mini", user3Mini);
                    profiles.setProperty("user2scoreR",user3scoreR);
                    profiles.setProperty("user2scoreC",user3scoreC);
                    profiles.setProperty("user2scoreF",user3scoreF);

                    profiles.remove("user3");
                    profiles.remove("user3image");
                    profiles.remove("user3mini");
                    profiles.remove("user3scoreR");
                    profiles.remove("user3scoreC");
                    profiles.remove("user3scoreT");
                }
                break;

            default:
                profiles.remove("user3");
                profiles.remove("user3image");
                profiles.remove("user3mini");
                profiles.remove("user3scoreR");
                profiles.remove("user3scoreC");
                profiles.remove("user3scoreT");
                break;
        }
        saveProfiles();
    }

    public void setCurrentUser(String user, int image, int mini, String number, int scoreR, int scoreC, int scoreF){
        profiles.setProperty("currentUserNum", number);
        profiles.setProperty("currentUser", user);
        profiles.setProperty("currentUserImage", String.valueOf(image));
        profiles.setProperty("currentUserMini", String.valueOf(mini));
        profiles.setProperty("currentUserScoreR", String.valueOf(scoreR));
        profiles.setProperty("currentUserScoreC", String.valueOf(scoreC));
        profiles.setProperty("currentUserScoreF", String.valueOf(scoreF));
        saveProfiles();

    }

    public void updateScore(int score, String user, int game) {
        String stringScore = String.valueOf(score);
        if (game == 0) {
            if (score > getCurrentUserScoreR()) {
                profiles.setProperty("currentUserScoreR", stringScore);
                profiles.setProperty(user+"scoreR",stringScore);
            }
        }
        else if (game == 1){
            if (score > getCurrentUserScoreC()) {
                profiles.setProperty("currentUserScoreC", stringScore);
                profiles.setProperty(user+"scoreC",stringScore);
            }
        }
        else if (game == 2){
            if (score > getCurrentUserScoreF()) {
                profiles.setProperty("currentUserScoreF", stringScore);
                profiles.setProperty(user+"scoreF",stringScore);
            }
        }
        saveProfiles();
    }

    public String getUsername(String user){
        return profiles.getProperty(user);
    }

    public String getUserNumber(String userNum){
        return profiles.getProperty(userNum);
    }

    public int getImage(String image){
        return Integer.parseInt(profiles.getProperty(image));
    }

    public int getScoreR(String scoreR){
        return Integer.parseInt(profiles.getProperty(scoreR));
    }

    public int getScoreC(String scoreC){
        return Integer.parseInt(profiles.getProperty(scoreC));
    }

    public int getScoreF(String scoreF){
        return Integer.parseInt(profiles.getProperty(scoreF));
    }

    public int getCount(){
        return Integer.parseInt(profiles.getProperty("userCount"));
    }


    public String getCurrentUser(){
        return getUsername("currentUser");
    }

    public String getCurrentUserNumber(){ return getUserNumber("currentUserNum"); }

    public int getCurrentUserImage(){
        return getImage("currentUserImage");
    }

    public int getCurrentUserMini(){
        return getImage("currentUserMini");
    }

    public int getCurrentUserScoreR(){
        return getScoreR("currentUserScoreR");
    }

    public int getCurrentUserScoreC(){
        return getScoreC("currentUserScoreC");
    }

    public int getCurrentUserScoreF(){
        return getScoreF("currentUserScoreF");
    }
}
