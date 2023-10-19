<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Doughnut Chart Example</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>

<body>
hello
   <canvas id="bar-chart"></canvas>

 <script>
                                                        var chartData = [];
                                                        var chartData2 = [];
            <c:forEach items="${chartTrans}" var="entry">
                                                        var key = "${entry.key}"; // Get the key from LinkedHashMap entry
                                                        var value = "${entry.value}";
                                                        chartData.push(key);
                                                        chartData2.push(value);

            </c:forEach>

                                                        var ctx = document.getElementById("bar-chart").getContext("2d");
                                                        var myChart = new Chart(ctx, {
                                                            type: "bar",
                                                            data: {
                                                                labels: chartData, // Use the chartData array as labels
                                                                datasets: [{
                                                                        backgroundColor: [
                                                                            "rgba(0, 156, 255, .7)",
                                                                            "rgba(0, 156, 255, .6)",
                                                                            "rgba(0, 156, 255, .5)",
                                                                            "rgba(0, 156, 255, .4)",
                                                                            "rgba(0, 156, 255, .3)"
                                                                        ],
                                                                        data: chartData2 // Replace with your desired data
                                                                    }]
                                                            },
                                                            options: {
                                                                responsive: true
                                                            }
                                                        });
        </script>

</body>

</html>
