<%@ page import="java.io.*,java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">SelfManagement</a>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <form class="navbar-form navbar-left" role="search" action="search">
                            <div class="input-group">
                                  <input id="search" name="search" type="text" class="form-control" placeholder="Поиск...">
                                  <span class="input-group-btn">
                                    <button class="btn btn-secondary" type="submit">Искать!</button>
                                  </span>
                                </div>
                            <!--<div class="form-group">
                                <input type="text" class="form-control" placeholder="Поиск"></input>
                                <button id="search" type="submit" class="btn"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                            </div>-->
                        </form>
                        <ul class="nav navbar-nav navbar-right">
                            <script>
                            </script>
                            <li><a href="#" data-toggle="modal" data-target="#myModal" onclick="getAllTasks();">
                            <div id='currentTime'></div>
                            </a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-bell" aria-hidden="true"></span> События </a>
                                <ul class="dropdown-menu">
                                <c:choose>
                                  <c:when test="${not empty meetings}">
                                    <c:forEach items="${meetings}" var="meeting">
                                            <li class='bg-info'><a href="#">${meeting.startTime} ${meeting.desc}</a></li>
                                    </c:forEach>
                                  </c:when>

                                  <c:otherwise>
                                        <li class='text-muted'>Нет предстоящих событий! Отдыхайте!</li>
                                        <li class='bg-info'><a href="/meetings">Запланировать событие</a></li>

                                  </c:otherwise>
                                </c:choose>


                                </ul>
                            </li>
                            <li id="startDay" onclick="startStopDay();"><a>
                            <span class="glyphicon glyphicon-play" aria-hidden="true"></span> Начать день</a>
                            </li>
                            <li id="pause"><a onclick="pauseDay();">
                            <span class="glyphicon glyphicon-pause" aria-hidden="true"></span> Пауза</a>
                            </li>

                            <li class="dropdown">
                                <a href="#"  class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${loggedinuser} <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="/"><span class="glyphicon glyphicon-tasks"></span> Мой день </a></li>
                                   <!-- <li><a href="/meetings"><span class="glyphicon glyphicon-globe"></span> Портал </a></li>-->
                                    <li><a href="<c:url value="/admin/" />"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span> Администрирование </a></li>
                                    <li><a href="" onclick="suborders(this.id)"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span> Отчеты </a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="<c:url value="/logout" />"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Выйти из учетной записи </a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <!-- /.navbar-collapse -->
                </div>
                <!-- /.container-fluid -->
            </nav>
        </div>