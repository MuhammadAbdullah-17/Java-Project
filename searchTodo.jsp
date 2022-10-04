<%@page errorPage="addbookerror.jsp" %> 
<%
          
          String s =(String) session.getAttribute("email");
          if(s==null)
          {
            response.sendRedirect("SessionInvalid.jsp");
          }
%>
<html><body><center> 

<jsp:include page="index.jsp"></jsp:include>
<h3> Search Todo</h3> 

<form name ="search" action="ControllerServlet" > 
<TABLE BORDER="1" > 

<TR> 
  <TD><h4 >Title</h4></TD> 
  <TD><input type="text" name="title" required/></TD> 
</TR> 

<TR>
  <TD COLSPAN="2" ALIGN="CENTER"><input type="submit" value="search" name="action" />
  <input type="reset" value="clear" /></TD> 
</TR> 

</TABLE></form> 
</center></body></html>