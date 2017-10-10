 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <div class="col-md-6  col-sm-6 col-xs-12" style='padding-top: 20px;'>
                        <h4 class="modal-title" id="myModalLabel">Длительность рабочего дня</h4>
                    </div>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <div><a href="#"><span class="glyphicon glyphicon-time" aria-hidden="true"></span>  <label><small>7.34.56</small></label></a></div>
                        <div><a href="#" data-toggle="modal" data-target="#modalstartstop"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>  <small><label>Начать/Завершить с другим временем</label></small></a></div>
                        <div onclick="getAllTasks();"><a href=''><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>  <small><label>Обновить список задач</label></small></a></div>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="panel-body">
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#plans" data-toggle="tab" aria-expanded="true">Планы на день</a>
                            </li>
                            <li class=""><a href="#mades" data-toggle="tab" aria-expanded="false">Сделано за день</a>
                            </li>
                        </ul>
                        <!-- Tab panes -->
                        <div class="tab-content">
                            <div class="tab-pane fade active in" id="plans">
                                <div class="table-responsive table-bordered" style="height:200px;overflow:auto;">
                                    <table class="table" id="currtable">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Название задачи</th>
                                                <th>Дедлайн</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                                <div>

                                    <form role="form" >
                                        <!--                   <div class='col-xs-0 col-sm-0'><hr></div> -->
                                        <div class=''>
                                            <div class="form-group">
                                                <input class="form-control" id="taskInput" type="text" placeholder="Введите текст задачи">
                                                <input class="form-control" id="deadline" type="text" placeholder="Дедлайн">
                                            </div>
                                        </div>
                                        <div class=' '>
                                            <button onclick="ajaxAddTask($('#taskInput').val(),$('#deadline').val());" type="button" class="btn btn-primary col-md-4 col-sm-4 col-xs-12">Добавить</button>
                                        </div>
                                        <div class="checkbox col-md-8 col-sm-8 col-xs-12">
                                            <label style="
    margin-top: -2px;"><input type="checkbox">Добавить в список постоянных задач
                      </label>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="mades">
                                <div class="table-responsive table-bordered" style="height:200px;overflow:auto;">
                                    <table class="table" id="finaltable">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Название задачи</th>
                                                <th>Дата начала</th>
                                                <th>Дата окончания</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>

                                </div>

                                <div>

                                    <form role="form">
                                        <div class="form-group ">
                                            <textarea id='descbyday' class="span6 col-md-12 col-sm-12 col-xs-12" rows="3" placeholder="Напишите краткий коментарий о проделанной работе" required></textarea>

                                        </div>
                                        <div class="col-md-3 col-sm-3 col-xs-0"></div>
                                        <div><button type="button" onclick="finalizeday();" style="margin-top: 10px;"
                                         class="btn btn-primary col-md-6 col-sm-6 col-xs-12">Сохранить отчет</button></div>




                                    </form>
                                </div>


                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
</div>
 <%@include file="modal2.jsp" %>
 <%@include file="modal3.jsp" %>
