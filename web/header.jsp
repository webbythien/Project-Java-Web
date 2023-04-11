s<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Header</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="shortcut icon" type="image/x-icon" href="css/iconShortcut.jpg" />
        <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Amatic+SC:400,700&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
        
        <link rel="stylesheet" href="css/animate.css">

        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">

        <link rel="stylesheet" href="css/aos.css">

        <link rel="stylesheet" href="css/ionicons.min.css">

        <link rel="stylesheet" href="css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="css/jquery.timepicker.css">


        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/icomoon.css">
        <link rel="stylesheet" href="css/style.css">

        <link rel="stylesheet" href="css/searchCSS.css">


    </head>
    <body class="goto-here">
        <div class="py-1 bg-primary">
            <div class="container">
                <div class="row no-gutters d-flex align-items-start align-items-center px-md-0">
                    <div class="col-lg-12 d-block">
                        <div class="row d-flex">
                            <div class="col-md pr-4 d-flex topper align-items-center">
                                <div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-phone2"></span></div>
                                <span class="text">+ 1235 2355 98</span>
                            </div>
                            <div class="col-md pr-4 d-flex topper align-items-center">
                                <div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-paper-plane"></span></div>
                                <span class="text">youremail@email.com</span>
                            </div>
                            <div class="col-md-5 pr-4 d-flex topper align-items-center text-lg-right">
                                <span class="text">3-5 Business days delivery &amp; Free Returns</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
            <div class="container">
                <a class="navbar-brand" href="index.jsp"><img src="css/iconShortcut.jpg" width="70"  height="100%"  alt="Home Macca Shopping"/>Macca</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="oi oi-menu"></span> Menu
                </button>

                <div class="collapse navbar-collapse" id="ftco-nav">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active"><a href="index.jsp" class="nav-link">Home</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Shop</a>
                            <div class="dropdown-menu" aria-labelledby="dropdown04">
                                <a class="dropdown-item" href="main?action=Shop">Shop</a>
                                <!--<a class="dropdown-item" href="wishlist.html">Wishlist</a>-->
                                <!--                                <a class="dropdown-item" href="product-single.html">Single Product</a>-->
                                <a class="dropdown-item" href="main?action=AddCart">Cart</a>


                                <a class="dropdown-item" href="checkout">Checkout</a>
                            </div>
                        </li>
                        <%                            String key = (String) request.getParameter("keyword");
                            if (key == null) {
                                key = "";
                            }
                        %>
                        <!--<li class="nav-item"><a href="contact.html" class="nav-link">Contact</a></li>-->
                        <li class="nav-item">
                            <form action="main" class="search-bar" method="get">
                                <input type="text" name="keyword" pattern=".*\S.*" required value="<%= key%>" >
                                <button name="action" class="search-btn" value="Search" type="submit">
                                    <span>Search</span>
                                </button>
                            </form>
                        </li>
                        <%
                            String loginUser = (String) session.getAttribute("LOGIN_USER");
                            if (loginUser != null) {
                        %>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="main?action=InforUser" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Hello <%=loginUser%></a>
                            <div class="dropdown-menu" aria-labelledby="dropdown04">




                                <c:choose>
                                    <c:when test="${sessionScope.USER.role.equals('US')}">
                                        <form action="main" style="text-align: center" style="border: 0">
                                            <input style="background: none"  type="submit" name="action" value="Order"/>
                                        </form> 

                                        <form action="main" style="text-align: center" style="border: 0">
                                            <input style="background: none"  type="submit" name="action" value="InforUser"/>
                                        </form>
                                    </c:when>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${sessionScope.USER.role.equals('AD')}">
                                        <form action="main" style="text-align: center" style="border: 0">
                                            <input style="background: none"  type="submit" name="action" value="SearchAcc"/>
                                        </form>

                                        <form action="main" style="text-align: center" style="border: 0">
                                            <input style="background: none"  type="submit" name="action" value="ManageOrder"/>
                                        </form>

                                        <form action="main" style="text-align: center" style="border: 0">
                                            <input style="background: none"  type="submit" name="action" value="ManageProduct"/>
                                        </form>
                                    </c:when>
                                </c:choose>
                                <form action="main" style="text-align: center" style="border: 0">
                                    <input style="background: none"  type="submit" name="action" value="Logout"/>
                                </form>
                                <!--                                                                
                                                                                                <a class="dropdown-item" href="cart.html">Cart</a>
                                                                                                <a class="dropdown-item" href="checkout.html">Checkout</a>-->
                            </div>

                        </li>

                        <%
                            }
                            if (loginUser == null) {
                        %>              
                        <li class="nav-item"><a href="login.jsp" class="nav-link">Login / Sign Up</a></li>
                            <%
                                }

                            %>

                        <li class="nav-item cta cta-colored"><a href="cart.jsp" class="nav-link"><span class="icon-shopping_cart"></span>[${requestScope.NUM_CART}]</a></li>

                    </ul>
                </div>
            </div>
        </nav>

        <script src="js/jquery.min.js"></script>
        <script src="js/jquery-migrate-3.0.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.easing.1.3.js"></script>
        <script src="js/jquery.waypoints.min.js"></script>
        <script src="js/jquery.stellar.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/aos.js"></script>
        <script src="js/jquery.animateNumber.min.js"></script>
        <script src="js/bootstrap-datepicker.js"></script>
        <script src="js/scrollax.min.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
        <script src="js/google-map.js"></script>
        <script src="js/main.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
        <script>
            $(document).ready(function () {
                $("a.scrollLink").click(function (event) {
                    event.preventDefault();
                    $("html, body").animate({
                        scrollTop: $($(this).attr("href")).offset().top
                    }, 500);
                });
            });

        </script>
    </body>

</html>
