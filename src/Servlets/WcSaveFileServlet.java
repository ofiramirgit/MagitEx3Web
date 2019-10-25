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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "WcSaveFileServlet", urlPatterns = {"/save_file"})
public class WcSaveFileServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String filePath = req.getParameter("path");
        String file_content = req.getParameter("content");
        System.out.println(filePath);
        System.out.println(file_content);
        File file = new File(filePath);
        file.delete();
        Files.write(Paths.get(file.getPath()),file_content.getBytes());
    }
}