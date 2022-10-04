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
<h3> New Todo is added successfully!</h3> 
</center></body></html> 
