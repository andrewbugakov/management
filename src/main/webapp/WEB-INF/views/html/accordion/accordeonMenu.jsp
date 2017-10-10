<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="col-md-4 col-sm-6 col-xs-12  panel-group" id="accordion" style="display:inline-block;">
                <div class="panel panel-default">
                    <div class="panel-heading" role="button" data-toggle="collapse" href="#collapse1" aria-expanded="false" aria-controls="#collapse1">
                        <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
            Рабочий день <span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span></a>
      </h4>
                    </div>
                    <div id="collapse1" class="panel-collapse collapse in">
                        <ul class="list-group linked-left-menu">
                            <li id='tasksForDay' class="list-group-item" onclick="tasksForDay(this.id)">Задачи на день</li>
                            <li id="meetings" class="list-group-item" onclick="meetings(this.id)">Собрания и планерки</li>
                            <!--<li id="calendar" class="list-group-item" onclick="calendar(this.id)">Календарь</li>
                            <li id="documentLoop" class="list-group-item" onclick="documentLoop(this.id)">Документооборот?!</li>-->
                        </ul>

                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="button" data-toggle="collapse" href="#collapse2" aria-expanded="false" aria-controls="#collapse2">
                        <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">
        Сотрудники  <span class="glyphicon glyphicon-user" aria-hidden="true"></span></a>
      </h4>
                    </div>
                    <div id="collapse2" class="panel-collapse collapse">
                        <ul class="list-group linked-left-menu">
                            <li id='searchEmp' onclick="searchEmp(this.id)" class="list-group-item">Поиск сотрудника</li>
                            <li id='phones' onclick="phones(this.id)" class="list-group-item">Телефонный справочник</li>
                            <li id='birthdays' onclick="birthdays(this.id)" class="list-group-item">Дни рождения</li>
                          <!-- <li id='hallOfFame' onclick="hallOfFame(this.id)" class="list-group-item">Доска почета?!</li>-->
                        </ul>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="button" data-toggle="collapse" href="#collapse3" aria-expanded="false" aria-controls="#collapse3">
                        <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">
        Компания  <span class="glyphicon glyphicon-home" aria-hidden="true"></span></a>
      </h4>
                    </div>
                    <div id="collapse3" class="panel-collapse collapse">
                        <ul class="list-group linked-left-menu">
                            <li id='companyStructure' onclick="companyStructure(this.id)" class="list-group-item">Структура компании</li>
                            <!--<li id='calendarEvents' onclick="calendarEvents(this.id)" class="list-group-item">Календарь событий</li>-->
                            <li id='shedule' onclick="shedule(this.id)" class="list-group-item">График работы</li>
                            <!--<li id='vacations' onclick="vacations(this.id)" class="list-group-item">Календарь отпусков?!</li>-->
                        </ul>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="button" data-toggle="collapse" href="#collapse4" aria-expanded="false" aria-controls="#collapse4">
                        <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapse4">
        Мои отчеты  <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span></a>
      </h4>
                    </div>
                    <div id="collapse4" class="panel-collapse collapse">
                        <ul class="list-group linked-left-menu">
                            <li id='lates' onclick="lates(this.id)" class="list-group-item">Опоздания</li>
                            <li id='tasks' onclick="tasks(this.id)" class="list-group-item">Задачи</li>
                            <li id='efficient' onclick="efficient(this.id)" class="list-group-item">Отчеты за месяц</li>
                           <!-- <li id='suborders' onclick="suborders(this.id)" class="list-group-item">Отчеты подчиненных</li>-->

                        </ul>
                    </div>
                </div>
               <!-- <div class="panel panel-default">
                    <div class="panel-heading" role="button" data-toggle="collapse" href="#collapse5" aria-expanded="false" aria-controls="#collapse5">
                        <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapse5">
        Тех. поддержка  <span class="glyphicon glyphicon-wrench" aria-hidden="true"></span></a>
      </h4>
                    </div>
                    <div id="collapse5" class="panel-collapse collapse">
                        <ul class="list-group linked-left-menu">
                            <li id='leaderships' onclick="leaderships(this.id)" class="list-group-item">Руководство</li>
                            <li id='lettersAdmin' onclick="letterForAdmin(this.id)" class="list-group-item">Письмо администратору</li>
                        </ul>
                    </div>
                </div>-->
            </div>