<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class='container'>
<div class=""><h2>Создание митинга</h2><hr></div>
<form:form method="POST" modelAttribute="meeting" class="form-horizontal" accept-charset="UTF-8">
   <form:input type="hidden" path="id_meeting" id="id_meeting"/>
   <div class="row">
         <div class="form-group col-md-12">
            <label class="col-md-3 control-lable" for="startTime">Время начала митинга</label>
            <div class="col-md-7">
               <form:input type="text" path="startTime" placeholder='dd-MM-yyyy hh:mm' id="startTime" class="form-control input-sm"/>
               <div class="has-error">
                  <form:errors path="startTime" class="help-inline"/>
               </div>
            </div>
         </div>
   </div>
   <div class="row">
            <div class="form-group col-md-12">
               <label class="col-md-3 control-lable" for="endTime">Время окончания митинга</label>
               <div class="col-md-7">
                  <form:input type="text" path="endTime"  placeholder='dd-MM-yyyy hh:mm'  id="endTime" class="form-control input-sm"/>
                  <div class="has-error">
                     <form:errors path="endTime" class="help-inline"/>
                  </div>
               </div>
            </div>
   </div>
   <div class="row">
            <div class="form-group col-md-12">
               <label class="col-md-3 control-lable" for="desc">Примечание</label>
               <div class="col-md-7">
                  <form:input type="text" path="desc" id="desc" class="form-control input-sm"/>
                  <div class="has-error">
                     <form:errors path="desc" class="help-inline"/>
                  </div>
               </div>
            </div>
   </div>

   <div class="row">
      <div class="form-group col-md-12">
         <label class="col-md-3 control-lable" for="employees">Сотрудники</label>
         <div class="col-md-7">
            <form:select path="employees" items="${users}" multiple="true" itemValue="id" itemLabel="fullname" class="form-control input-sm" />
            <div class="has-error">
               <form:errors path="employees" class="help-inline"/>
            </div>
         </div>
      </div>
   </div>
   <div class="row">
      <div class="form-actions floatRight">
         <c:choose>
            <c:when test="${edit}">
               <input type="submit" value="Обновить" class="btn btn-primary btn-sm"/> или <a href="<c:url value='/meetings' />">Отмена</a>
            </c:when>
            <c:otherwise>
               <input type="submit" value="Создать" class="btn btn-primary btn-sm"/> или <a href="<c:url value='/meetings' />">Отмена</a>
            </c:otherwise>
         </c:choose>
      </div>
   </div>
</form:form>
</div>