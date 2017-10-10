<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='container'>
		<div class=" "><h2>Добавление задачи</h2><hr></div>
	 	<form:form method="POST" modelAttribute="task" class="form-horizontal" accept-charset="UTF-8">
			<form:input type="hidden" path="id_task" id="id_task"/>


            <div class="row">
 				<div class="form-group col-md-12">
 					<label class="col-md-3 control-lable" for="deadline">Дедлайн</label>
 					<div class="col-md-7">
 						<form:input type="text" placeholder='dd-MM-yyyy HH:mm' path="deadline" id="deadline" class="form-control input-sm"/>
 						<div class="has-error">
 							<form:errors path="deadline" class="help-inline"/>
 						</div>
 					</div>
 				</div>
 			</div>
            <div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="titleTask">Название задачи</label>
					<div class="col-md-7">
						<form:input type="text" path="titleTask" id="titleTask" class="form-control input-sm"/>
						<div class="has-error">
							<form:errors path="titleTask" class="help-inline"/>
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
					<label class="col-md-3 control-lable" for="userWhoDo">Исполнитель</label>
					<div class="col-md-7">
						<form:select path="userWhoDo" items="${users}" multiple="false" itemValue="id" itemLabel="fullname" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="userWhoDo" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
           <!-- <div class="row">
            				<div class="form-group col-md-12">
            					<label class="col-md-3 control-lable" for="userWhoDo">Участник</label>
            					<div class="col-md-7">
            						<form:select path="collaborators" items="${users}" multiple="true" itemValue="id" itemLabel="fullname" class="form-control input-sm" />
            						<div class="has-error">
            							<form:errors path="collaborators" class="help-inline"/>
            						</div>
            					</div>
            				</div>
            			</div>-->
			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Обновить" class="btn btn-primary btn-sm"/> или <a href="<c:url value='/tasks' />">Отмена</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Создать" class="btn btn-primary btn-sm"/> или <a href="<c:url value='/tasks' />">Отмена</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
		</div>