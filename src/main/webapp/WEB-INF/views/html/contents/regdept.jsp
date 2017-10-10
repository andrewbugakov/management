<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class='container'>
<div class=""><h2>Добавление отдела</h2><hr></div>
<form:form method="POST" modelAttribute="department" class="form-horizontal" accept-charset="UTF-8">
   <form:input type="hidden" path="id_department" id="id_department"/>
   <div class="row">
      <div class="form-group col-md-12">
         <label class="col-md-3 control-lable" for="title">Название отдела</label>
         <div class="col-md-7">
            <form:input type="text" path="title" id="title" class="form-control input-sm"/>
            <div class="has-error">
               <form:errors path="title" class="help-inline"/>
            </div>
         </div>
      </div>
   </div>
   <div class="row">
      <div class="form-group col-md-12">
         <label class="col-md-3 control-lable" for="branches">Офисы</label>
         <div class="col-md-7">
            <form:select path="branchOffice" items="${branches}" multiple="false" itemValue="id_branch" itemLabel="name_office" class="form-control input-sm" />
            <div class="has-error">
               <form:errors path="branchOffice" class="help-inline"/>
            </div>
         </div>
      </div>
   </div>
   <div class="row">
      <div class="form-actions floatRight">
         <c:choose>
            <c:when test="${edit}">
               <input type="submit" value="Обновить" class="btn btn-primary btn-sm"/> или <a href="<c:url value='/admin/branches' />">Отмена</a>
            </c:when>
            <c:otherwise>
               <input type="submit" value="Создать" class="btn btn-primary btn-sm"/> или <a href="<c:url value='/admin/branches' />">Отмена</a>
            </c:otherwise>
         </c:choose>
      </div>
   </div>
</form:form>
</div>