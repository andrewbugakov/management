<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">Телефонный справочник</span></div>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Имя</th>
				        <th>Фамилия</th>
				        <th>Email</th>
				        <th>Личный телефон </th>
				        <th>Рабочий телефон </th>
				        <th>Внутренний телефон </th>

					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${users}" var="user">
					<tr onclick="go2UserInfo('${user.ssoId}');">
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td>${user.email}</td>
						<td>${user.phone}</td>
                        <td>${user.work_phone}</td>
                        <td>${user.interoffice_phone}</td>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
   	</div>