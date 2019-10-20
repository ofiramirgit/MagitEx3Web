package Servlets;

import Logic.Logic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReadXmlServlet", urlPatterns = {"/readXml"})
public class ReadXmlServlet extends HttpServlet {

    Logic logicManager = new Logic();
    String path = "C:\\Users\\amira\\Desktop\\Java\\ex3-medium.xml";

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException{
        String username= req.getParameter("username");
        FileUploadServlet fileUploadServlet = new FileUploadServlet();

        try {
            fileUploadServlet.doPost(req,res);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
//            logicManager.readXML("C:\\Users\\amira\\Desktop\\Java\\ex3-medium.xml", "C:\\magit-ex3\\"+username + "\\repositories");
            logicManager.readXML("C:\\Users\\OL\\Desktop\\Java Course\\ex3-medium.xml", "C:\\magit-ex3\\"+username + "\\repositories");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
