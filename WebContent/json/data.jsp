<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% Gson gson = new GsonBuilder().setPrettyPrinting().create();
JsonObject json = (JsonObject)request.getAttribute("JsonObjectToSend");%>
<%= gson.toJson(json) %>