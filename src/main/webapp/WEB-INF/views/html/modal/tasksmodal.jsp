 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Modal -->
<div class="modal fade" id="taskmodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Задачи выполненные за день</h4>
      </div>
      <div class="modal-body container ">
        <table id='tasktable' class='example'>
            <thead>
            <tr>
                <th>Название задачи</th>
                <th>Время начала выполнения</th>
                <th>Время окончания выполнения</th>
             </tr>
            </thead>
            <tbody>

            </tbody>
        </table>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
      </div>
    </div>
  </div>
</div>