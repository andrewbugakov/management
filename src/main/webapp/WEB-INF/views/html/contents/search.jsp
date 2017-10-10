<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.2/css/bootstrap.css" rel="stylesheet" />
<link href="https://cdn.datatables.net/1.10.11/css/dataTables.bootstrap4.min.css" rel="stylesheet" />
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="/static/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.11/js/dataTables.bootstrap4.min.js"></script>
<script src="/static/js/index.js"></script>
<h1>Поиск сотрудника</h1>
<hr>
<form class="form" role="search" action="search">
                            <div class="input-group">
                                  <input id="search" name="search" type="text" class="form-control" placeholder="Поиск...">
                                  <span class="input-group-btn">
                                    <button class="btn btn-secondary" type="submit">Искать!</button>
                                  </span>
                                </div>
                        </form>
<c:if test="${not empty users}">
<table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
   <thead>
         <tr>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Отчество</th>
         </tr>
      </thead>
      <tbody>
         <c:forEach items="${users}" var="user">
            <tr onclick="go2UserInfo('${user.ssoId}');">
               <td>${user.firstName}</td>
               <td>${user.lastName}</td>
               <td>${user.patronymic}</td>
            </tr>
         </c:forEach>
      </tbody>
   </table>
</c:if>
<c:if test="${empty users}">
    <div class="alert alert-success lead">
                      	    	По вашему запросу ничего не найдено попробуйте поискать еще раз.
                      		</div>
</c:if>

