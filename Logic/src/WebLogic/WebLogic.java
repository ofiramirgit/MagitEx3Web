package WebLogic;

import Logic.Logic;
import WebLogic.WebObjects.Notification;
import WebLogic.WebObjects.Repository;
import static Logic.ConstantsEnums.dateFormat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class WebLogic {

    private Logic logicManager = new Logic();

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

    public ArrayList<Notification> getNotifications(String username) {
        String userLastLoggedIn = getUserLastLogedIn(username);
        File file = new File("C:\\magit-ex3\\"+username+"\\NOTIFICATIONS.txt");
        String[] arrayNotifications = getContentOfFile(file).split(System.lineSeparator());
        ArrayList<Notification> listNotifications = new ArrayList<Notification>();
        for(String notification: arrayNotifications)
        {
            Notification noti = new Notification(notification);
            try {
                Date notiDate = dateFormat.parse(noti.getM_CreatedTime());
                Date lastLoggedInDate = dateFormat.parse(userLastLoggedIn);
                if (notiDate.compareTo(lastLoggedInDate) > 0) {
                    listNotifications.add(noti);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        setUserLastLogedIn(username);
        return listNotifications;
    }


    public ArrayList<String> getUsers(String username) {
        File file = new File("C:\\magit-ex3\\users.txt");
        ArrayList<String> listUsers = new ArrayList<String>(Arrays.asList(getContentOfFile(file).split(System.lineSeparator())));
        listUsers.remove(username);
        return listUsers;
    }

    public ArrayList<Repository> getUserRepositories(String username){
        File[] repositories = new File("C:\\magit-ex3\\"+username+"\\repositories").listFiles(File::isDirectory);
        ArrayList<Repository> directoriesList = new ArrayList<>();

        for(File repoFile: repositories){
            directoriesList.add(getRepositoryDetails(repoFile));
        }
        return directoriesList;
    }

    public Repository getRepositoryDetails(File repoFile) {
        File branchesDirectory = new File(repoFile.getPath() + File.separator + ".magit" + File.separator +"branches");
        File headBranchFile = new File( branchesDirectory +File.separator + "HEAD.txt");
        File activeBranchFile = new File(branchesDirectory +File.separator + logicManager.getContentOfFile(headBranchFile) +".txt");
        String commitDetails = logicManager.getContentOfZipFile(repoFile + File.separator + ".magit" + File.separator + "objects", logicManager.getContentOfFile(activeBranchFile));
        Integer branchesNum = getTextFiles(branchesDirectory)-2;

        Repository repo = new Repository();
        repo.setName(repoFile.getName());
        repo.setActiveBranch(activeBranchFile.getName().replace(".txt",""));
        repo.setBranchesNumber(branchesNum.toString());
        repo.setLastCommitTime(commitDetails.split(",")[3]);
        repo.setLastCommitMsg(commitDetails.split(",")[2]);
        return repo;
    }

    private Integer getTextFiles(File branchesDirectory) {
        Integer num =0;
        for(File f : branchesDirectory.listFiles()){
            if(f.isFile())
                num++;
        }
        return  num;
    }

    public String getUserLastLogedIn(String username) {
        File file = new File("C:\\magit-ex3\\"+username+"\\LAST_LOGGED_IN.txt");
        String lastLogedIn = getContentOfFile(file);
        return lastLogedIn;
    }

    public void setUserLastLogedIn(String username) {
        File file = new File("C:\\magit-ex3\\"+username+"\\LAST_LOGGED_IN.txt");
        String lastLogedIn = dateFormat.format(new Date());
        try {
            file.delete();
            Files.write(Paths.get(file.toString()), lastLogedIn.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
