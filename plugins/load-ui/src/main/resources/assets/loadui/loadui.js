var loadUiApp = angular.module('loadUiApp',  []);

loadUiApp.controller('loadCtrl', ['$scope', '$http','$interval', function ($scope, $http, $interval) {

        $scope.myInterval = 500;
        $scope.myIterations = 10;
        $scope.myUrl = '/helloworld';

        var stop;
        $scope.start = function(myInterval, myIterations, url) {
            if ( angular.isDefined(stop) ) return;
            $scope.runningIterations = myIterations;
            stop = $interval(function(){ $scope.startGetRequest(url); }, myInterval);
        };

        $scope.stop = function() {
            if (angular.isDefined(stop)) {
                $interval.cancel(stop);
                stop = undefined;
            }
        };

        $scope.startGetRequest = function(url){
            if($scope.runningIterations != 0){
                $scope.runningIterations = $scope.runningIterations - 1;
                $http.get(url);
                console.log ('Http Get request to '+ url);
            } else {
                $scope.stop();
            }
        };

        $scope.$on('$destroy', function() {
            $scope.stop();
        });

}]);



