package WebLogic;

import Logic.Logic;
import WebLogic.WebObjects.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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

    public ArrayList<Repository> getUserRepositories(String username){
        File[] repositories = new File("C:\\magit-ex3\\"+username+"\\repositories").listFiles(File::isDirectory);
        ArrayList<Repository> directoriesList = new ArrayList<>();

        for(File repoFile: repositories){
            directoriesList.add(getRepositoryDetails(repoFile));
        }
        return directoriesList;
    }

    private Repository getRepositoryDetails(File repoFile) {
        File branchesDirectory = new File(repoFile.getPath() + File.separator + ".magit" + File.separator +"branches");
        File headBranchFile = new File( branchesDirectory +File.separator + "HEAD.txt");
        File activeBranchFile = new File(branchesDirectory +File.separator + logicManager.getContentOfFile(headBranchFile) +".txt");
        String commitDetails = logicManager.getContentOfZipFile(repoFile + File.separator + ".magit" + File.separator + "objects", logicManager.getContentOfFile(activeBranchFile));
        Integer branchesNum = branchesDirectory.listFiles().length - 2;

        Repository repo = new Repository();
        repo.setName(repoFile.getName());
        repo.setActiveBranch(activeBranchFile.getName().replace(".txt",""));
        repo.setBranchesNumber(branchesNum.toString());
        repo.setLastCommitTime(commitDetails.split(",")[3]);
        repo.setLastCommitMsg(commitDetails.split(",")[2]);
        return repo;
    }
}
