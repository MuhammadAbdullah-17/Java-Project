<%@page errorPage="addbookerror.jsp" %> 

<%@page import="java.util.*" %><%@page import="MyPack.*" %> <%@page import="MyPack.todoInfo" %> 

<%
          
          String s =(String) session.getAttribute("email");
          if(s==null)
          {
            response.sendRedirect("SessionInvalid.jsp");
          }
%>
<style>
*{
  overflow-x:hidden;
}
</style>

<html><body><center> 
<jsp:include page="index.jsp"></jsp:include>
<h3> Following results meet your search criteria</h3> 



<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<table class="table table-bordered">
				<thead>
					<TR> 
 <TH> Title </TH> 
 <TH> Message </TH> 
 <TH> Category </TH> 
</TR> 
				</thead>
				<tbody>
					
<% 
ArrayList todoList = (ArrayList)request.getAttribute("completed"); 
todoInfo todo = new todoInfo();
for(int i=0; i < todoList.size(); i++) { 
todo = (todoInfo)todoList.get(i); %>

<TR> <TD> <%= todo.getTitle()%> </TD>
<TD> <%= todo.getMsg()%> </TD>
<TD> <%= todo.getCategory()%> </TD> 
<td>
<form action="ControllerServlet"> <input value=<%= todo.getTitle()%> name="title" hidden><button class="btn btn-success"  type="submit" value="setCategory" name="action" > Set to complete</button>
    </form>						 
<form action="ControllerServlet"> <input value=<%= todo.getTitle()%> name="title" hidden><button class="btn btn-danger"  type="submit" value="delete" name="action" >Delete</button>
    </form>
</td>
</TR> 

<% 
} 
%> 

			</table>
		</div>
	</div>
</center> </body></html> 

