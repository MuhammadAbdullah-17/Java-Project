package MyPack;

import MyPack.todoInfo;
import java.util.*;
import java.sql.*;

public class todoDAO {

    private Connection con;

    public todoDAO() throws ClassNotFoundException, SQLException {
        establishConnection();
    }

    private void establishConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String conUrl = "jdbc:mysql://127.0.0.1/todoapp";
        con = DriverManager.getConnection(conUrl, "root", "root");
    }

    // Retrieve Todo List

    public ArrayList retrieveTodoList(String titleMain) throws SQLException {

        ArrayList todoList = new ArrayList();

        String sql = " SELECT * FROM todolist WHERE title = ?";
        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setString(1, titleMain);

        ResultSet rs = pStmt.executeQuery();

        String title;
        String msg;
        String category;

        while (rs.next()) {
            title = rs.getString("title");
            msg = rs.getString("msg");
            category = rs.getString("category");
            todoInfo todoBean = new todoInfo();
            todoBean.setTitle(title);
            todoBean.setMsg(msg);
            todoBean.setCategory(category);
            todoList.add(todoBean);

        }

        return todoList;

    }

    // Login User

    public ArrayList LoginUser(String email, String pass) throws SQLException {

        String sql = " SELECT * FROM login WHERE email = ? AND pass = ?";
        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setString(1, email);
        pStmt.setString(2, pass);
        ResultSet rs = pStmt.executeQuery();
        ArrayList temp = new ArrayList();
        if (rs.next()) {

            temp.add(rs.getString("name"));
            temp.add(rs.getInt("id"));
            temp.add(rs.getString("status"));
            return temp;
        } else {
            temp.add("error");
            return temp;
        }
    }

    //Show all todos
    public ArrayList retrieveAllTodoList(int id) throws SQLException {

        ArrayList todoList = new ArrayList();

        String sql = " SELECT * FROM todolist WHERE id = ?";
        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setInt(1, id);

        ResultSet rs = pStmt.executeQuery();

        String title;
        String msg;
        String category;

        while (rs.next()) {
            title = rs.getString("title");
            msg = rs.getString("msg");
            category = rs.getString("category");
            todoInfo todoBean = new todoInfo();
            todoBean.setTitle(title);
            todoBean.setMsg(msg);
            todoBean.setCategory(category);
            todoList.add(todoBean);

        }

        return todoList;

    }

    // Sign Up User

    public void Signup(String name, String pass, String email, int id, String status) throws SQLException {

        String sql = "INSERT INTO login VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setString(1, name);
        pStmt.setString(2, pass);
        pStmt.setString(3, email);
        pStmt.setInt(4, id);
        pStmt.setString(5, status);
        pStmt.executeUpdate();
    }


    //Add todo

    public void addTodo(todoInfo todo, int id) throws SQLException {

        String sql = " INSERT INTO todolist VALUES (?, ?, ?, ?)";
        PreparedStatement pStmt = con.prepareStatement(sql);

        String title = todo.getTitle();
        String msg = todo.getMsg();
        String category = todo.getCategory();

        pStmt.setString(1, title);
        pStmt.setString(2, msg);
        pStmt.setString(3, category);
        pStmt.setInt(4, id);
        pStmt.executeUpdate();
    }

    // Set category function

public void setCategory(todoInfo todo ,int id) throws SQLException {

    ArrayList todoList = new ArrayList();

    String sql = "Update todolist SET category = ? WHERE id = ? AND title  =  ?";
    PreparedStatement pStmt = con.prepareStatement(sql);
    pStmt.setString(1, "complete");
    pStmt.setInt(2, id);
    pStmt.setString(3, todo.getTitle());
    pStmt.executeUpdate();

}

 // Delete todo function

 public void delete(todoInfo todo ,int id) throws SQLException {

    ArrayList todoList = new ArrayList();

    String sql = "DELETE FROM todolist WHERE title= ? AND id= ?  ;";
    PreparedStatement pStmt = con.prepareStatement(sql);
    pStmt.setString(1, todo.getTitle());
    pStmt.setInt(2, id);
    pStmt.executeUpdate();

}


//show completed to dos
public ArrayList showCompleted(int id) throws SQLException {

    ArrayList todoList = new ArrayList();

    String sql = " SELECT * FROM todolist WHERE id = ? AND category = ?";
    PreparedStatement pStmt = con.prepareStatement(sql);
    pStmt.setInt(1, id);
    pStmt.setString(2, "complete");
    ResultSet rs = pStmt.executeQuery();
    String title;
    String msg;
    String category;
    while (rs.next()) {
        title = rs.getString("title");
        msg = rs.getString("msg");
        category = rs.getString("category");
        todoInfo todoBean = new todoInfo();
        todoBean.setTitle(title);
        todoBean.setMsg(msg);
        todoBean.setCategory(category);
        todoList.add(todoBean);

    }

    return todoList;

}


//show incomplete to dos
public ArrayList showIncomplete(int id) throws SQLException {

    ArrayList todoList = new ArrayList();

    String sql = " SELECT * FROM todolist WHERE id = ? AND category = ?";
    PreparedStatement pStmt = con.prepareStatement(sql);
    pStmt.setInt(1, id);
    pStmt.setString(2, "incomplete");
    ResultSet rs = pStmt.executeQuery();
    String title;
    String msg;
    String category;
    while (rs.next()) {
        title = rs.getString("title");
        msg = rs.getString("msg");
        category = rs.getString("category");
        todoInfo todoBean = new todoInfo();
        todoBean.setTitle(title);
        todoBean.setMsg(msg);
        todoBean.setCategory(category);
        todoList.add(todoBean);

    }

    return todoList;

}


//Search User

public ArrayList searchUser(String email) throws SQLException {

    ArrayList user = new ArrayList();

    String sql = " SELECT * FROM login WHERE email = ?";
    PreparedStatement pStmt = con.prepareStatement(sql);
    pStmt.setString(1, email);

    ResultSet rs = pStmt.executeQuery();

    String name;
    String mail;
    String password;

    while (rs.next()) {
        name = rs.getString("name");
        mail = rs.getString("email");
        password = rs.getString("pass");
        userInfo userBean = new userInfo();
        userBean.setName(name);
        userBean.setEmail(email);
        userBean.setPassword(password);
        user.add(userBean);

    }

    return user;

}


 //Show all Users
 public ArrayList ShowAllUsers() throws SQLException {

    ArrayList usersList = new ArrayList();

    String sql = " SELECT * FROM login WHERE status = ? AND name != ?";
    PreparedStatement pStmt = con.prepareStatement(sql);
    pStmt.setString(1, "unblock");
    pStmt.setString(2, "admin");
    ResultSet rs = pStmt.executeQuery();
    String name;
    String email;
    String password;
    while (rs.next() ) {
        name = rs.getString("name");
        email = rs.getString("email");
        password = rs.getString("pass");
        userInfo userBean = new userInfo();
        userBean.setName(name);
        userBean.setEmail(email);
        userBean.setPassword(password);
        usersList.add(userBean);

    }
    return usersList;
}


// Block A user

public void blockUser(String email) throws SQLException {


    String sql = "Update login SET status = ? WHERE email = ?";
    PreparedStatement pStmt = con.prepareStatement(sql);
    pStmt.setString(1,"block");
    pStmt.setString(2, email);
    pStmt.executeUpdate();

}


}
