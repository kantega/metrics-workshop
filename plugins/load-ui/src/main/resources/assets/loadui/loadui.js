var loadUiApp = angular.module('loadUiApp',  []);

loadUiApp.controller('loadCtrl', ['$scope', '$http','$interval', function ($scope, $http, $interval) {

        $scope.myInterval = 1000;
        $scope.myUsers = 1;
        $scope.myUrl = '/#/blogs';
        $scope.running = false;

        var stop;
        $scope.start = function(myUsers, url) {
            if ( angular.isDefined(stop) ) return;
            $scope.running = true;
            var interval = Math.floor($scope.myInterval/myUsers);
            stop = $interval(function(){ $scope.startGetRequest(url); }, interval);
        };

        $scope.addUser = function() {
            $scope.myUsers++;
        };

        $scope.subUser = function() {
            if($scope.myUsers != 0){
                $scope.myUsers--;
            }
        };

        $scope.stop = function() {
            if (angular.isDefined(stop)) {
                $interval.cancel(stop);
                stop = undefined;
            }
            $scope.running = false;
        };

        $scope.startGetRequest = function(url){
                $http.get(url);
                console.log ('Http Get request to '+ url);
        };

        $scope.$on('$destroy', function() {
            $scope.stop();
        });

}]);



