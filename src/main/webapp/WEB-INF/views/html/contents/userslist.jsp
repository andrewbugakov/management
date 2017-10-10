<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link href="https://cdn.datatables.net/1.10.11/css/dataTables.bootstrap4.min.css" rel="stylesheet" />
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="/static/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.11/js/dataTables.bootstrap4.min.js"></script>
<script src="/static/js/index.js"></script>
<h1>Сотрудники</h1>
<hr>
<table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
   <thead>
         <tr>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Email</th>
            <th>Логин</th>
            <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
               <th width="100"></th>
            </sec:authorize>
            <sec:authorize access="hasRole('ADMIN')">
               <th width="100"></th>
            </sec:authorize>
         </tr>
      </thead>
      <tfoot>
            <tr>
               <th>Имя</th>
               <th>Фамилия</th>
               <th>Email</th>
               <th>Логин</th>
               <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                  <th width="100"></th>
               </sec:authorize>
               <sec:authorize access="hasRole('ADMIN')">
                  <th width="100"></th>
               </sec:authorize>
            </tr>
         </tfoot>
      <tbody>
         <c:forEach items="${users}" var="user">
            <tr onclick="go2UserInfo('${user.ssoId}');">
               <td>${user.firstName}</td>
               <td>${user.lastName}</td>
               <td>${user.email}</td>
               <td>${user.ssoId}</td>
               <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                  <td><a href="<c:url value='/admin/edit-user-${user.ssoId}' />" class="btn btn-success custom-width">Изменить</a></td>
               </sec:authorize>
               <sec:authorize access="hasRole('ADMIN')">
                  <td><a href="<c:url value='/admin/delete-user-${user.ssoId}' />" class="btn btn-danger custom-width">Удалить</a></td>
               </sec:authorize>
            </tr>
         </c:forEach>
      </tbody>
   </table>
   <sec:authorize access="hasRole('ADMIN')">
      <div class="well">
         <a href="<c:url value='/admin/newuser' />">Добавить нового пользователя</a>
      </div>
   </sec:authorize>
