package Servlets;

import Logic.Logic;
import WebLogic.WebLogic;
import WebLogic.WebObjects.FileObject;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "WcServlet", urlPatterns = {"/wc"})
public class WcServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Map<String,Object> map=new HashMap<String,Object>();
        String username = req.getParameter("username");
        String repo_name = req.getParameter("repo_name");
        String path = req.getParameter("path");
        WebLogic m_WebLogic = new WebLogic();
        ArrayList<FileObject> files = m_WebLogic.getAllFiles("C:\\magit-ex3\\" + username + "\\repositories\\" + repo_name);
        String[] folderArratName = files.get(0).getPath().split("\\\\");
        String folderName = folderArratName[folderArratName.length-1];
        if (folderName.equals(".magit")) {
            folderArratName = files.get(1).getPath().split("\\\\");
            folderName = folderArratName[folderArratName.length-1];
        }
        path = "C:\\magit-ex3\\"+ username +"\\repositories\\" + repo_name+"\\"+folderName;
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