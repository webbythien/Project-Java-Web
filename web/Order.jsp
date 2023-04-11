<%-- 
    Document   : Order
    Created on : Mar 7, 2023, 12:03:07 AM
    Author     : thien
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
        <link rel="stylesheet" href="css/table.css">
        <link rel="stylesheet" type="image/x-icon" href="css/iconShortcut.jpg"/>

    </head>
    <body>
        <h1>Ordered</h1>
        <c:url var="Processing" value="main">
            <c:param name="action" value="Order"></c:param>
        </c:url> 

        <c:url var="Confirm" value="main">
            <c:param name="action" value="Order"></c:param>
        </c:url> 


        <c:url var="HomeLink" value="main">
            <c:param name="action" value="Search"></c:param>
        </c:url> 

        <c:url var="Complete" value="main">
            <c:param name="action" value="Order"></c:param>
        </c:url> 

        <c:url var="Cancel" value="main">
            <c:param name="action" value="Order"></c:param>
        </c:url> 

        <a href="${HomeLink}">Home</a>
        <a href="${Processing}&st=1">Processing</a>
        <a href="${Confirm}&st=2">Confirm</a>
        <a href="${Complete}&st=3">Complete</a>
        <a href="${Complete}&st=0">Cancel</a>


        <h1>${requestScope.Message}</h1>
        <c:choose>

            <c:when test="${sessionScope.USER.role.equals('US')}">
                <!--                <form action="main">
                                    Search <input style="width: 20%" type="text" placeholder="Search by date" value=""/>
                                    <input type="submit" name="action" value="Order"/>
                                </form>-->
                <c:if test="${requestScope.List1!=null}">
                    <c:if test="${not empty requestScope.List1}">
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
                                    <th><h1>Cancel</h1></th>

                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td style="font-size: 2.5em;color: red;" colspan="8">
                                        Processing :
                                    </td>
                                </tr>
                                <c:forEach var="dto" varStatus="counter" items="${requestScope.List1}">

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
                                            Processing <br>

                                        </td>
                                        <td>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="8">
                                            ${dto.getDetails()}
                                        </td>
                                    </tr>
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
                                            <input  type="hidden"  name="orderID" value="${dto.getId()}"/>
                                            <input type="submit" name="action" value="Cancel" onclick="return confirm('Are you sure cancel order:  ${item.getName()} have ${dto.getQuantity()} quantity  !');" />   
                                        </td>
                                    </tr>
                                </form>
                                <br>

                            </c:forEach>

                            </tbody>
                        </table>
                    </c:if>
                </c:if>

                <c:if test="${requestScope.List2!=null}">
                    <c:if test="${not empty requestScope.List2}">
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
                                    <th><h1>Complete</h1></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td style="font-size: 2.5em;color: red;" colspan="8">
                                        Confirm :
                                    </td>
                                </tr>
                                <c:forEach var="dto" varStatus="counter" items="${requestScope.List2}">

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
                                            Confirm <br>
                                            and order has been delivery to you. 
                                        </td>
                                        <td>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="8">
                                            ${dto.getDetails()}
                                        </td>
                                    </tr>
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
                                            <input type="submit" name="action" value="Complete" onclick="return confirm('Are you sure  you were receive order !');" />   
                                        </td>
                                    </tr>
                                </form>
                                <br>

                            </c:forEach>

                            </tbody>
                        </table>
                    </c:if>
                </c:if>

                <c:if test="${requestScope.List3!=null}">
                    <c:if test="${not empty requestScope.List3}">
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
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td style="font-size: 2.5em;color: red;" colspan="8">
                                        Complete :
                                    </td>
                                </tr>
                                <c:forEach var="dto" varStatus="counter" items="${requestScope.List3}">

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
                                            Completed <br>
                                            . 
                                        </td>

                                    </tr>
                                    <tr>
                                        <td colspan="8">
                                            ${dto.getDetails()}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 2.5em;" colspan="4">
                                            Total Price :
                                        </td>
                                        <td style="font-size: 2.5em;
                                            color: red;"  colspan="3">
                                            $ ${dto.getTotalPrice()}
                                        </td>

                                    </tr>
                                </form>
                                <br>

                            </c:forEach>

                            </tbody>
                        </table>
                    </c:if>
                </c:if>
                <br>


                <c:if test="${requestScope.List0!=null}">
                    <c:if test="${not empty requestScope.List0}">
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
                                    <th><h1>Cancel</h1></th>

                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td style="font-size: 2.5em;color: red;" colspan="8">
                                        Canceled :
                                    </td>
                                </tr>
                                <c:forEach var="dto" varStatus="counter" items="${requestScope.List0}">

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
                                            Canceled <br>

                                        </td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td colspan="8">
                                            ${dto.getDetails()}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size: 2.5em;" colspan="4">
                                            Total Price :
                                        </td>
                                        <td style="font-size: 2.5em;
                                            color: red;"  colspan="2">
                                            $ ${dto.getTotalPrice()}
                                        </td>
                                        <td style="font-size: 2.5em;
                                            "  colspan="2">
                                            <input  type="hidden"  name="orderID" value="${dto.getId()}"/>
                                            <input type="submit" name="action" value="Reorder" onclick="return confirm('Are you sure to reOrder !');" />
                                        </td>
                                    </tr>
                                </form>
                                <br>

                            </c:forEach>

                            </tbody>
                        </table>
                    </c:if>
                </c:if>


                <c:if test="${ empty requestScope.List1 }">
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
