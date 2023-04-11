<%-- 
    Document   : editproduct.jsp
    Created on : Mar 12, 2023, 10:44:18 AM
    Author     : thien
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="image/x-icon" href="css/iconShortcut.jpg"/>
        <title>Product Edit</title>
        <style>
            @import url(https://fonts.googleapis.com/css?family=Roboto:400,300,600,400italic);
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box;
                -webkit-font-smoothing: antialiased;
                -moz-font-smoothing: antialiased;
                -o-font-smoothing: antialiased;
                font-smoothing: antialiased;
                text-rendering: optimizeLegibility;
            }

            body {
                font-family: "Roboto", Helvetica, Arial, sans-serif;
                font-weight: 100;
                font-size: 12px;
                line-height: 30px;
                color: #777;
                background: #4CAF50;
            }

            .container {
                max-width: 400px;
                width: 100%;
                margin: 0 auto;
                position: relative;
            }

            #contact input[type="text"],
            #contact input[type="email"],
            #contact input[type="tel"],
            #contact input[type="url"],
            #contact textarea,
            #contact input[type="submit"] {
                font: 400 12px/16px "Roboto", Helvetica, Arial, sans-serif;
            }

            #contact {
                background: #F9F9F9;
                padding: 25px;
                margin: 50px 0;
                box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
            }

            #contact h3 {
                display: block;
                font-size: 30px;
                font-weight: 300;
                margin-bottom: 10px;
            }

            #contact h4 {
                margin: 5px 0 15px;
                display: block;
                font-size: 13px;
                font-weight: 400;
            }

            fieldset {
                border: medium none !important;
                margin: 0 0 10px;
                min-width: 100%;
                padding: 0;
                width: 100%;
            }

            #contact input[type="text"],
            #contact input[type="email"],
            #contact input[type="tel"],
            #contact input[type="url"],
            #contact textarea {
                width: 100%;
                border: 1px solid #ccc;
                background: #FFF;
                margin: 0 0 5px;
                padding: 10px;
            }

            #contact input[type="text"]:hover,
            #contact input[type="email"]:hover,
            #contact input[type="tel"]:hover,
            #contact input[type="url"]:hover,
            #contact textarea:hover {
                -webkit-transition: border-color 0.3s ease-in-out;
                -moz-transition: border-color 0.3s ease-in-out;
                transition: border-color 0.3s ease-in-out;
                border: 1px solid #aaa;
            }

            #contact textarea {
                height: 100px;
                max-width: 100%;
                resize: none;
            }

            #contact input[type="submit"] {
                cursor: pointer;
                width: 100%;
                border: none;
                background: #4CAF50;
                color: #FFF;
                margin: 0 0 5px;
                padding: 10px;
                font-size: 15px;
            }

            #contact input[type="submit"]:hover {
                background: #43A047;
                -webkit-transition: background 0.3s ease-in-out;
                -moz-transition: background 0.3s ease-in-out;
                transition: background-color 0.3s ease-in-out;
            }

            #contact input[type="submit"]:active {
                box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.5);
            }


            button {
                cursor: pointer;
                width: 100%;
                border: none;
                background: #4CAF50;
                color: #FFF;
                margin: 0 0 5px;
                padding: 10px;
                font-size: 15px;
            }

            button:hover {
                background: #43A047;
                -webkit-transition: background 0.3s ease-in-out;
                -moz-transition: background 0.3s ease-in-out;
                transition: background-color 0.3s ease-in-out;
            }

            button:active {
                box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.5);
            }

            a {
                cursor: pointer;
                width: 100%;
                border: none;
                background: #4CAF50;
                color: #FFF;
                margin: 0 0 5px;
                padding: 10px;
                font-size: 15px;
            }

            a:hover {
                background: #43A047;
                -webkit-transition: background 0.3s ease-in-out;
                -moz-transition: background 0.3s ease-in-out;
                transition: background-color 0.3s ease-in-out;
            }

            a:active {
                box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.5);
            }

            .copyright {
                text-align: center;
            }

            #contact input:focus,
            #contact textarea:focus {
                outline: 0;
                border: 1px solid #aaa;
            }

            ::-webkit-input-placeholder {
                color: #888;
            }

            :-moz-placeholder {
                color: #888;
            }

            ::-moz-placeholder {
                color: #888;
            }

            :-ms-input-placeholder {
                color: #888;
            }
        </style>


    </head>
    <body>
        <c:url var="Search" value="main">
            <c:param name="action" value="ManageProduct"></c:param>
        </c:url> 

        <c:url var="HomeLink" value="main">
            <c:param name="action" value="Search"></c:param>
        </c:url> 



        <a href="${HomeLink}">Home</a>
        <a href="${Search}&task=Create">Search</a>

        <c:choose>
            <c:when test="${sessionScope.USER.role.equals('AD')}">
                <div class="container">  
                    <form id="contact" action="main" method="post">
                        <h3>${requestScope.task} Product</h3>
                        <fieldset>
                            <input placeholder="Product's name" name="name" value="${requestScope.object.getName()}" type="text" tabindex="1" required autofocus>
                        </fieldset>
                        <fieldset>
                            <input placeholder="Product's description" type="text" name="description" value="${requestScope.object.getDescription()}" tabindex="2" required>
                        </fieldset>
                        <fieldset>
                            <input placeholder="Product's color" type="text" name="color" value="${requestScope.object.getColor()}" tabindex="3" required>
                        </fieldset>
                        <fieldset>
                            <input placeholder="Product's price" type="text" name="price" value="${requestScope.object.getPrice()}" tabindex="4" required>
                        </fieldset>
                        <fieldset>
                            <input  placeholder="Product's link image" tabindex="5" id="imgOld" required="" type="url" name="url" value="${requestScope.object.getImaga_link()}" />
                            <input id="imgBack" style="display: none"  type="hiiden"  value="${requestScope.object.getImaga_link()}" />
                            <!--                        <textarea placeholder="Type your message here...." tabindex="5" required></textarea>-->
                        </fieldset>
                        <fieldset>
                            <img tabindex="6" id="image" style="height: 100px; width: auto" src="${requestScope.object.getImaga_link()}" />
                        </fieldset>

                        <fieldset>
                            <input placeholder="Product's quantity" type="text" name="quantity" value="${requestScope.object.getQuantity()}" tabindex="7" required>
                        </fieldset>

                        <fieldset>
                            <input placeholder="Product's weight" type="text" name="weight" value="${requestScope.object.getWeight()}" tabindex="8" required>
                        </fieldset>

                        <fieldset>
                            <input placeholder="Product's category" type="text" name="category" value="${requestScope.object.getCategory()}" tabindex="9" required>
                        </fieldset>

                        <fieldset>
                            <input placeholder="Product's origin" type="text" name="origin" value="${requestScope.object.getOrigin()}" tabindex="9" required>
                        </fieldset>

                        <fieldset>
                            <c:if test="${requestScope.task.equals('Update')}"> 
                                <input type="hidden" name="proId" value="${requestScope.object.getId()}" />
                            </c:if>
                            <input type="hidden" name="action" value="Edit"/>
                            <input  type="submit" name="task" value="${requestScope.task}"/>

                        </fieldset>
                    </form>

                    <button onclick="changeImage()">Test Image Link New</button>
                    <c:if test="${requestScope.task.equals('Update')}">
                        <button onclick="changeImageOld()">Back Image Original</button>  
                    </c:if>
                </div>



            </c:when>
            <c:otherwise>
                <c:redirect url="logout"></c:redirect>
            </c:otherwise>
        </c:choose>

        <script>
            function changeImage() {
                var imgNew = document.getElementById('imgOld');
                document.getElementById('image').src = imgNew.value;
            }
        </script>

        <script>
            function changeImageOld() {
                var imgNew = document.getElementById('imgBack');
                document.getElementById('image').src = imgNew.value;
                document.getElementById('imgOld').value = imgNew.value;

            }
        </script>
    </body>
</html>
