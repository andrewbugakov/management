$(document).ready(function() {
    $(".myselectornone").append("<option selected value='-1'> -- выберите значение из выпадающего списка -- </option>");
//    getAllTasks();
} );
function goWorkTime(){
 go2Page('/admin/worktime');
}
function getOrderFor(empid,type){
//alert(empid+'  '+type);
  go2Page('/sub-orders/'+empid+'/'+type);

}
function saveWorkTime(opozd,zaversh,pereriv,kolvorabdnei,edVremya){
    $.getJSON("/admin/saveworktime", {
                    opozd:opozd,
                    zaversh:zaversh,
                    pereriv:pereriv,
                    kolvorabdnei:kolvorabdnei,
                    edVremya:edVremya
                 },
                 function(data) {
                     switch (data.status) {
                            case "ok":
                              alert('Новое рабочее время установлено!')
                               break

                            default:
                                 alert("undefined response from server:"+data.status);
                               break
                         }
         //            alert(data.status);
                 });
}
function startStopAnotherTime(type,time,cas){
     $.getJSON("/startStopAnotherTime", {
                type:type,
                time:time,
                cas:cas
             },
             function(data) {
                 switch (data.status) {
                        case "ok":
                          $("#modalstartstop").modal('hide');
                           break

                        default:
                             alert("undefined response from server:"+data.status);
                           break
                     }
     //            alert(data.status);
             });
}
function setTasksModal(tasks){
    $("#tasktable tbody").empty();
    for(var i=0;i<tasks.length;i++){
        var task=tasks[i];
        var str=' <tr><th>'+task.titleTask+'</th> <th>'+task.actualStart+'</th><th>'+task.actualEnd+'</th></tr>';
            $("#tasktable tbody").append(str);
    }

}
function suborders(id){
  onSelect(id);
  go2Page('/sub-orders');
}
function finalizeday(){
    var descbyday=$('#descbyday').val();
    var fintable=$('#finaltable tr');
    var array=[];
    for(var i=0;i<fintable.length-1;i++)
    {
        array[i]=fintable[i+1].id;
    }
    $.ajax({
      url: "/daydesc",
      dataType: 'json',
      data: {
                        'myArray':array,
                        desc:descbyday
                        },
                                    traditional: true,
      success: function(data) {
                               switch (data.status) {
                                      case "ok":
                                          $("#myModal").modal('hide');

                                         break
                                      default:
                                           alert("undefined response from server:"+data.status);
                                         break
                                   }
                           }
    });

}
function getAllTasks(){
$.getJSON("/mytsks", {

        },
        function(data) {
            switch (data.status) {
                   case "ok":
                   clearTasksModal();
                    var tasks=data.tasks;
                    var task;
                    for(var i=0;i<tasks.length;i++)
                    {
                        task=tasks[i];
                        //title,starttime,endtime
                        addTask(task[0],task[7],task[3],false);
                    }
                    var fintasks=data.closedtasks;

                    for(var i=0;i<fintasks.length;i++)
                    {
                        task=fintasks[i];
                        //id,title,starttime,endtime
                        addFinTask(task[0],task[7],task[2],task[1]);
                    }
                      break
                   default:
                        alert("undefined response from server:"+data.status);
                      break
                }
        });
}
function clearTasksModal(){
    $("table#currtable tbody").empty();
    $("table#finaltable tbody").empty();
}
var myVar=setInterval(function () {myTimer()}, 1000);
function myTimer() {
    var date = new Date();
    document.getElementById("currentTime").innerHTML = date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();

}


function startStopDay(){
$.getJSON("/startstopday", {

        },
        function(data) {
            switch (data.status) {
                   case "started normally":
                      startedNormally();
                      break
                   case "started with late":
                        startedLate();
                      break
                    case "stopped early":
                        stoppedEarly();
                      break
                    case "stopped normally":
                        stoppedNormally();
                      break
                   default:
                        alert("undefined response from server:"+data.status);
                      break
                }
//            alert(data.status);
        });
}
function startedNormally(){
$('#startDay').empty();
$('#startDay').append('<a><span class="glyphicon glyphicon-stop" aria-hidden="true"></span> Закончить день</a>');

}

function startedLate(){
startedNormally();

}

function stoppedEarly(){
stoppedNormally();
$("#stopped").modal();

}
function lateStart(msg){
    $.getJSON("/late", {
            lates:msg
            },
            function(data) {
                switch (data.status) {
                       case "added":
                            $("#myModal21").modal('hide');
                          break
                       default:
                            alert("undefined response from server:"+data.status);
                          break
                    }
            });
}
function earlyStopped(msg){

    $.getJSON("/early", {
                early:msg
                },
                function(data) {
                    switch (data.status) {
                           case "added":
                                $("#myModal2").modal('hide');
                              break
                           default:
                                alert("undefined response from server:"+data.status);
                              break
                        }
                });

}

function stoppedNormally(){
$('#startDay').empty();
$('#startDay').append('<a><span class="glyphicon glyphicon-play" aria-hidden="true"></span> Начать день</a>');


}

