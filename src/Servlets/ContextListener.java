package Servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
         try {
             Files.createDirectories(Paths.get("c:\\magit-ex3"));
             Files.createFile(Paths.get("c:\\magit-ex3\\users.txt"));
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        File dir = new File("c:\\magit-ex3");
        deleteDirectory(dir);
    }

    boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
}
