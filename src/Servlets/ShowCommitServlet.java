package Servlets;

import Logic.Logic;
import WebLogic.WebLogic;
import WebLogic.WebObjects.FileObject;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ShowCommitServlet", urlPatterns = {"/showCommit"})
public class ShowCommitServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Map<String,Object> map=new HashMap<String,Object>();
        String username = req.getParameter("username");
        String repo_name = req.getParameter("repo_name");
        String commit_sha1 = req.getParameter("commit_sha1");
        String path = req.getParameter("path");
        Logic logicManager = new Logic(username, "C:\\magit-ex3\\"+ username +"\\repositories\\" + repo_name);
        logicManager.deleteCommitSpreadFolder(new File("C:\\magit-ex3\\"+ username +"\\repositories\\" + repo_name+"\\.magit\\commitToShow"));
        logicManager.spreadCommitToShow(commit_sha1);
        WebLogic m_WebLogic = new WebLogic();
        ArrayList<FileObject> files = m_WebLogic.getAllFiles("C:\\magit-ex3\\"+ username +"\\repositories\\" + repo_name+"\\.magit\\commitToShow");
        String[] folderArratName = files.get(0).getPath().split("\\\\");
        String folderName = folderArratName[folderArratName.length-1];
        if (folderName.equals("CommitStatus.txt")) {
            folderArratName = files.get(1).getPath().split("\\\\");
            folderName = folderArratName[folderArratName.length-1];
        }
        path = "C:\\magit-ex3\\"+ username +"\\repositories\\" + repo_name+"\\.magit\\commitToShow"+"\\"+folderName;
        ArrayList <FileObject> mainfolder = m_WebLogic.getAllFiles(path);
        map.put("mainpath",path);
        map.put("mainfolder", mainfolder);
        write(res, map);
    }

    private void write(HttpServletResponse res, Map<String, Object> map) throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(new Gson().toJson(map));
    }

}