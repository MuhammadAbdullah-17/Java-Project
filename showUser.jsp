<%@page errorPage="addbookerror.jsp" %> 

<%@page import="java.util.*" %><%@page import="MyPack.*" %> 

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
<jsp:include page="adminPanel.jsp"></jsp:include>
<h3> Following results meet your search criteria</h3> 



<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<table class="table table-bordered">
				<thead>
					<TR> 
 <TH> Name </TH> 
 <TH> Email </TH> 
 <TH> Password </TH> 
</TR> 
				</thead>
				<tbody>
					
<% 
ArrayList user = (ArrayList)request.getAttribute("user"); 
userInfo userObj = new userInfo();
for(int i=0; i<user.size(); i++) { 
userObj = (userInfo)user.get(i); %>

<TR> <TD> <%= userObj.getName()%> </TD>
<TD> <%= userObj.getEmail()%> </TD>
<TD> <%= userObj.getPassword()%> </TD> 
<td>
<form action="ControllerServlet"> <input value=<%= userObj.getEmail()%> name="email" hidden><button class="btn btn-danger"  type="submit" value="blockUser" name="action" > Block User</button>
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

