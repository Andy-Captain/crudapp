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
<h2>Редактирование пользователя <i>${user.name}</i></h2>
<h4><a href="<c:url value="/users" ><c:param name="page" value="${currentPage}"/>${currentPage}</c:url>">В список пользователей</a></h4>
<c:url var="editAction" value="/edit"/>
<form:form action="${editAction}" commandName="user" acceptCharset="utf-8">

    <table class="tg">
        <tr>
            <th width="40">№№</th>
            <th width="200">Имя</th>
            <th width="40">Возраст</th>
            <th width="40">Админ</th>
            <th width="180">Создан</th>
        </tr>
            <form:input path="id" readonly="true" hidden="true"/>
        <tr>
            <td>
                ${user.id}
            </td>

            <td>
                <form:input path="name" required="required"  size="30"  type="text"
                            title="Введите не менее 3-х и не более 25 символов.Разрешены буквы (RUS ENG),цифры, пробел, тире и символ подчеркивания. Первый символ всегда буква"
                            maxlength="25" acceptCharset="utf-8" pattern="^[а-яА-ЯёЁa-zA-Z][а-яА-ЯёЁa-zA-Z0-9-_ ]{1,25}$"
                />
            </td>

            <td>
                <form:input type="number" name="age" min="1" max="100" path="age" size="4" id="dva"/>
            </td>

            <td>
                <form:checkbox path="admin" />
            </td>
            <td>
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${user.createDate}"/>
            </td>

        </tr>
        <tr>
            <td>
                <input type="submit" value="<spring:message text="Записать"/>"/>
            <td>
                <input type="reset" value="<spring:message text="Сбросить"/>"/>
            </td>

        </tr>
    </table>
</form:form>

</body>
</html>