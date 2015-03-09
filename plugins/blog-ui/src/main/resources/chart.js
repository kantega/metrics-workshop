(function () {
    var memoryApp = angular.module("memory", ["highcharts-ng"]);

    memoryApp.controller("MemoryController", ['$scope', '$http', '$interval', function ($scope, $http, $interval) {

        var series = [{
            name: 'Memory use',
            data: []
        }];

        var memoryGraph = {
            options: {
                title: {
                    text: 'Memory use'
                },
                xAxis: {
                    type: 'datetime'
                },
                yAxis: {
                    title: {
                        text: 'Use'
                    }
                },
                legend: {
                    enabled: false
                },
                exporting: {
                    enabled: false
                }
            },
            series: series
        };

        $scope.memoryGraph = memoryGraph;

        $interval(function () {

            // TODO: Use Angular's $http service to fetch the /metrics/ data
            // TODO Find the gauge named "heap.used"
            // TODO Push an {x:,y:} data point to series.data (X should be the current time, Y should be the memory usage
        }, 1000)

    }]);
})();