package MyPack;
import java.io.*; 
public class userInfo implements Serializable{ 
private String name; 
private String email; 
private String password;
public userInfo() { 
name = ""; 
email = ""; 
password = ""; 
} 
public void setName(String n){ 
name = n; } 

public void setEmail(String e){ 
email = e; } 

public void setPassword(String p){ 
password = p;} 


public String getName( ){ 
return name;} 

public String getEmail( ){ 
return email; } 

public String getPassword( ){ 
return password;} 

} 
