<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link href="https://cdn.datatables.net/1.10.11/css/dataTables.bootstrap4.min.css" rel="stylesheet" />
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="/static/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.11/js/dataTables.bootstrap4.min.js"></script>
<script src="/static/js/index.js"></script>
<script src="/static/js/index2.js"></script>

<h1>Встречи</h1>
<hr>
<a class="btn btn-primary custom-width" href="/newmeeting">
                       <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                       Создать новое событие
                     </a>
                     <hr>
<table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
                           <th>Дата начала</th>
                           <th>Дата окончания</th>
                           <th>Название митинга</th>
                           <th>Участники</th>
                           <th width="20" ></th>
                           <th width="20" ></th>

                       </tr>
        </thead>
        <tfoot>
            <tr>
                <th>Дата начала</th>
                <th>Дата окончания</th>
                <th>Название митинга</th>
                <th>Участники</th>
                <th width="20" ></th>
                <th width="20" ></th>

            </tr>
        </tfoot>
        <tbody>
            <c:forEach items="${meetings}" var="meeting">
                    <tr>
                       <td>${meeting.startTime}</td>
                       <td>${meeting.endTime}</td>
                       <td>${meeting.desc}</td>
                       <td><a href='showUch("${meeting.id_meeting}")'>Участники</a></td>
                       <td><a href="<c:url value='/edit-meeting-${meeting.id_meeting}' />" class="btn btn-success custom-width">
                              <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            </a>
                       </td>
                       <td><a class="btn btn-danger custom-width">
                              <span href="<c:url value='/delete-meeting-${meeting.id_meeting}' />" class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                           </a>
                       </td>
                   </tr>
            </c:forEach>

        </tbody>
    </table>