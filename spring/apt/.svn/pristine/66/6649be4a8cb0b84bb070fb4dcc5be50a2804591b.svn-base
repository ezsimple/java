<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.util.Enumeration"%>

{
	"success":false,
	"error_id": "${requestScope["javax.servlet.error.status_code"]}"
	"error_message": "${requestScope["javax.servlet.error.message"]}"
}
<%
Object errorId = request.getAttribute("javax.servlet.error.status_code");

Exception e = (Exception)request.getAttribute("javax.servlet.error.exception");
if(e==null){
	Enumeration en = request.getAttributeNames();
	while(en.hasMoreElements()){
		Object key = en.nextElement();
		// System.out.println(key + " : " + request.getAttribute(key.toString()));
	}
}else{
	e.printStackTrace();
}	

if(errorId != null && StringUtils.startsWith(errorId.toString(), "4")){
	response.sendRedirect("/");
}

%>