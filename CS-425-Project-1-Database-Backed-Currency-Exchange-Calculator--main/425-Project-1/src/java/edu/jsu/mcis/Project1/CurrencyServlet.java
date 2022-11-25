package edu.jsu.mcis.Project1;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.jsu.mcis.dao.CurrencyDAO;
import edu.jsu.mcis.dao.DAOFactory;
import javax.servlet.ServletContext;
public class CurrencyServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        DAOFactory daoFactory = null;
        ServletContext context = request.getServletContext();

        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        } else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }

        response.setContentType("application/json; charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            CurrencyDAO dao = daoFactory.getCurrencyDAO();

            out.println(dao.find());

        } catch (Exception e) {
            e.printStackTrace();
        }


    
        }

}
