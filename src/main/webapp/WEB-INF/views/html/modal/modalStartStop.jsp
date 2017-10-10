 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Modal -->
<div class="modal fade" id="modalstartstop" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Начать/завершить с другим временем</h4>
      </div>
      <div class="modal-body container ">
        <select class='col-md-6' id='type'>
           <option>--Выберите действие--</option>
            <option value='start'>Начать с другим временем</option>
            <option value='stop'>Закончить с другим временем </option>
        </select>
        <input id='time' type='text' class='widtt col-md-6' />
        <input id='case' type='text' class='widtt col-md-6' />
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
        <button type="button" class="btn btn-primary" onclick="startStopAnotherTime($('#type').val(),$('#time').val(),$('#case').val());">Да,я хочу завершить свой день</button>
      </div>
    </div>
  </div>
</div>