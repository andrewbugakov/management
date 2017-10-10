<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class='eee'>
  <div class='container'>
    <h1>Отчеты</h1>
    <hr>
    <button type='button' class='btn btn-primary'>Отчеты по опозданиям подчиненных</button>
    <button type='button' class='btn btn-primary'>Отчеты по отделам</button>
    <button type='button' class='btn btn-primary'>Отчеты по филиалам</button>
    <p></p>
    <h3>Отчет по подчиненному</h3>
    <p></p>
    <div class='row'><label class='col-md-2'>Сотрудник </label><select class='col-md-4' id='empl'>
    <option>--Выберите сотрудника из списка--</option>
    <c:forEach items="${subordinates}" var="subordinate">
             <option value=${subordinate.id}>${subordinate.fullname}</option>
          </c:forEach>
</select>
    </div>
    <div class='row'>
      <label class='col-md-2'>Тип отчета </label><select class='col-md-4' id='typeot'>
   <option>--Выберите тип отчета из списка--</option>
    <option value='all'>все отчеты</option>
    <option value='opozd'>по опозданиям</option>
    <option value='early'>по ранним уходам</option>
    <option value='efficient'> по эффективности</option>
</select>
    </div>
    <button type='submit' class=' btn btn-primary' onclick="getOrderFor($('#empl').val(),$('#typeot').val())">Получить отчет</button>
    <!--<table class='table'>
    <thead>
      <tr>
        <th>#</th>

        <th>Название отдела</th>
        <th>ФИО сотрудника</th>
                <th>Название фи</th>

        <th>Дата опаздания</th>
        <th>Время опаздания</th>
        <th>Причина опоздания</th>
      </tr>
    </thead>
    <tbody>

    </tbody>
  </table>-->
  </div>
</div>