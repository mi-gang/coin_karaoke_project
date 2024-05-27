<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//${playLists}
//{"id":"name"}
Gson gson = new Gson();
String jsonObjectString = gson.toJson((List)request.getAttribute("playLists"));
System.out.println("JSON Object : " + jsonObjectString);
%>
<%=jsonObjectString %>