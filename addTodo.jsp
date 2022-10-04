<html><body><center> 



<%@ include file = "index.jsp" %>
<h3> Add new Todo</h3> 
<form name ="register" action="ControllerServlet" > 
<TABLE BORDER="1" > 
<TR> 
  <TD> <h4 > Title </h4> </TD> 
 <TD> <input type="text" name="title" required/> </TD> 
</TR> 

<TR>
  <TD> <h4> Message </h4> </TD> 
  <TD> <input type="text" name="msg" required/> </TD> 
</TR> 
<TR> 
  <TD COLSPAN="2" ALIGN="CENTER"><input type="submit" value="save" name="action" />
			<input type="reset" value="clear" /></TD> 
</TR> 


</TABLE></form> 


</center></body></html>