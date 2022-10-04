<%@page errorPage="addbookerror.jsp" %>

<html>
<script>
function validateForm() {
  password1 = signup.password.value;
  password2 = signup.password2.value;
  if (password1 != password2) {
     alert ("\nPassword did not match: Please try again...")
       return false;
  }
  }
</script>
<body>
<center> 

<h2> Todo App </h2> 
<h3> Sign UP</h3> 


<form name ="signup" onsubmit="return validateForm(this)" action="ControllerServlet" > 
<TABLE BORDER="1" > 
<TR> 
  <TD><h4 >Email</h4></TD> 
  <TD><input type="email" name="email" required /></TD> 
</TR> 
<TR> 
<TR> 
  <TD><h4 >Name</h4></TD> 
  <TD><input type="text" name="name" required /></TD> 
</TR> 
<TR> 
  <TD><h4 >Password</h4></TD> 
  <TD><input type="password" name="password" required/></TD> 
</TR> 
<TR> 
  <TD><h4 >Confirm Password</h4></TD> 
  <TD><input type="password" name="password2" required/></TD> 
</TR> 

<TR>
  <TD COLSPAN="2" ALIGN="CENTER"><input type="submit" value="signup" name="action" />
  <input type="reset" value="clear" /></TD> 
</TR> 

</TABLE></form> 

<h4>Already an account? <a href="ControllerServlet?action=loginUser" > Login </a></h4> 

</center></body></html>