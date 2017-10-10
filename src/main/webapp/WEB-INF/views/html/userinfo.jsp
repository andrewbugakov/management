<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page isELIgnored="false" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
                <!DOCTYPE html>
                <html>

                <head>
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                    <title>Management</title>
                    <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>
                    <link rel='stylesheet prefetch' href='https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css'>
                    <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css'>
                    <link rel="stylesheet" href="/static/css/style.css">
                    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js'></script>
                    <script src='https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js'></script>
                    <script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js'></script>
                    <script src='https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js'></script>
                    <script src='https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js'></script>
                    <script src="/static/js/index.js"></script>
                </head>

                <body>

                    <div>
                        <%@include file="navbar/navbar.jsp" %>
                            <div class='row' id='rowbody'>
                                <%@include file="accordion/accordeonMenu.jsp" %>
                                    <div class="col-md-8 col-sm-6 col-xs-12" id='content' style="display:inline-block;" id='content'>
                                        <h1>${user.firstName} ${user.patronymic} ${user.lastName}</h1>
                                        <hr>
                                        <p>
                                            <table class="table table-hover">
                                                <!--<thead>
                                                    <tr>
                                                        <th>Контактная информация</th>
                                                    </tr>
                                                </thead>-->
                                                <tbody>
                                                    <tr>
                                                        <td>День рождения</td>
                                                        <td>${user.birthday}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Телефон</td>
                                                        <td>${user.phone}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Рабочий телефон</td>
                                                        <td>${user.work_phone}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Внутренний телефон</td>
                                                        <td>${user.interoffice_phone}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Должность</td>
                                                        <td>${user.position}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Начальник</td>
                                                        <td>${user.subordinator.firstName} ${user.subordinator.lastName}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Отдел</td>
                                                        <td>${user.department.title}</td>
                                                    </tr>
                                                    <tr>
                                                         <td>Глава отдела</td>
                                                         <td>${user.dept_where_head.title}</td>
                                                    </tr>
                                                    <tr>
                                                         <td>Глава офиса</td>
                                                         <td>${user.branch_where_head.name_office}</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </p>
                                        </divtasks </div>
                                    </div>


                                    <%@include file="modal/modal1.jsp" %>



                </body>

                </html>