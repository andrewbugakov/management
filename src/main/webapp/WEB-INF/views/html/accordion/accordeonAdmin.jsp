<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="col-md-4 col-sm-6 col-xs-12  panel-group" id="accordion" style="display:inline-block;">
   <div class="panel panel-default">
      <div class="panel-heading" role="button" data-toggle="collapse" href="#collapse1" aria-expanded="false" aria-controls="#collapse1">
         <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
            Структура компании <span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span></a>
         </h4>
      </div>
      <div id="collapse1" class="panel-collapse collapse in">
         <ul class="list-group linked-left-menu">
            <li id='branches' class="list-group-item" onclick="gobranches(this.id)">Филиалы</li>
            <li id="depts" class="list-group-item" onclick="godepts(this.id)">Отделы</li>
            <li id="users" class="list-group-item" onclick="gousers(this.id)">Сотрудники</li>
         </ul>
      </div>

   </div>
   <div class="panel panel-default">
         <div class="panel-heading" role="button" data-toggle="collapse" href="#collapse2" aria-expanded="false" aria-controls="#collapse2">
            <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapse2">
               Модель рабочего времени <span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span></a>
            </h4>
         </div>
         <div id="collapse2" class="panel-collapse collapse in">
            <ul class="list-group linked-left-menu">
               <li id='branches' class="list-group-item" onclick="goWorkTime()">Настройка рабочих часов</li>

            </ul>
         </div>

      </div>
</div>