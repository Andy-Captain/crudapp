<%--
  Created by IntelliJ IDEA.
  User: AndyCaptain
  Date: 20.08.2016
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<html>
<head>
    <title>User List</title>

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
        .err {
            color: red; /* Цвет символа */
        }

    </style>
</head>

<br/>

<h2>Список пользователей</h2>
<br/>
<c:url var="lastError" value="${errorMessage}"/>
<c:url var="searchAction" value="/search"/>
<form:form action="${searchAction}" commandName="userquery" acceptCharset="utf-8">
<table>
    <td>Введите имя (часть имени) пользователя</td>
    <td>
    <form:input path="searchString" required="required" size="30" type="text"
                title="Введите любую часть имени пользователя (не менее 3-х символов).Разрешены буквы ,цифры, пробел, тире."
                maxlength="25" acceptCharset="utf-8"
                pattern="^[а-яА-ЯёЁa-zA-Z0-9- ]{3,25}$"
    />
    </td>
    <td>
        <input type="submit" value="<spring:message text="Искать"/>"/>
    </td>
</table>
</form:form>

    <h5>Показана стр. ${currpage} из ${endpage}</h5>
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
    <div class="pagination">
    <h4>Стр.
    <c:forEach begin="${startpage}" end="${endpage}" var="p">
        <c:if test="${currpage!=p}">
            <a href="<c:url value="/users" ><c:param name="page" value="${p}"/>${p}</c:url>">${p}</a>
        </c:if>
        <c:if test="${currpage==p}">
            ${p}
        </c:if>
    </c:forEach>
    </h4>
    </div>
    <br/>

    <h3>Добавление пользователя</h3>
    <c:url var="addAction" value="/add"/>

    <form:form action="${addAction}" commandName="user" acceptCharset="utf-8">
        <table>
            <tr>
                <td>
                    <form:label path="name">
                        <spring:message text="Имя "/>
                    </form:label>
                </td>
                <td>
                    <form:input path="name" required="required" size="30" type="text"
                                title="Введите не менее 3 и не более 25 символов.Разрешены буквы ,цифры, пробел, тире. Первый символ всегда буква"
                                maxlength="25" acceptCharset="utf-8"
                                pattern="^[а-яА-ЯёЁa-zA-Z][а-яА-ЯёЁa-zA-Z0-9-_ ]{3,25}$"
                    />
                </td>
            </tr>
            <tr>
                <b><i><span class="err"> ${errorMessage}</span></i></b>
            </tr>
        </table>
        <table>
            <tr>
                <td>
                    <form:label path="age">
                        <spring:message text="Возраст (1-100)"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="number" name="age" min="1" max="100" path="age" value="0" id="dva"/>
                </td>
                <td>
                    <form:label path="admin">
                        <spring:message text="Админ ?"/>
                    </form:label>
                </td>
                <td>
                    <form:checkbox path="admin"/>
                </td>

            </tr>
            <tr></tr>
        </table>
        <br/>
        <input type="submit" value="<spring:message text="Создать"/>"/>


    </form:form>


    </body>
    </html>
