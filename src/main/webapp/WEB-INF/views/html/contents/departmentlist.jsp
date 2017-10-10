<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link href="https://cdn.datatables.net/1.10.11/css/dataTables.bootstrap4.min.css" rel="stylesheet" />
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="/static/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.11/js/dataTables.bootstrap4.min.js"></script>
<script src="/static/js/index.js"></script>
<h1>Отделы</h1>
<hr>
<table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
   <thead>
         <tr>
            <th>Название отдела</th>
            <th>Филиал</th>
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
               <th>Филиал</th>
               <th>Название отдела</th>
               <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                  <th width="100"></th>
               </sec:authorize>
               <sec:authorize access="hasRole('ADMIN')">
                  <th width="100"></th>
               </sec:authorize>
            </tr>
         </tfoot>
      <tbody>
         <c:forEach items="${departments}" var="department">
            <tr>
               <td>${department.title}</td>
               <td>${department.branchOffice.name_office}</td>
               <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                  <td><a href="<c:url value='/admin/edit-dept-${department.id_department}' />" class="btn btn-success custom-width">Изменить</a></td>
               </sec:authorize>
               <sec:authorize access="hasRole('ADMIN')">
                  <td><a href="<c:url value='/admin/delete-dept-${department.id_department}' />" class="btn btn-danger custom-width">Удалить</a></td>
               </sec:authorize>
            </tr>
         </c:forEach>
      </tbody>
   </table>
   <sec:authorize access="hasRole('ADMIN')">
      <div class="well">
         <a href="<c:url value='/admin/newdept' />">Добавить новый отдел</a>
      </div>
   </sec:authorize>