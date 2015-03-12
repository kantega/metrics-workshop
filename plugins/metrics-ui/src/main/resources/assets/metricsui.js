var metricsUiApp = angular.module('metricsUiApp', [
    "ngRoute",
    "highcharts-ng"
]);

metricsUiApp.controller('chartController', ['$scope', '$http', function ($scope, $http) {

    var staticChartData = [10, 15, 12, 8, 7, 10, 20, 19, 5, 22, 30, 15, 10];

    $scope.chart1Config = {
        options: {
            chart: {
                type: 'line'
            }
        },
        series: [{
            data: staticChartData
        }],
        title: {
            text: 'Chart 1'
        }

    };

}]);
