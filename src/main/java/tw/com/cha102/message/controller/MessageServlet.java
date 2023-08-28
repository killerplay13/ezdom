package tw.com.cha102.message.controller;

import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String userName = req.getParameter("userName");

        req.setAttribute("userName", userName);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/chat.jsp");
        dispatcher.forward(req,resp);
    }


}
