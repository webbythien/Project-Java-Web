<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="image/x-icon" href="css/iconShortcut.jpg"/> 
        <title>Login Macca Shopping</title>
        <style>
        </style>
        <link rel="stylesheet" href="css/login.css">
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css'>
    </head>
    <body>
    <body>

        <%
            String err = (String) session.getAttribute("ERROR_MESSAGE");
            String err2 = (String) session.getAttribute("ERROR_MESSAGE2");
            String email = (String) session.getAttribute("LOGIN_USER_EMAIL");
            if (email == null) {
                email = "";
            }

        %>
        <div class="container" id="container">

            <div class="form-container register-container">
                <form action="main" name="" method="post">
                    <h1>Register Macca.</h1>
                    <%if (err2 != null) {
                    %>
                    <i><%= err2%> </i>
                    <%
                        }%>
                    <input type="email" required="required" placeholder="Email" name="userEmail">
                    <input type="text" required="required" placeholder="Name" name="userName">
                    <input id="password-field" type="password" required="required" placeholder="Password" name="userPassword">
                    <span toggle="#password-field"  class="fa fa-fw fa-eye field-icon toggle-password"></span>

                    <button value="Register" name="action" type="submit">Register</button>
                    <!--           <input value="Register" name="action" type="submit">-->
                    <span>or use your account</span>
                    <div class="social-container">
                        <a href="#" class="social"><i class="lni lni-facebook-fill"></i></a>
                        <a href="#" class="social"><i class="lni lni-google"></i></a>
                        <a href="#" class="social"><i class="lni lni-linkedin-original"></i></a>
                    </div>
                </form>
            </div>  

            <div class="form-container login-container">
                <form action="main" method="post">
                    <h1>Login Macca</h1>
                    <%if (err != null) {
                    %>
                    <i><%= err%> </i>
                    <%
                        }
                        String currPath = (String) request.getAttribute("currPath");
                    %>
                    <input type="email" value="<%= email%>" placeholder="Email" name="email">

                    <input  id="password-field" type="password" placeholder="Password" name="password">
                    <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>

                    <div style="clear: both"></div>
                    <input type="hidden"  name="currPath" value="<%= currPath%>">
                    <!-- if currPath == Cart thi gui them idItem-->

                    <button  style="clear: both" value="Login" name="action" type="submit">Login</button>
                    <!--          <input value="Login" name="action" type="submit">-->
                    <span>or use your account</span>
                    <div class="social-container">
                        <a href="#" class="social"><i class="lni lni-facebook-fill"></i></a>
                        <a href="#" class="social"><i class="lni lni-google"></i></a>
                        <a href="#" class="social"><i class="lni lni-linkedin-original"></i></a>
                    </div>
                </form>
            </div>

            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1 class="title">Hello <br> friends</h1>
                        <p>if Yout have an account, login here and have fun</p>
                        <button class="ghost" id="login">Login
                            <i class="lni lni-arrow-left login"></i>
                        </button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1 class="title">Start yout <br> journy now</h1>
                        <p>if you don't have an account yet, join us and start your journey.</p>
                        <button class="ghost" id="register">Register
                            <i class="lni lni-arrow-right register"></i>
                        </button>
                    </div>
                </div>
            </div>

        </div>

        <script src="js/login.js"></script>
        <script src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js'></script>
        <script>
            $(".toggle-password").click(function () {

                $(this).toggleClass("fa-eye fa-eye-slash");
                var input = $($(this).attr("toggle"));
                if (input.attr("type") == "password") {
                    input.attr("type", "text");
                } else {
                    input.attr("type", "password");
                }
            });
        </script>
    </body>
</html>