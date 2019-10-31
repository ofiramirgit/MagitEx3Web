package Servlets.WC;

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

@WebServlet(name = "WcFilesServlet", urlPatterns = {"/wc_files"})
public class WcFilesServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Map<String,Object> map=new HashMap<String,Object>();
        String filePath = req.getParameter("path");
        String isFolder = req.getParameter("isFolder");
        WebLogic m_WebLogic = new WebLogic();
        if(isFolder.equals("true")) {
            ArrayList<FileObject> mainfolder = m_WebLogic.getAllFiles(filePath);
            map.put("mainfolder", mainfolder);

        }else{
            Logic logicManager = new Logic();
            String Content = logicManager.getContentOfFile(new File(filePath));
            map.put("content",Content);
        }
        write(res, map);
    }

    private void write(HttpServletResponse res, Map<String, Object> map) throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(new Gson().toJson(map));
    }

}