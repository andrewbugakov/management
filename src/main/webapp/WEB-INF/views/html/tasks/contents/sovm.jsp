<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link href="https://cdn.datatables.net/1.10.11/css/dataTables.bootstrap4.min.css" rel="stylesheet" />
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="/static/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.11/js/dataTables.bootstrap4.min.js"></script>
<script src="/static/js/index.js"></script>
<h1>Выполненные задачи</h1>
<hr>
<table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
   <thead>
         <tr>
            <th>Время создания задачи</th>
            <th>Срок выполнения задачи</th>
            <th>Задача</th>
            <th>Замечание к задаче</th>
            <th>Ответственный по задаче</th>
            <th>Статус задачи</th>
         </tr>
      </thead>
      <tfoot>
         <tr>
                    <th>Время создания задачи</th>
                    <th>Срок выполнения задачи</th>
                    <th>Задача</th>
                    <th>Замечание к задаче</th>
                    <th>Ответственный по задаче</th>
                    <th>Статус задачи</th>
                 </tr>
         </tfoot>
      <tbody>
         <c:forEach items="${tasks}" var="task">
            <tr id="${task.id_task}">
               <td>${task.timeStampCreate}</td>
               <td>${task.deadline}</td>
               <td>${task.titleTask}</td>
               <td>${task.desc}</td>
               <td>${task.userWhoDo.id}</td>
               <td>
               <c:choose>
                 <c:when test="${not empty day.actualEnd}">
                    Завершено
                 </c:when>
                 <c:when test="${not empty day.actualStart}">
                       В процессе
                 </c:when>
                 <c:otherwise>
                    Не выполнено
                 </c:otherwise>
               </c:choose>
               </td>
            </tr>
         </c:forEach>
      </tbody>
   </table>
