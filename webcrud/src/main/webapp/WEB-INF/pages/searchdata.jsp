<%--
  Created by IntelliJ IDEA.
  User: AndyCaptain
  Date: 20.08.2016
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<html>
<head>
    <title>User Details</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
        #dva {
            width: 6em;
        }
    </style>

</head>
<body>
<h2>Результаты поиска</h2>
<br/>
<a href="<c:url value="/users" ><c:param name="page" value="${currentPage}"/>${currentPage}</c:url>">В список пользователей</a>
<br/>
Найдено <b><i>${numrecords}</i></b> записей по запросу "<b><i>${searchstring}</i></b>"
<br/>
<table class="tg">
    <tr>
        <th width="40">№№</th>
        <th width="200">Имя</th>
        <th width="40">Возраст</th>
        <th width="60">Тип</th>
        <th width="180">Создан</th>
    </tr>
    <c:forEach items="${listUsers}" var="user">
        <tr>


            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <td>
                <c:if test="${user.admin=='true'}">
                    AДМИН
                </c:if>
                <c:if test="${user.admin=='false'}">
                </c:if>
            </td>

            <td>
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${user.createDate}"/>
            </td>
            <td><a href="<c:url value='/userdata/${user.id}'/>">Изменить</a></td>
            <td><a href="<c:url value='/remove/${user.id}'/>">Удалить</a></td>

        </tr>
    </c:forEach>
</table>

</body>
</html>