package MyPack;
import java.io.*; 
public class todoInfo implements Serializable{ 
private String title; 
private String msg; 
private String category;
public todoInfo() { 
title = ""; 
msg = ""; 
category = ""; 
} 
public void setTitle(String t){ 
title = t; } 

public void setMsg(String m){ 
msg = m; } 

public void setCategory(String c){ 
category = c;} 



public String getTitle( ){ 
return title;} 

public String getMsg( ){ 
return msg; } 

public String getCategory( ){ 
return category;} 

} // end class TodoInfo 