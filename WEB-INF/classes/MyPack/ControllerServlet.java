package MyPack;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ControllerServlet extends HttpServlet {

    // This method only calls processRequest()

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // This method only calls processRequest()
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        // retrieving value of action parameter
        String userAction = request.getParameter("action");

        // if request comes to move to addTodo.jsp from hyperlink
        if (userAction.equals("addTodo")) {
            response.sendRedirect("addTodo.jsp");
        }
        // if request comes to move to searchTodo.jsp from hyperlink
        else if (userAction.equals("searchTodo")) {
            response.sendRedirect("searchTodo.jsp");
        } else if (userAction.equals("loginUser")) {
            response.sendRedirect("login.jsp");
        } else if (userAction.equals("SignupUser")) {
            response.sendRedirect("Signup.jsp");
        }
        // if “save�? button clicked on addTodo.jsp to add new record
        if (userAction.equals("save")) {
            addTodo(request, response);
        } else if (userAction.equals("search")) {
            searchTodo(request, response);
        } else if (userAction.equals("login")) {
            LoginUser(request, response);
        } else if (userAction.equals("signup")) {
            SignupUser(request, response);
        } else if (userAction.equals("alltodo")) {
            allTodo(request, response);
        } else if (userAction.equals("alltodo")) {
            allTodo(request, response);
        } else if (userAction.equals("setCategory")) {
            setCategory(request, response);
        } else if (userAction.equals("delete")) {
            deleteTodo(request, response);
        } else if (userAction.equals("completedtodo")) {
            completedTodo(request, response);
        } else if (userAction.equals("incompleteTodo")) {
            incompleteTodo(request, response);
        }
        else if (userAction.equals("searchUser")) {
            SearchUser(request, response);
        }
        else if (userAction.equals("blockUser")) {
           blockUser(request, response);
        }

        else if (userAction.equals("allUsers")) {
            ShowAllUsers(request, response);
        }

    }
    // end processRequest()

    // Search Todo
    private void searchTodo(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {

            todoDAO tDAO = new todoDAO();

            String title = request.getParameter("title");

            ArrayList todoList = tDAO.retrieveTodoList(title);
            request.setAttribute("list", todoList);

            RequestDispatcher rd = request.getRequestDispatcher("showTodo.jsp");
            rd.forward(request, response);
        } catch (SQLException sqlex) {

            // setting SQLException instance
            request.setAttribute("javax.servlet.jsp.JspException", sqlex);
            RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
            rd.forward(request, response);

        } catch (ClassNotFoundException cnfe) {

            // setting ClassNotFoundException instance
            // request.setAttribute("javax.servlet.jsp.JspException" , cnfe);
            RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
            rd.forward(request, response);

        }
    }// end searchPerson()

    // Show All todo function

    private void allTodo(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {

            todoDAO tDAO = new todoDAO();
            HttpSession session = request.getSession(false);

            int id = (int) session.getAttribute("id");

            ArrayList todoList = tDAO.retrieveAllTodoList(id);
            request.setAttribute("alllist", todoList);

            RequestDispatcher rd = request.getRequestDispatcher("showAllToDo.jsp");
            rd.forward(request, response);
        } catch (SQLException sqlex) {

            // setting SQLException instance
            request.setAttribute("javax.servlet.jsp.JspException", sqlex);
            RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
            rd.forward(request, response);

        } catch (ClassNotFoundException cnfe) {

            // setting ClassNotFoundException instance
            // request.setAttribute("javax.servlet.jsp.JspException" , cnfe);
            RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
            rd.forward(request, response);

        }
    }// end searchPerson()

    // Completed to dos
    private void completedTodo(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {

            todoDAO tDAO = new todoDAO();
            HttpSession session = request.getSession(false);

            int id = (int) session.getAttribute("id");

            ArrayList todoList = tDAO.showCompleted(id);
            request.setAttribute("completed", todoList);

            RequestDispatcher rd = request.getRequestDispatcher("showCompleted.jsp");
            rd.forward(request, response);
        } catch (SQLException sqlex) {

            // setting SQLException instance
            request.setAttribute("javax.servlet.jsp.JspException", sqlex);
            RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
            rd.forward(request, response);

        } catch (ClassNotFoundException cnfe) {

            // setting ClassNotFoundException instance
            // request.setAttribute("javax.servlet.jsp.JspException" , cnfe);
            RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
            rd.forward(request, response);

        }
    }// end searchPerson()

    // Incomplete To dos
    private void incompleteTodo(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {

            todoDAO tDAO = new todoDAO();
            HttpSession session = request.getSession(false);

            int id = (int) session.getAttribute("id");

            ArrayList todoList = tDAO.showIncomplete(id);
            request.setAttribute("incomplete", todoList);

            RequestDispatcher rd = request.getRequestDispatcher("showIncomplete.jsp");
            rd.forward(request, response);
        } catch (SQLException sqlex) {

            // setting SQLException instance
            request.setAttribute("javax.servlet.jsp.JspException", sqlex);
            RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
            rd.forward(request, response);

        } catch (ClassNotFoundException cnfe) {

            // setting ClassNotFoundException instance
            // request.setAttribute("javax.servlet.jsp.JspException" , cnfe);
            RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
            rd.forward(request, response);

        }
    }// end searchPerson()

    // Login Function
    private void LoginUser(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {

            todoDAO tDAO = new todoDAO();

            String email = request.getParameter("email");
            String pass = request.getParameter("password");

            ArrayList login = tDAO.LoginUser(email, pass);
           
           
            String error=(String) login.get(0);
            if( error.equals( "error"))
            {
                request.setAttribute("wrong_login", "true");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }
            else{

                String status=(String)login.get(2);
            if (!status.equals("block")) {
                HttpSession session = request.getSession(true);
                session.setAttribute("email", email);
                session.setAttribute("id", (int) login.get(1));
                session.setAttribute("name", (String) login.get(0));
                if ( (int)session.getAttribute("id") == 0) {
                    RequestDispatcher rd = request.getRequestDispatcher("adminPanel.jsp");
                    rd.forward(request, response);
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                    rd.forward(request, response);
                }
            } else {

             
                request.setAttribute("block", "true");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }

        }
     } catch (SQLException sqlex) {

            // setting SQLException instance
            request.setAttribute("javax.servlet.jsp.JspException", sqlex);
            RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
            rd.forward(request, response);

        } catch (ClassNotFoundException cnfe) {

            // setting ClassNotFoundException instance
            // request.setAttribute("javax.servlet.jsp.JspException" , cnfe);
            RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
            rd.forward(request, response);
        }
    }

    // Sign Up New User
    static int id = 1;

    private void SignupUser(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            todoDAO tDAO = new todoDAO();
            String name = request.getParameter("name");
            String pass = request.getParameter("password");
            String email = request.getParameter("email");
            // response.getWriter().write(name+pass);
            tDAO.Signup(name, pass, email, id, "unblock");
            id++;
            response.getWriter().write("You have been successfully registered. Please login.");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        } catch (SQLException sqlex) {

            // setting SQLException instance
            request.setAttribute("javax.servlet.jsp.JspException", sqlex);
            RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
            rd.forward(request, response);

        } catch (ClassNotFoundException cnfe) {

            // setting ClassNotFoundException instance
            // request.setAttribute("javax.servlet.jsp.JspException" , cnfe);
            RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
            rd.forward(request, response);
        }
    }

    // Add todo
    private void addTodo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            todoDAO tDAO = new todoDAO();
            todoInfo todo = new todoInfo();
            HttpSession session = request.getSession(false);
            Integer id = (Integer) session.getAttribute("id");
            int idx = id;
            String category = "incomplete";
            String title = request.getParameter("title");
            todo.setTitle(title);
            String msg = request.getParameter("msg");
            todo.setMsg(msg);
            todo.setCategory(category);
            tDAO.addTodo(todo, idx);
            response.sendRedirect("saveTodo.jsp");
        } catch (SQLException sqlex) {

            // setting SQLException instance
            request.setAttribute("javax.servlet.jsp.JspException", sqlex);
            RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
            rd.forward(request, response);
        } catch (ClassNotFoundException cnfe) {
            // setting ClassNotFoundException instance
            request.setAttribute("javax.servlet.jsp.JspException", cnfe);
            RequestDispatcher rd = request.getRequestDispatcher("addbokerror.jsp");
            rd.forward(request, response);
        }
    }

    // set category function

    private void setCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            todoDAO tDAO = new todoDAO();
            todoInfo todo = new todoInfo();
            HttpSession session = request.getSession(false);
            Integer id = (Integer) session.getAttribute("id");
            int idx = id;
            String title = request.getParameter("title");
            todo.setTitle(title);
            tDAO.setCategory(todo, idx);
            response.sendRedirect("index.jsp");
        } catch (SQLException sqlex) {

            // setting SQLException instance
            request.setAttribute("javax.servlet.jsp.JspException", sqlex);
            RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
            rd.forward(request, response);
        } catch (ClassNotFoundException cnfe) {
            // setting ClassNotFoundException instance
            request.setAttribute("javax.servlet.jsp.JspException", cnfe);
            RequestDispatcher rd = request.getRequestDispatcher("addbokerror.jsp");
            rd.forward(request, response);
        }
    }

    // Delete todo
    private void deleteTodo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            todoDAO tDAO = new todoDAO();
            todoInfo todo = new todoInfo();
            HttpSession session = request.getSession(false);
            Integer id = (Integer) session.getAttribute("id");
            int idx = id;
            String title = request.getParameter("title");
            todo.setTitle(title);
            tDAO.delete(todo, idx);
            response.sendRedirect("index.jsp");
        } catch (SQLException sqlex) {

            // setting SQLException instance
            request.setAttribute("javax.servlet.jsp.JspException", sqlex);
            RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
            rd.forward(request, response);
        } catch (ClassNotFoundException cnfe) {
            // setting ClassNotFoundException instance
            request.setAttribute("javax.servlet.jsp.JspException", cnfe);
            RequestDispatcher rd = request.getRequestDispatcher("addbokerror.jsp");
            rd.forward(request, response);
        }
    }



            ///////////////////////Admin Functions////////////////////////////

    //Search User 

    private void SearchUser(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
try {

    todoDAO tDAO = new todoDAO();

    String email = request.getParameter("email");

    ArrayList user = tDAO.searchUser(email);
    request.setAttribute("user", user);

    RequestDispatcher rd = request.getRequestDispatcher("showUser.jsp");
    rd.forward(request, response);
} catch (SQLException sqlex) {

    // setting SQLException instance
    request.setAttribute("javax.servlet.jsp.JspException", sqlex);
    RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
    rd.forward(request, response);

} catch (ClassNotFoundException cnfe) {

    // setting ClassNotFoundException instance
    // request.setAttribute("javax.servlet.jsp.JspException" , cnfe);
    RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
    rd.forward(request, response);

}
}// end searchPerson()

    //Show All Users

    private void ShowAllUsers(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
try {

    todoDAO tDAO = new todoDAO();
    HttpSession session = request.getSession(false);
    response.getWriter().write("hddddddddddddddd");
    ArrayList usersList = tDAO.ShowAllUsers();
    request.setAttribute("allUsers", usersList);

    RequestDispatcher rd = request.getRequestDispatcher("showAllUsers.jsp");
    rd.forward(request, response);
} catch (SQLException sqlex) {

    // setting SQLException instance
    request.setAttribute("javax.servlet.jsp.JspException", sqlex);
    RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
    rd.forward(request, response);

} catch (ClassNotFoundException cnfe) {

    // setting ClassNotFoundException instance
    // request.setAttribute("javax.servlet.jsp.JspException" , cnfe);
    RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
    rd.forward(request, response);

}
}// end searchPerson()

    //Block a User

    private void blockUser(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
try {
    todoDAO tDAO = new todoDAO();
    String email = request.getParameter("email");
    tDAO.blockUser(email);
    response.sendRedirect("adminPanel.jsp");
} catch (SQLException sqlex) {

    // setting SQLException instance
    request.setAttribute("javax.servlet.jsp.JspException", sqlex);
    RequestDispatcher rd = request.getRequestDispatcher("addbookerror.jsp");
    rd.forward(request, response);
} catch (ClassNotFoundException cnfe) {
    // setting ClassNotFoundException instance
    request.setAttribute("javax.servlet.jsp.JspException", cnfe);
    RequestDispatcher rd = request.getRequestDispatcher("addbokerror.jsp");
    rd.forward(request, response);
}
}




}
// end ControllerServlet