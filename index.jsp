<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
   <%
          
          String s =(String) session.getAttribute("email");
          if(s==null)
          {
            response.sendRedirect("SessionInvalid.jsp");
          }
%>
   <% 
    String name = (String)session.getAttribute("name");
   %>
   <style>
   *
{
    font-family: Georgia, 'Times New Roman', Times, serif;
}

   .header
   {
    background-color: orange;
    height: 70px;
   }
   .link
   {
    color:white;
   }
   a:hover
   {
    text-decoration: none;
    color:white;
   }
   </style>
   
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title >Todo App</title>
    </head>
    <body>
    
    <div class="container-fluid">
    <div class="header row">
    <h3 class=" fw-bolder m-2 col-2"><%= "Hi "+name + "! " %></h3>
        <h2 class="text-center mb-3 col-6 m-3">Todo App</h2>
        <button class="btn btn-outline-warning col-2"><a class="link fs-5" href="searchTodo.jsp" > Search Todo </a></button>
    <button class="btn btn-danger col-1 mx-3"><a class="link fs-5" href="logout.jsp">Log Out</a></button>
    </div>
    <div class="row mt-3">
    <div class="">
    <button class="btn btn-success col-2 mx-3 text-dark"><a class="link fs-5" href="ControllerServlet?action=alltodo">All To dos</a></button>
    <button class="btn btn-success col-2 mx-3 text-black"><a class="link fs-5" href="ControllerServlet?action=completedtodo">Completed Todo list</a></button>
    <button class="btn btn-success col-3 mx-3 text-black"><a class="link fs-5" href="ControllerServlet?action=incompleteTodo">Incomplete Todo List</a></button>
<form action="ControllerServlet"> <button class="btn btn-success col-2 ms-3 text-black"  type="submit" value="addTodo" name="action" > Add a new Todo </button>
    </form>    </div>
    </div>
         
		<br/>
		
        </div>
    </body>
</html>
