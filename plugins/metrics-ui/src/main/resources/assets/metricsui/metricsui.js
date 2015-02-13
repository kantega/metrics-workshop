var metricsUiApp = angular.module('metricsUiApp',  [
    "ngRoute",
    "highcharts-ng"
]);

metricsUiApp.controller('chartController', ['$scope', '$http', function ($scope, $http) {

    var chartData = [];

    $http.get('/metrics/')
        .success(function(jsonData){
            // Do some remodeling of the jsonData if necessary, else just give it to the chart:
            chartData = jsonData;
        });


    $scope.chartConfig = {
        options: {
            chart: {
                type: 'column'
            }
        },
        series: [{
            data: [10, 15, 12, 8, 7]
        }],
        title: {
            text: 'HelloWorld'
        },

        loading: false
    }


}]);
