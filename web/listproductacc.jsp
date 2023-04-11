<%-- 
    Document   : listproductacc
    Created on : Mar 11, 2023, 11:57:52 PM
    Author     : thien
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Product</title>
        <link rel="stylesheet" href="css/table.css">
        <link rel="stylesheet" type="image/x-icon" href="css/iconShortcut.jpg"/>
    </head>
    <body>
        <c:url var="Create" value="main">
            <c:param name="action" value="Edit"></c:param>
        </c:url> 

        <c:url var="HomeLink" value="main">
            <c:param name="action" value="Search"></c:param>
        </c:url> 

        <c:url var="OutOfStock" value="main">
            <c:param name="action" value="ManageProduct"></c:param>
        </c:url> 

        <a href="${HomeLink}">Home</a>
        <a href="${Create}&task=Create">Create</a>
        <a href="${OutOfStock}&outStock=0">Out Of Stock</a>



        <h1>${requestScope.Message}</h1>
        <c:choose>

            <c:when test="${sessionScope.USER.role.equals('AD')}">
                <form action="main">
                    Search <input style="width: 20%" type="text" placeholder="Search" name="search" value=""/>
                    <input type="submit" name="action" value="ManageProduct"/>
                </form>
                <c:if test="${requestScope.List!=null}">
                    <c:if test="${not empty requestScope.List}">
                        <table class="container">
                            <thead>
                                <tr>
                                    <th><h1>Product ID</h1></th>
                                    <th><h1>Image</h1></th>
                                    <th><h1>Product's Name</h1></th>
                                    <th><h1>Price</h1></th>
                                    <th><h1>Quantity</h1></th>
                                    <th><h1>Weight</h1></th>
                                    <th><h1>Origin</h1></th>
                                    <th><h1>Delete</h1></th>
                                    <th><h1>Update</h1></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td style="font-size: 2.5em;color: red;" colspan="10">
                                        Product
                                    </td>
                                </tr>
                                <c:forEach var="dto" varStatus="counter" items="${requestScope.List}">

                                <form action="main">

                                    <tr>


                                        <td>
                                            ${dto.getId()}
                                        </td>

                                        <td>

                                            <img style="height: 100px; width: auto" src="${dto.getImaga_link()}" />

                                        </td>
                                        <td style="width:10%; word-wrap: break-word;">${dto.getName()}</td>



                                        <td style="width: 10%">
                                            $ ${dto.getPrice()}
                                        </td>

                                        <td>
                                            ${dto.getQuantity()}
                                        </td>

                                        <td>
                                            ${dto.getWeight()} KG
                                        </td>

                                        <td>
                                            ${dto.getOrigin()}
                                        </td>
                                        <td style="width: 10%">
                                            <input  type="hidden"  name="action" value="Edit"/>
                                            <input  type="hidden"  name="proId" value="${dto.getId()}"/>
                                            <input type="submit" name="task" value="Delete" onclick="return confirm('Are you sure Delete ${dto.getName()    }!');" />   
                                        </td>
                                        <td style="width: 10%">
                                            <input  type="hidden"  name="action" value="Edit"/>
                                            <input  type="hidden"  name="proId" value="${dto.getId()}"/>
                                            <input type="submit" name="task" value="Change"/>   
                                        </td>
                                    </tr>
                                    <!--mai làm tiếp phần delte update và create của thực phẩm
                                    làm thêm chỉnh sửa thông tin cá nhân của user-->

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
