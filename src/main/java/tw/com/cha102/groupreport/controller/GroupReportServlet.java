package tw.com.cha102.groupreport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.cha102.groupreport.model.GroupReportDAO;
import tw.com.cha102.groupreport.model.GroupReportDAOImpl;
import tw.com.cha102.groupreport.model.GroupReportJoin;
import tw.com.cha102.groupreport.model.GroupReportVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GroupReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if("getAll".equals(action)) {

            GroupReportDAOImpl dao = new GroupReportDAOImpl();
            List<GroupReportJoin> list = dao.getAll();

            HttpSession session = req.getSession();
            session.setAttribute("list", list);

            String url = "listAll_GROUP_REPORT";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, resp);
            return;
        }

        if("reject".equals(action)){

            return;
        }
        if("accept".equals(action)){
            GroupReportDAOImpl dao = new GroupReportDAOImpl();

            return;
        }

    }
}
