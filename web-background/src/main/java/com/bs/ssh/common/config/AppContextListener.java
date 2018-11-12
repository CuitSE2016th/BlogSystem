package com.bs.ssh.common.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.DriverManager;

/**
 * 应用上下文监听器
 *
 * @author Egan
 * @date 2018/11/12 1:00
 **/
@WebListener
public class AppContextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent event)  {
        try{
            while(DriverManager.getDrivers().hasMoreElements()){
                DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void contextInitialized(ServletContextEvent event)  {
        ServletContext context = event.getServletContext();
        String rootPath = context.getRealPath("/");
        System.setProperty("rootPath", rootPath);

        //logger.info("global setting,rootPath:{}",rootPath);
        //logger.info("deployed on architecture:{},operation System:{},version:{}",
        //       System.getProperty("os.arch"), System.getProperty("os.name"),
        //   System.getProperty("os.version"));
        //logger.info("app startup completed....");
    }
}
