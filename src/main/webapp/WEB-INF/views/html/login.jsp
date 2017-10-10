<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>LoginPage</title>
                <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>
                <link rel="stylesheet" href="/static/css/style.css">
                 <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js'></script>
                                <script src='https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js'></script>
                                <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js'></script>
            </head>

            <body>

                <div class="container">
                    <c:url var="loginUrl" value="/login" />
                    <form class="form-signin" action="${loginUrl}" method="post">
                        <h2 class="form-signin-heading">Авторизация</h2>
                        <c:if test="${param.error != null}">
                            <div class="alert alert-danger">
                                <p>Некорректный логин или пароль.</p>
                            </div>
                        </c:if>
                        <c:if test="${param.logout != null}">
                            <div class="alert alert-success">
                                <p>Вы успешно вышли из учетной записи.</p>
                            </div>
                        </c:if>
                        <label for="inputEmail" class="sr-only">Логин</label>
                        <input type="text" id="inputEmail" name="ssoId" class="form-control" placeholder="Логин" required="" autofocus="">
                        <label for="inputPassword" class="sr-only">Password</label>
                        <input type="password" id="inputPassword"  name="password"  class="form-control" placeholder="Пароль" required="">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="remember-me"> Запомнить меня
                        </label>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
                    </form>
                </div>


            </body>

            </html>