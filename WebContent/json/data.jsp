<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% Gson gson = new GsonBuilder().setPrettyPrinting().create();
//JsonObject json = (JsonObject)request.getAttribute("dataToSend");
    /**사용법:
    	1. Action file에서 Gson gson = new GsonBuilder().setPrettyPrinting().create(); 입력
        2. request.setAttribute("dataToSend", gson.toJson(보낼데이터)); 입력
        3. action.execute() 반환값 설정: return new Url("json/data.jsp", Url.FORWARD);
    	*/
    /**사용예시: 객체가 아닌 값을 보내는 경우
        1. action파일에서 JsonObject 생성
        2. 생성한 JsonObject에 원하는 데이터 입력(예시: 플레이리스트에 특정 곡이 들어있는지 여부를 응답하고 싶다.)
        
        JsonObject json = new JsonObject();
        boolean result = true
        json.addProperty("result": result)
        request.setAttribute("dataToSend", gson.toJson(json));
        return new Url("json/data.jsp", Url.FORWARD);
                결과: 해당 jsp를 받은 사용자는 {"result":true}라는 문자열을 받게 된다.
        */
    /** 객체 또는 배열을 보내는 경우(JsonObject를 쓰지 않는 것을 제외하면 위와 동일)
        UserVO user = new UserVO("test@test.com", "소리꾼123", LocalDateTime.now(), null);
        request.setAttribute("dataToSend", gson.toJson(user));
                결과: { "userId": "test@test.com", "nickname": "소리꾼123", "birthDate": { "date": { "year": 2024, "month": 5, "day": 23 }, "time": { "hour": 11, "minute": 0, "second": 23, "nano": 213000000 } } }
        P.S. userVO를 만들 때 password를 null로 초기화했기 때문에 password값이 아예 안 들어감.
        
        배열 보내기
        ArrayList<UserVO> list = new ArrayList<>();
        list.add(user);
        list.add(user);
        
                결과: [ { "userId": "test@test.test", "nickname": "소리꾼123", "birthDate": { "date": { "year": 2024, "month": 5, "day": 23 }, "time": { "hour": 11, "minute": 2, "second": 35, "nano": 613000000 } } }
                , { "userId": "test@test.test", "nickname": "소리꾼123", "birthDate": { "date": { "year": 2024, "month": 5, "day": 23 }, "time": { "hour": 11, "minute": 2, "second": 35, "nano": 613000000 } } } 
                ]
                 그냥 JSON 객체 두 개가 배열로 묶인 형태 
        */
%>
<%= request.getAttribute("dataToSend") %>
