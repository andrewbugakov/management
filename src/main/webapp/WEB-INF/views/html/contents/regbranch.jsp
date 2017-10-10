<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class='container'>
<div class=""><h2>Добавление филиалов</h2><hr></div>
<form:form method="POST" modelAttribute="branch" class="form-horizontal" accept-charset="UTF-8">
   <form:input type="hidden" path="id_branch" id="id_branch"/>
   <div class="row">
      <div class="form-group col-md-12">
         <label class="col-md-3 control-lable" for="name_office">Название офиса</label>
         <div class="col-md-7">
            <form:input type="text" path="name_office" id="name_office" class="form-control input-sm"/>
            <div class="has-error">
               <form:errors path="name_office" class="help-inline"/>
            </div>
         </div>
      </div>
   </div>
   <div class="row">
      <div class="form-group col-md-12">
         <label class="col-md-3 control-lable" for="address">Адрес</label>
         <div class="col-md-7">
            <form:input type="text" path="address" id="address" class="form-control input-sm" />
            <div class="has-error">
               <form:errors path="address" class="help-inline"/>
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