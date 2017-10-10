<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class='eee'>
  <div class='container '>
    <h1>Настройка рабочего дня</h1>
    <hr>
    <div class='row'>
      <div class='col-md-4'>
        <div class='row'>
          <label class='col-md-5 col-xs-3'>Опаздание (мин.)</label>
          <input class='col-md-5 col-xs-3' id='opozd' value='${workingtime.opazdanie}' />
        </div>
        <div class='row'>
          <label class='col-md-5  col-xs-3'>Завершение (мин.)</label>
          <input class='col-md-5  col-xs-3' id='zaversh' value='${workingtime.zavershenie}'/>
        </div>
        <div class='row'>
          <label class='col-md-5  col-xs-3'>Перерыв (мин.)</label>
          <input class='col-md-5  col-xs-3' id='pereriv' value='${workingtime.pereriv}' />
        </div>
        <div class='row'>
          <label class='col-md-5  col-xs-3'>Кол-во раб. дней </label>
          <input class='col-md-5  col-xs-3' id='kolvorabdnei' value='${workingtime.colvo_dnei}'/>
        </div>
      </div>
      <div class='col-md-4'>
        <table class='table'>
          <thead>
            <tr>
              <th width='20px'>#</th>
              <th>Рабочие дни</th>
              <th>Рабочие часы</th>
              <th >Всего</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td><input type='checkbox'/></td>
              <td>Единое время</td>
              <td><input id='edVremya' value='8:00-17:00'/></td>
              <td><input class='vsego'  /></td>
            </tr>
            <tr>
              <td><input type='checkbox'/></td>
              <td>Понедельник</td>
              <td><input value='${workingtime.startPnd}-${workingtime.endPnd}'/></td>
              <td><input class='vsego'  value='9' /></td>
            </tr>
            <tr>
              <td><input type='checkbox'/></td>
              <td>Вторник</td>
              <td><input  value='${workingtime.startVt}-${workingtime.endVt}'/></td>
              <td><input class='vsego'   value='9'/></td>
            </tr>
            <tr>
              <td><input type='checkbox'/></td>
              <td>Среда</td>
              <td><input  value='${workingtime.startSr}-${workingtime.endSr}' /></td>
              <td><input class='vsego'  value='9'/></td>
            </tr>
            <tr>
              <td><input type='checkbox'/></td>
              <td>Четверг</td>
              <td><input  value='${workingtime.startCht}-${workingtime.endCht}'/></td>
              <td><input class='vsego'   value='9'/></td>
            </tr>
            <tr>
              <td><input type='checkbox'/></td>
              <td>Пятница</td>
              <td><input  value='${workingtime.startPt}-${workingtime.endPt}'/></td>
              <td><input class='vsego'   value='9'/></td>
            </tr>
            <tr>
              <td><input type='checkbox'/></td>
              <td>Суббота</td>
              <td><input  value='${workingtime.startSub}-${workingtime.endSub}'/></td>
              <td><input class='vsego'  />0</td>
            </tr>
            <tr>
              <td><input type='checkbox'/></td>
              <td>Воскресенье</td>
              <td><input  value='${workingtime.startVs}-${workingtime.endVs}'/></td>
              <td><input class='vsego'  />0</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <button class='btn btn-primary' type='button' onclick='saveWorkTime($("#opozd").val(),$("#zaversh").val(),$("#pereriv").val(),$("#kolvorabdnei").val(),$("#edVremya").val());'>Сохранить изменения</button>
  </div>
</div>