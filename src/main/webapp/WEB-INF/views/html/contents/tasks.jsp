<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h1>Статистика по задачам</h1>
        <hr>
          <script>
            $(function () {
                $('#cont2').highcharts({
                    data: {
                        table: 'datatable2'
                    },
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: 'Статистика по задачам'
                    },
                    yAxis: {
                        allowDecimals: false,
                        title: {
                            text: 'Время в минутах'
                        }
                    },
                    tooltip: {
                        formatter: function () {
                            return '<b>' + this.series.name + '</b><br/>' +
                                this.point.y + ' ' + this.point.name.toLowerCase();
                        }
                    }
                });
            });
          </script>
          <script src="https://code.highcharts.com/highcharts.js"></script>
          <script src="https://code.highcharts.com/modules/data.js"></script>
          <script src="https://code.highcharts.com/modules/exporting.js"></script>

          <div id="cont2" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

          <table id="datatable2" class='table' style="width: 100%;">
              <thead>
                  <tr>
                      <th>Название</th>
                      <th>Время выполнения</th>
                  </tr>
              </thead>
              <tbody>
                 <c:forEach items="${efs}" var="ef">
                  <tr>
                      <th>${ef.title}</th>
                      <td>${ef.duration}</td>
                  </tr>
                </c:forEach>
              </tbody>
          </table>
