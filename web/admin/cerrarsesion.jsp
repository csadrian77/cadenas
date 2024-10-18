<%@ page session="true" %>
<%
HttpSession sesionOk = request.getSession();
sesionOk.invalidate();
response.sendRedirect("/boletos/admin/login.jsp");
%>
