package Data;

import java.io.*;
import java.util.HashMap;

public class IDandPasswords {
    HashMap<String, String> logininfo = new HashMap<>();

    FileReader reader = new FileReader("src/Data/userData.txt");
    protected String userID;
    protected String userPass;

    public IDandPasswords() throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("=");
                if (data.length == 2) {
                userID = data[0];
                userPass = data[1];

                logininfo.put(userID, userPass);
                }
            }
        reader.close();
        } catch (IOException ex) {
        throw new RuntimeException(ex);
        }
    }

    public HashMap<String, String> getLogininfo() {
        return logininfo;
    }
}