function pauseDay(){

    $.getJSON("/pause", {
            },
            function(data) {
                switch (data.status) {
                   case "paused":
                      $('#myModal3').modal();
                      onPause();
                      break
                   case "resumed":
                        onResume();
                      break
                   default:
                        alert("undefined response from server:"+data.status);
                      break
                }
            });
}
function AddPauseDesc(desc){
    var str=$('#casePause').val();
    $.getJSON("/addDescPause", {
            message:desc
                },
                function(data) {
                    switch (data.status) {
                       case "addded":
                          $('#myModal3').modal('hide');
                          onPause();
                          break
                       default:
                            alert("undefined response from server:"+data.status);
                          break
                    }
                });
}
function onResume(){
    $("#pause").empty();
    $("#pause").append("<a onclick='pauseDay();'><span class='glyphicon glyphicon-forward' aria-hidden='true''></span> Пауза</a>");

}
function onPause(){
    $("#pause").empty();
    $("#pause").append("<a onclick='pauseDay();'><span class='glyphicon glyphicon-pause' aria-hidden='true''></span> Пауза</a>");
}
function onSelect(id){
  $("ul.linked-left-menu li").removeClass('active');
  $('#'+id).addClass('active');
}
function go2Page(url) {
document.location.href = url;
}

function gobranches(id){
  onSelect(id);
  go2Page('/admin/branches');
}

function godepts(id){
  onSelect(id);
  go2Page('/admin/depts');
}

function gousers(id){
  onSelect(id);
  go2Page('/admin/list');
}
function tasksForDay(id){
  onSelect(id);
  go2Page('/main');
}

function meetings(id){
  onSelect(id);
  go2Page('/meetings');
}
function go2UserInfo(id){
  //onSelect(id);
  go2Page('/user-'+id);
}
function calendar(id){
  onSelect(id);
  go2Page('/calendar');
}
function documentLoop(id){
  onSelect(id);
  go2Page('/documentsflow');
}
function searchEmp(id){
  onSelect(id);
  go2Page('/findemployee');
}
function phones(id){
  onSelect(id);
    go2Page('/phonebook');
}
function birthdays(id){
  onSelect(id);
    go2Page('/birthdates');
}
function hallOfFame(id){
  onSelect(id);
    go2Page('/fames');
}
function companyStructure(id){
  onSelect(id);
    go2Page('/companystructure');
}

function shedule(id){
  onSelect(id);
    go2Page('/timetable');
}

function calendarEvents(id){
  onSelect(id);
    go2Page('/documentsflow');
}

function vacations(id){
  onSelect(id);
    go2Page('/vacations');
}

function lates(id){
  onSelect(id);
    go2Page('/lates');
}
function tasks(id){
  onSelect(id);
    go2Page('/tasks');
}
function efficient(id){
  onSelect(id);
    go2Page('/efficient');
}
function leaderships(id){
  onSelect(id);
    go2Page('/managements');
}
function letterForAdmin(id){
  onSelect(id);
    go2Page('/mail-to-admin');
}
function addTask(id,message,deadline,started){
var buttoninfo;
if(started==true){
buttoninfo='<span class="glyphicon glyphicon-stop" aria-hidden="true"></span>';
}else{
buttoninfo='<span class="glyphicon glyphicon-play" aria-hidden="true"></span>';
}

  var str='<tr id="'+id+'"><td><a href="#" id="'+id+'go" onclick="goTask(this.id,$( this ).parents(\'tr\'))">'+buttoninfo+'</a></td><td style="vertical-align: middle;">'+message+'</td><td>'+deadline+'</td></tr>';
  $("table#currtable tbody").append(str);

}
function addFinTask(id,title,starttime,endtime){
  var str='<tr id="'+id+'"><td style="vertical-align: middle;"><input type="checkbox" value=""></td><td style="vertical-align: middle;">'+title+'</td><td>'+starttime+'</td><td>'+endtime+'</td></tr>';
  $("table#finaltable tbody").append(str);

}
function goTask(num,idhead){
  var idtask=idhead[0].id;
  $.getJSON("/startstoptask",
        {
            idtask:idtask
        },
          function(data) {
              switch (data.status) {
                     case "stopped":
                        $('#'+num).empty();
                        $('#'+num).addClass('disabled');
                        $('#'+num).append("<span class='glyphicon glyphicon-plus' aria-hidden='true'></span>");
                        $('#'+num).removeClass('go');
                        getAllTasks();
                       break
                    case "started":
                        $('#'+num).empty();
                        $('#'+num).append("<span class='glyphicon glyphicon-stop' aria-hidden='true'></span>");
                        $('#'+num).removeClass('go');
                       break
                     default:
                          alert("undefined response from server:"+data.status);
                        break
                  }
  //            alert(data.status);
          });

//  if($('#'+num).hasClass("go")){
//    $('#'+num).empty();
//  $('#'+num).append("<span class='glyphicon glyphicon-pause' aria-hidden='true'></span>");
//    $('#'+num).removeClass('go');
//  }else{
//    $('#'+num).addClass('go');
//    $('#'+num).empty();
//  $('#'+num).append("<span class='glyphicon glyphicon-play' aria-hidden='true'></span>");
//  }

}
function ajaxAddTask(message,ded){
var dada={title:message,deadline:ded};
$.getJSON("/addtask",dada ,
        function(data) {
            switch (data.status) {
                   case "added":
                        getAllTasks();
                     break
                   default:
                        alert("undefined response from server:"+data.status);
                      break
                }
//            alert(data.status);
        });
}
function clickedCheckbox(id1){
     $("#finaltable").append(id1);
}