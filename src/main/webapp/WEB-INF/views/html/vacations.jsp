<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Management</title>
        <%@include file="imports.jsp" %>

</head>

<body>

    <div>
        <%@include file="navbar/navbar.jsp" %>
        <div class='row' id='rowbody'>
            <%@include file="accordion/accordeonMenu.jsp" %>
            <div class="col-md-8 col-sm-6 col-xs-12" id='content' style="display:inline-block;" id='content'>
                <%@include file="contents/vacations.jsp" %>
            </div>
        </div>
    </div>


<%@include file="modal/modal1.jsp" %>



</body>

</html>