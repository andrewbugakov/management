<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
        <h1>Отчеты за месяц</h1>
        <hr>
        <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
           <thead>
              <tr>
                 <th>Дата</th>
                 <th>Начало дня</th>
                 <th>Окончание дня</th>
                 <th>Список задач</th>
                 <th>Комментарий о дне</th>
              </tr>
           </thead>
           <tfoot>
              <tr>
                 <th>Дата</th>
                 <th>Начало дня</th>
                 <th>Окончание дня</th>
                 <th>Список задач</th>
                 <th>Комментарий о дне</th>
              </tr>
           </tfoot>
           <tbody>
              <c:forEach items="${days}" var="day">
                 <tr>
                    <td>${day.day}</td>
                    <td>${day.startTime}</td>
                    <td>${day.endTime}</td>
                    <td><a href="#" >
                    Задачи</a></td>
                    <td>${day.des}</td>

                 </tr>
              </c:forEach>
           </tbody>
        </table>
      </div>
<%@include file="../modal/tasksmodal.jsp" %>
