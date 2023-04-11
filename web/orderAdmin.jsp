<%-- 
    Document   : orderAdmin
    Created on : Mar 10, 2023, 10:12:40 PM
    Author     : thien
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="image/x-icon" href="css/iconShortcut.jpg"/> 
        <title>Manage Order</title>
        <link rel="stylesheet" href="css/table.css">
    </head>
    <body>
        <c:url var="Processing" value="main">
            <c:param name="action" value="ManageOrder"></c:param>
        </c:url> 

        <c:url var="Confirm" value="main">
            <c:param name="action" value="ManageOrder"></c:param>
        </c:url> 


        <c:url var="HomeLink" value="main">
            <c:param name="action" value="Search"></c:param>
        </c:url> 

        <c:url var="Complete" value="main">
            <c:param name="action" value="ManageOrder"></c:param>
        </c:url> 
        <a href="${HomeLink}">Home</a>
        <a href="${Processing}&st=1">Processing</a>
        <a href="${Confirm}&st=2">Confirmed</a>
        <a href="${Complete}&st=3">Completed</a>

        <c:choose>

            <c:when test="${sessionScope.USER.role.equals('AD')}">
                <c:if test="${requestScope.List!=null}">
                    <c:if test="${not empty requestScope.List}">
                        <table class="container">
                            <thead>
                                <tr>
                                    <th><h1>Order ID</h1></th>
                                    <th><h1>Image</h1></th>
                                    <th><h1>Product's Name</h1></th>
                                    <th><h1>DESCRIPTION</h1></th>
                                    <th><h1>Price</h1></th>
                                    <th><h1>Quantity</h1></th>
                                    <th><h1>Status</h1></th>
                                    <th><h1></h1></th>

                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach var="dto" varStatus="counter" items="${requestScope.List}">
                                    <tr>
                                        <td style="font-size: 1em;color: red;" colspan="8">
                                            ID USER'S ORDER :  ${dto.getUser_id()}
                                        </td>
                                    </tr>

                                    <tr>
                                        <td style="font-size: 2.5em;color: red;" colspan="8">
                                            <c:choose>
                                                <c:when test="${dto.getStatus() == 1}">
                                                    Processing :
                                                </c:when>

                                                <c:when test="${dto.getStatus() == 2}">
                                                    Confirmed :
                                                </c:when>

                                                <c:when test="${dto.getStatus() == 3}">
                                                    Completed :
                                                </c:when>
                                            </c:choose>
                                        </td>
                                    </tr>
                                <form action="main">

                                    <tr>
                                        <c:forEach var="item" items="${requestScope.ListCart}">
                                            <c:if test="${dto.getProduct_id() == item.getId()}">
                                                <td>
                                                    ${dto.getId()}
                                                </td>

                                                <td>

                                                    <img style="height: 100px; width: auto" src="${item.getImaga_link()}" />

                                                </td>
                                                <td style="width:10%; word-wrap: break-word;">${item.getName()}</td>

                                                <td style=" word-wrap: break-word;">${item.getDescription()}</td>

                                                <td style="width: 10%">
                                                    $ ${item.getPrice()}
                                                </td>

                                            </c:if>
                                        </c:forEach>
                                        <td>
                                            ${dto.getQuantity()}
                                        </td>

                                        <td colspan="2">
                                            <c:choose>
                                                <c:when test="${dto.getStatus() == 1}">
                                                    processing 
                                                </c:when>

                                                <c:when test="${dto.getStatus() == 2}">
                                                    confirmed
                                                </c:when>

                                                <c:when test="${dto.getStatus() == 3}">
                                                    completed
                                                </c:when>
                                            </c:choose>
                                        </td>


                                    <tr>
                                        <td style="font-size: 2.5em;" colspan="4">
                                            Total Price :
                                        </td>
                                        <td style="font-size: 2.5em;
                                            color: red;"  colspan="2">
                                            $ ${dto.getTotalPrice()}
                                        </td>
                                        <td  style="font-size: 2.5em;
                                             " colspan="2">
                                            <input  type="hidden"  name="userID" value="${dto.getUser_id()}"/>
                                            <input  type="hidden"  name="orderID" value="${dto.getId()}"/>
                                            <c:if test="${dto.getStatus() == 1}">
                                                <input type="submit" name="action" value="Confirm" onclick="return confirm('Are you sure confirm order:  ${dto.getId()} have ${dto.getQuantity()} quantity  !');" />   
                                            </c:if>
                                        </td>
                                    </tr>
                                </form>
                            </c:forEach>

                        </tbody>
                    </table>
                </c:if>
            </c:if>

            <c:if test="${ empty requestScope.List }">
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
