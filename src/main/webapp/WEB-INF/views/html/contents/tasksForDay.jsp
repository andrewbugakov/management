<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
        <h1>Задачи</h1>
        <hr>
<p></p>
<a href="/newtask" class='btn btn-success'>Создать новую задачу</a>
<p></p>
<div class='row  container col-md-12'>
  <div class='bg-danger col-xs-5 buttontask' >
  <div class='row'><div class=col-md-12><a href="<c:url value='/outtasks' />">Просроченные задачи</a></div></div>
  <div class='row tasks'><div class='col-md-6'><h1>0</h1></div><div class='sost col-md-6 text-right'><div>0%</div><div>Вчера <label>0</label></div></div></div>
</div>
  <div class=' buttontask bg-info col-xs-5' >
  <div class='row'><div class=col-md-12><a href="<c:url value='/formetasks' />">Задачи к выполнению</a></div></div>
  <div class='row tasks'><div class='col-md-6'><h1>0</h1></div><div class='sost col-md-6 text-right'><div>0%</div><div>Вчера <label>0</label></div></div></div>
</div>
  <div class='buttontask bg-success col-xs-5'>
  <div class='row'><div class=col-md-12><a href="<c:url value='/endedtasks' />">Выполненные задачи</a></div></div>
  <div class='row tasks'><div class='col-md-6'><h1>0</h1></div><div class='sost col-md-6 text-right'><div>0%</div><div>Вчера <label>0</label></div></div></div>
</div>
<div class='buttontask bg-warning col-xs-5'>
  <div class='row'><div class=col-md-12><a href="<c:url value='/sovm' />">Совместные задачи</a></div></div>
  <div class='row tasks'><div class='col-md-6'><h1>0</h1></div><div class='sost col-md-6 text-right'><div>0%</div><div>Вчера <label>0</label></div></div></div>
</div>

</div>
      </div>