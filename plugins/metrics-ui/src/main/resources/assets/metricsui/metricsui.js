var metricsUiApp = angular.module('metricsUiApp', [
    "ngRoute",
    "highcharts-ng"
]);

metricsUiApp.controller('chartController', ['$scope', '$http', function ($scope, $http) {

    $scope.staticChartData = [10, 15, 12, 8, 7, 10, 20, 19, 5, 22, 30, 15, 10];

    $scope.chart1Config = {
        options: {
            chart: {
                type: 'line'
            }
        },
        series: [{
            data: $scope.staticChartData
        }],
        title: {
            text: 'Chart 1'
        },

        loading: false
    };

    $http.get('/metrics/')
        .success(function (jsonData) {
            // Do some remodeling of the jsonData if necessary, else just give it to the chart:
            $scope.heapMax = jsonData.gauges["heap.max"].value;
            $scope.heapMax2 = Math.floor($scope.heapMax/2);
            $scope.heapMax3 = Math.floor($scope.heapMax/1.5);
            $scope.heapUsed = [jsonData.gauges["heap.used"].value];

            $scope.chart2Config = {
                options: {
                    chart: {
                        type: 'gauge'
                    },

                    pane: {
                        startAngle: -150,
                        endAngle: 150
                    }

                },
                series: [{
                    name: 'Heap Usage',
                    data: $scope.heapUsed
                }],
                yAxis: [{
                    min: 0,
                    max: $scope.heapMax,

                    plotBands: [{
                        from: 0,
                        to: $scope.heapMax2,
                        color: '#55BF3B' // green
                    }, {
                        from: $scope.heapMax2,
                        to: $scope.heapMax3,
                        color: '#DDDF0D' // yellow
                    }, {
                        from: $scope.heapMax3,
                        to: $scope.heapMax,
                        color: '#DF5353' // red
                    }]
                }],
                title: {
                    text: 'Chart 2'
                }

            }

        });


}]);
