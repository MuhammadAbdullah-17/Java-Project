<%@page errorPage="addbookerror.jsp" %> 


<html><body><center> 
<% 
String err =(String) request.getAttribute("wrong_login");

if(err != null)
{


%>
<script>
alert("Wrong login credentials.");
</script>

<%  
}
%>

<% 
String block =(String) request.getAttribute("block");

if(block != null)
{


%>
<script>
alert("User has been Blocked");
</script>

<%  
}
%>
<h2> Todo App </h2> 
<h3> Login</h3> 

<form name ="login" action="ControllerServlet" > 
<TABLE BORDER="1" > 

<TR> 
  <TD><h4 >Email</h4></TD> 
  <TD><input type="text" name="email" /></TD> 
</TR> 
<TR> 
  <TD><h4 >Password</h4></TD> 
  <TD><input type="password" name="password" /></TD> 
</TR> 
<TR>
  <TD COLSPAN="2" ALIGN="CENTER"><input type="submit" value="login" name="action" />
  <input type="reset" value="clear" /></TD> 
</TR> 

</TABLE></form> 

<h4>Don't have an account? <a href="ControllerServlet?action=SignupUser" > Sign UP </a></h4> 

</center></body></html>