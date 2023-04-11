<%-- 
    Document   : listacc
    Created on : Mar 5, 2023, 12:22:19 AM
    Author     : thien
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Management</title>
        <link rel="stylesheet" href="css/table.css">
        <link rel="stylesheet" type="image/x-icon" href="css/iconShortcut.jpg"/>

    </head>
    <body>
        <h1>Account Management</h1>
        <c:url var="HomeLink" value="main">
            <c:param name="action" value="Search"></c:param>
        </c:url> 

        <c:url var="ManageLink" value="main">
            <c:param name="action" value="SearchAcc"></c:param>
        </c:url> 


        <c:url var="RestoreLink" value="main">
            <c:param name="action" value="RestoreAcc"></c:param>
        </c:url> 

        <a href="${HomeLink}">Home</a>
        <a href="${ManageLink}">Manage</a>
        <a href="${RestoreLink}">Restore</a>


        <c:choose>

            <c:when test="${sessionScope.USER.role.equals('AD')}">
                <form action="main">
                    Search <input style="width: 20%" type="text" placeholder="Please take SearchAcc for show list account" name="search" value="${param.search}"/>
                    <input type="submit" name="action" value="SearchAcc"/>
                </form>
                <c:if test="${requestScope.LIST_USER!=null}">
                    <c:if test="${not empty requestScope.LIST_USER}">
                        <table class="container">
                            <thead>
                                <tr>
                                    <th><h1>No</h1></th>
                                    <th><h1>ID</h1></th>
                                    <th><h1>Email</h1></th>
                                    <th><h1>Name</h1></th>
                                    <th><h1>Role</h1></th>
                                    <th><h1>Update</h1></th>
                                    <th><h1>DELETE</h1></th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" varStatus="counter" items="${requestScope.LIST_USER}">
                                <form action="main">
                                    <tr>
                                        <td style="width:5%">${counter.count}</td>
                                        <td style="width:5%">${dto.userID}</td>
                                        <td>${dto.email}</td>

                                        <td>
                                            <input type="text" name="name" value="${dto.name}"/>
                                        </td>
                                        <td>
                                            <input type="text" name="role" value="${dto.role}" />
                                        </td>
                                        <td>
                                            <input type="hidden" name="userID" value="${dto.userID}"/>
                                            <input type="submit" name="action" value="Update"/>
                                        </td>
                                        <td>
                                            <input type="hidden" name="userID" value="${dto.userID}"/>
                                            <input type="submit" name="action" value="Delete" onclick="return confirm('Are you sure delete email : ${dto.email} !');" />
                                        </td>
                                    </tr>
                                </form>
                            </c:forEach>

                        </tbody>
                    </table>
                </c:if>
            </c:if>
            <c:if test="${ empty requestScope.LIST_USER_NOT}">
                <span><br></span>
                <span><br></span>
                <span><br></span>
                Sorry ! NO ACCOUNT USER TO SHOW <3 
            </c:if>
        </c:when>
        <c:otherwise>
            <c:redirect url="logout"></c:redirect>
        </c:otherwise>
    </c:choose>



</body>
</html>
