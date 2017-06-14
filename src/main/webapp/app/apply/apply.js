angular.module('apply.controller', [])
    .controller('ApplyCtrl', ['$scope','$http','Restangular','$state','$timeout', function ($scope, $http,Restangular,$state,$timeout) {
        $scope.result = {'message':'Initialize'};

        Restangular.one('part/apply?number=100').get().then(function (data) {
            console.log(JSON.stringify(data));
            $scope.result.message = data;
        });

        $scope.applyForm = {accountName:"Coral", startDate: _.now(), endDate:_.now(), applyNumber: 2, leaveType: 1};

    }]);
