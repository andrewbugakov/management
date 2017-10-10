<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <h1>Опоздания</h1>
        <hr>
          <script>
            $(function () {
                $('#cont').highcharts({
                    data: {
                        table: 'datatable'
                    },
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: 'Опоздания'
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

          <div id="cont" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

          <table id="datatable" class='table' style="width: 100%;">
              <thead>
                  <tr>
                      <th>День опоздания</th>
                      <th>Время опоздания</th>
                  </tr>
              </thead>
              <tbody>
                 <c:forEach items="${lates}" var="late">
                  <tr>
                      <th>${late.date}</th>
                      <td>${late.late}</td>
                  </tr>
                </c:forEach>
              </tbody>
          </table>
