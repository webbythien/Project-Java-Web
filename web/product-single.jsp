<%@page import="data.ProductDTO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>SINGLE PRODUCT</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" type="image/x-icon" href="css/iconShortcut.jpg"/>  

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
    </head>
    <body class="goto-here">
        <%@ include file="header.jsp" %>
        <!-- END nav -->

        <div class="hero-wrap hero-bread" style="background-image: url('images/bg_1.jpg');">
            <div class="container">
                <div class="row no-gutters slider-text align-items-center justify-content-center">
                    <div class="col-md-9 ftco-animate text-center">
                        <p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home</a></span> <span class="mr-2"><a href="index.html">Product</a></span> <span>Product Single</span></p>
                        <h1 class="mb-0 bread">Product Single</h1>
                    </div>
                </div>
            </div>
        </div>

        <section class="ftco-section">
            <%!
                public String ratingStar(int rating) {
                    String star = "<a href=\"#\">\n" + "<span class=\"ion-ios-star-outline\">\n" + "</span>\n" + "</a>\n";
                    String temp = "";
                    for (int i = 1; i <= rating; i++) {
                        temp = temp + star;
                    }
                    return temp;
                }
            %>

            <%!                public String htmlProductItem(String uid,int id, String imageLink, String name, String description, float price, float weight, String NumStar, int rating, int quantity, int sold, String origin) {
                    float total = 0.00f;
                    String left = Integer.toString(quantity - sold);
                    if (sold > quantity) {
                        left = "SOLD OUT";
                    }
                    total = (float) (price * weight);
                    String productItem = "<div class=\"container\">\n"
                            + "<div class=\"row\">\n"
                            + "<div class=\"col-lg-6 mb-5 ftco-animate\">\n"
                            + "<a href=\"images/product-1.jpg\" class=\"image-popup\">\n" + "<img src=\"" + imageLink + "\" class=\"img-fluid\" alt=\"" + name + "\">\n" + "</a>\n"
                            + "</div>\n"
                            + "<div class=\"col-lg-6 product-details pl-md-5 ftco-animate\">\n"
                            + "<h3>\n" + name + "</h3>\n"
                            + "<div class=\"rating d-flex\">\n"
                            + "<p class=\"text-left mr-4\">\n"
                            + "<a href=\"#\" class=\"mr-2\">\n" + rating/* t?ng tb chia rating */ + "</a>\n"
                            + NumStar
                            + "</p>\n"
                            + "<p class=\"text-left mr-4\">\n"
                            //                            + "<a href=\"#\" class=\"mr-2\" style=\"color: #000;\">\n" + rating /*bao nhiêu id rating*/ + "<span style=\"color: #bbb;\">\n" + "</span></a>\n"
                            + "</p>\n"
                            + "<p class=\"text-left\">\n"
                            + "<a href=\"#\" class=\"mr-2\" style=\"color: #000;\">\n" + sold + "<span style=\"color: #bbb;\">\n" + " SOLD" + "</span>\n" + "</a>\n"
                            + "</p>\n"
                            + "</div>\n"
                            + "<p class=\"price\">\n" + "<span>\n $" + total + " / " + weight + "KG</span>\n" + "</p>\n"
                            + "<p>\n" + description + "</p>\n"
                            + "<div class=\"row mt-4\">\n"
                            + "<div class=\"col-md-6\">\n"
                            + "<div class=\"form-group d-flex\">\n"
                            + "<div class=\"select-wrap\">\n"
                            + "<div class=\"icon\">\n" + "<span class=\"ion-ios-arrow-down\">\n" + "</span>\n" + "</div>\n"
                            //                                        +"<select name=\"\" id=\"\" class=\"form-control\">\n"
                            ////                                            +"<option value=\"\">\n"Small + "</option>\n"
                            ////                            + "<option value=\"\">\n"Medium + "</option>\n"
                            ////                            + "<option value=\"\">\n"Large + "</option>\n"
                            ////                            + "<option value=\"\">\n"
                            ////                    Extra Large
                            ////                    +"</option>\n"
                            //                                        +"</select>\n"
                            + "</div>\n"
                            + "</div>\n"
                            + "</div>\n"
                            + "<div class=\"w-100\">\n" + "</div>\n"
                            + "<div class=\"input-group col-md-6 d-flex mb-3\">\n"
                            //                            + "<span class=\"input-group-btn mr-2\">\n"
                            //                            + "<button type=\"button\" class=\"quantity-left-minus btn\"  data-type=\"minus\" data-field=\"\">\n"
                            //                            + "<i class=\"ion-ios-remove\">\n" + "</i>\n"
                            //                            + "</button>\n"
                            //                            + "</span>\n"
                            + "<input type=\"number\" id=\"quantity\" name=\"quantity\" class=\"form-control input-number\" value=\"1\" min=\"1\" max=\"" + quantity + "\" form=\"addToCart\">\n"
                            //                            + "<span class=\"input-group-btn ml-2\">\n"
                            //                            + "<button type=\"button\" class=\"quantity-right-plus btn\" data-type=\"plus\" data-field=\"\">\n"
                            //                            + "<i class=\"ion-ios-add\">\n" + "</i>\n"
                            //                            + "</button>\n"
                            + "</span>\n"
                            + "</div>\n"
                            + "<div class=\"w-100\">\n" + "</div>\n"
                            + "<div class=\"col-md-12\">\n"
                            + "<p style=\"color: #000;\">STOCK : \n" + quantity + "</p>\n"
                            + "<p style=\"color: #000;\">ORIGIN : \n" + origin + "</p>\n"
                            + "</div>\n"
                            + "</div>\n"
                            + "<form action=\"main\" id=\"addToCart\" method=\"get\">\n"
                            + "<input type=\"hidden\" name=\"idItem\" value=\"" + id + "\">\n"
                            + "<input type=\"hidden\" name=\"idUser\" value=\"" + uid + "\">\n"
                            + "<button name=\"action\" class=\"btn btn-black py-3 px-5\" value=\"AddCart\" type=\"submit\">\n"
                            + "<span>Add to Cart</span>\n"
                            + " </button>\n"
                            + "</form>\n"
                            + "</div>\n"
                            + "</div>\n"
                            + "</div>\n";

                    return productItem;
                }
            %>
        </section>


        <%  
           String uID = (String) session.getAttribute("LOGIN_USER_ID");
            ProductDTO pro = (ProductDTO) request.getAttribute("itemID");
            int rating = (int) request.getAttribute("rating");
            int sold = (int) request.getAttribute("stock");
            String err = (String) request.getAttribute("ERROR_MESS");
            String numStar = ratingStar(rating);
            if (err
                    == null) {
                err = "";
            }
            if (pro
                    != null) {
                out.print(htmlProductItem(uID,pro.getId(), pro.getImaga_link(), pro.getName(), pro.getDescription(), pro.getPrice(), pro.getWeight(), numStar, rating, pro.getQuantity(), sold , pro.getOrigin()));
            }

            if (!err.equalsIgnoreCase(
                    "")) {
                out.print("<h2>" + err + "</h2>");
            }

        %>


        <section class="ftco-section ftco-no-pt ftco-no-pb py-5 bg-light">
            <div class="container py-4">
                <div class="row d-flex justify-content-center py-5">
                    <div class="col-md-6">
                        <h2 style="font-size: 22px;" class="mb-0">Subcribe to our Newsletter</h2>
                        <span>Get e-mail updates about our latest shops and special offers</span>
                    </div>
                    <div class="col-md-6 d-flex align-items-center">
                        <form action="#" class="subscribe-form">
                            <div class="form-group d-flex">
                                <input type="text" class="form-control" placeholder="Enter email address">
                                <input type="submit" value="Subscribe" class="submit px-3">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <footer class="ftco-footer ftco-section">
            <div class="container">
                <div class="row">
                    <div class="mouse">
                        <a href="#" class="mouse-icon">
                            <div class="mouse-wheel"><span class="ion-ios-arrow-up"></span></div>
                        </a>
                    </div>
                </div>
                <div class="row mb-5">
                    <div class="col-md">
                        <div class="ftco-footer-widget mb-4">
                            <h2 class="ftco-heading-2">Vegefoods</h2>
                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia.</p>
                            <ul class="ftco-footer-social list-unstyled float-md-left float-lft mt-5">
                                <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a></li>
                                <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
                                <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md">
                        <div class="ftco-footer-widget mb-4 ml-md-5">
                            <h2 class="ftco-heading-2">Menu</h2>
                            <ul class="list-unstyled">
                                <li><a href="#" class="py-2 d-block">Shop</a></li>
                                <li><a href="#" class="py-2 d-block">About</a></li>
                                <li><a href="#" class="py-2 d-block">Journal</a></li>
                                <li><a href="#" class="py-2 d-block">Contact Us</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="ftco-footer-widget mb-4">
                            <h2 class="ftco-heading-2">Help</h2>
                            <div class="d-flex">
                                <ul class="list-unstyled mr-l-5 pr-l-3 mr-4">
                                    <li><a href="#" class="py-2 d-block">Shipping Information</a></li>
                                    <li><a href="#" class="py-2 d-block">Returns &amp; Exchange</a></li>
                                    <li><a href="#" class="py-2 d-block">Terms &amp; Conditions</a></li>
                                    <li><a href="#" class="py-2 d-block">Privacy Policy</a></li>
                                </ul>
                                <ul class="list-unstyled">
                                    <li><a href="#" class="py-2 d-block">FAQs</a></li>
                                    <li><a href="#" class="py-2 d-block">Contact</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md">
                        <div class="ftco-footer-widget mb-4">
                            <h2 class="ftco-heading-2">Have a Questions?</h2>
                            <div class="block-23 mb-3">
                                <ul>
                                    <li><span class="icon icon-map-marker"></span><span class="text">203 Fake St. Mountain View, San Francisco, California, USA</span></li>
                                    <li><a href="#"><span class="icon icon-phone"></span><span class="text">+2 392 3929 210</span></a></li>
                                    <li><a href="#"><span class="icon icon-envelope"></span><span class="text">info@yourdomain.com</span></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 text-center">

                        <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                            Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart color-danger" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        </p>
                    </div>
                </div>
            </div>
        </footer>



        <!-- loader -->
        <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


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

        <script>
                                $(document).ready(function () {

                                    var quantitiy = 0;
                                    $('.quantity-right-plus').click(function (e) {

                                        // Stop acting like a button
                                        e.preventDefault();
                                        // Get the field name
                                        var quantity = parseInt($('#quantity').val());

                                        // If is not undefined

                                        $('#quantity').val(quantity + 1);


                                        // Increment

                                    });

                                    $('.quantity-left-minus').click(function (e) {
                                        // Stop acting like a button
                                        e.preventDefault();
                                        // Get the field name
                                        var quantity = parseInt($('#quantity').val());

                                        // If is not undefined

                                        // Increment
                                        if (quantity > 0) {
                                            $('#quantity').val(quantity - 1);
                                        }
                                    });

                                });
        </script>

    </body>
</html>