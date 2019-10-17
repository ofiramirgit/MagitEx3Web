package WebLogic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Logic {
    public Boolean userExist(String username)
    {
        File file = new File("C:\\magit-ex3\\users.txt");
        String[] usersArray = getContentOfFile(file).split(System.lineSeparator());
        return Arrays.asList(usersArray).contains(username);
    }

    public String getContentOfFile(File i_File) {
        String fileContent = "";
        Path path = Paths.get(i_File.getAbsolutePath());

        try {
            fileContent = "";
            if (path.toFile().length() > 0)
                fileContent = new String(Files.readAllBytes(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileContent;
    }

    public List<String> getNotifications(String username) {
        File file = new File("C:\\magit-ex3\\"+username+"\\NOTIFICATIONS.txt");
        List<String> listNotifications = Arrays.asList(getContentOfFile(file).split(System.lineSeparator()));
        return listNotifications;
    }

    public ArrayList<String> getUsers(String username) {
        File file = new File("C:\\magit-ex3\\users.txt");
//        String[] usersArray = getContentOfFile(file).split(System.lineSeparator());
        ArrayList<String> listUsers = new ArrayList<String>(Arrays.asList(getContentOfFile(file).split(System.lineSeparator())));
        listUsers.remove(username);
        return listUsers;
    }

    public ArrayList<String> getUserRepositories(String username){
        File[] directories = new File("C:\\magit-ex3\\"+username+"\\repositories").listFiles(File::isDirectory);;
        ArrayList<String> directoriesList = new ArrayList<String>();
        for(File folder: directories){
            directoriesList.add(folder.getName());
        }
        return directoriesList;
    }
}
