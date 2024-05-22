<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>이 페이지는 action이 Url 대신 Null값을 반환할 때 나타나는 페이지입니다.</h1>
<h1>입력된 CMD : <%= request.getParameter("cmd") %></h1>
<h2>실행된 Action: <%= request.getAttribute("action").getClass().getName() %></h2>