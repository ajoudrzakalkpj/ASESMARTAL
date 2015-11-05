<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" 
%>
<%

String json = net.sf.json.JSONSerializer.toJSON(request.getAttribute("map")).toString();
json = request.getAttribute("callback")+"("+json+")";
%>
<%=json%>