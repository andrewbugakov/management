 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Modal -->
<div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Укажите причину паузы. </h4>
      </div>
      <div class="modal-body container ">
        <input id='casePause' type='text' class='widtt col-md-6' />

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
        <button type="button" class="btn btn-primary" onclick="AddPauseDesc($('#casePause').val());">Начать паузу</button>
      </div>
    </div>
  </div>
</div>