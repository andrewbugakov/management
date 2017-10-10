<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class='container'>
		<div class=""><h2>Регистрация пользователя</h2><hr></div>
	 	<form:form method="POST" modelAttribute="user" class="form-horizontal" accept-charset="UTF-8">
			<form:input type="hidden" path="id" id="id"/>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="firstName">Имя</label>
					<div class="col-md-7">
						<form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
						<div class="has-error">
							<form:errors path="firstName" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
            <div class="row">
                                    				<div class="form-group col-md-12">
                                    					<label class="col-md-3 control-lable" for="patronymic">Отчество</label>
                                    					<div class="col-md-7">
                                    						<form:input type="text" path="patronymic" id="patronymic" class="form-control input-sm" />
                                    						<div class="has-error">
                                    							<form:errors path="patronymic" class="help-inline"/>
                                    						</div>
                                    					</div>
                                    				</div>
                                    			</div>
            			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="lastName">Фамилия</label>
					<div class="col-md-7">
						<form:input type="text" path="lastName" id="lastName" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="lastName" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="ssoId">Логин</label>
					<div class="col-md-7">
						<c:choose>
							<c:when test="${edit}">
								<form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" disabled="true"/>
							</c:when>
							<c:otherwise>
								<form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="ssoId" class="help-inline"/>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="password">Пароль</label>
					<div class="col-md-7">
						<form:input type="password" path="password" id="password" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="password" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="email">Email</label>
					<div class="col-md-7">
						<form:input type="text" path="email" id="email" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="email" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	        <div class="row">
            				<div class="form-group col-md-12">
            					<label class="col-md-3 control-lable" for="birthday">День рождения</label>
            					<div class="col-md-7">
            						<form:input type="text" path="birthday" id="birthday" class="form-control input-sm" />
            						<div class="has-error">
            							<form:errors path="birthday" class="help-inline"/>
            						</div>
            					</div>
            				</div>
            			</div>
            <div class="row">
            				<div class="form-group col-md-12">
            					<label class="col-md-3 control-lable" for="phone">Телефон</label>
            					<div class="col-md-7">
            						<form:input type="text" path="phone" id="phone" class="form-control input-sm" />
            						<div class="has-error">
            							<form:errors path="phone" class="help-inline"/>
            						</div>
            					</div>
            				</div>
            			</div>
             <div class="row">
              				<div class="form-group col-md-12">
              					<label class="col-md-3 control-lable" for="phone">Рабочий телефон</label>
              					<div class="col-md-7">
              						<form:input type="text" path="work_phone" id="work_phone" class="form-control input-sm" />
              						<div class="has-error">
              							<form:errors path="work_phone" class="help-inline"/>
              						</div>
              					</div>
              				</div>
              			</div>
            <div class="row">
              				<div class="form-group col-md-12">
              					<label class="col-md-3 control-lable" for="phone">Внутренний телефон</label>
              					<div class="col-md-7">
              						<form:input type="text" path="interoffice_phone" id="interoffice_phone" class="form-control input-sm" />
              						<div class="has-error">
              							<form:errors path="interoffice_phone" class="help-inline"/>
              						</div>
              					</div>
              				</div>
              			</div>
            <div class="row">
              				<div class="form-group col-md-12">
              					<label class="col-md-3 control-lable" for="position">Должность</label>
              					<div class="col-md-7">
              						<form:input type="text" path="position" id="position" class="form-control input-sm" />
              						<div class="has-error">
              							<form:errors path="position" class="help-inline"/>
              						</div>
              					</div>
              				</div>
              			</div>
            <div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="departments">Отделы</label>
					<div class="col-md-7">
						<form:select path="department" items="${depts}" multiple="false" itemValue="id_department" itemLabel="title" class="form-control input-sm myselectornone" />
						<div class="has-error">
							<form:errors path="department" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
            				<div class="form-group col-md-12">
            					<label class="col-md-3 control-lable" for="departments">Начальник отдела</label>
            					<div class="col-md-7">
            						<form:select path="dept_where_head" items="${depts}" multiple="false" itemValue="id_department" itemLabel="title" class="form-control input-sm myselectornone" />
            						<div class="has-error">
            							<form:errors path="dept_where_head" class="help-inline"/>
            						</div>
            					</div>
            				</div>
            			</div>
<div class="row">
      <div class="form-group col-md-12">
         <label class="col-md-3 control-lable" for="branches">Начальник офиса</label>
         <div class="col-md-7">
            <form:select  path="branch_where_head" items="${branches}" multiple="false" itemValue="id_branch" itemLabel="name_office" class="form-control input-sm myselectornone" />
            <div class="has-error">
               <form:errors path="branch_where_head" class="help-inline"/>
            </div>
         </div>
      </div>
   </div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="userProfiles">Роли</label>
					<div class="col-md-7">
						<form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="userProfiles" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Обновить" class="btn btn-primary btn-sm"/> или <a href="<c:url value='/admin/list' />">Отмена</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Создать" class="btn btn-primary btn-sm"/> или <a href="<c:url value='/admin/list' />">Отмена</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
		</div>