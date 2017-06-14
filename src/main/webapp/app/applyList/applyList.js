angular.module('applyList.controller', [])
    .controller('ApplyListCtrl', ['$scope','$rootScope', 'Restangular','$timeout', '$filter','$state',
        function ($scope, $rootScope, Restangular, $timeout, $filter,$state) {
            // load full time accc
            $rootScope.mainTitle = "Apply List";

            $scope.loadType = false;
            /*$scope.loadFullTimeAccounts = function() {
                Restangular.all('leaveManage/getFullTimeAccounts').post().then(function (data) {
                    /!*console.log(JSON.stringify(data));*!/
                    $scope.accounts = data;
                    $scope.accountBackups = data;
                });
            };
            $scope.loadFullTimeAccounts();*/
            $scope.accounts = [
                {accountName:"Coral"},{accountName:"Leo"},{accountName:"Vance"},{accountName:"May"},{accountName:"Jason"},
                {accountName:"Mary"},{accountName:"Jack"},{accountName:"Rose"},{accountName:"Ralf"},{accountName:"Henry"},
                {accountName:"Susan"},{accountName:"Robot"},{accountName:"David"},{accountName:"Sofia"},{accountName:"Fox"},
                {accountName:"Nina"},{accountName:"Robbin"},{accountName:"Apple"},{accountName:"Luis"},{accountName:"Eddie"}
            ];
            $scope.accountBackups  = $scope.accounts;

            $scope.accountFilter = function(data) {
                $scope.accounts = _.filter($scope.accountBackups, function(o) {
                    return o.accountName.startsWith(data);
                });
            };

            $scope.myPagingFunction = function() {
              console.log('myPagingFunction');
              $scope.loadType = true;
            };

            $scope.editAccount = function(account) {
                $state.go("manage.accountEdit", {account:account});
            };

            var $searchBar = $('#searchBar'),
                $searchText = $('#searchText'),
                $searchInput = $('#searchInput'),
                $searchClear = $('#searchClear'),
                $searchCancel = $('#searchCancel');

            function cancelSearch(){
                $searchBar.removeClass('weui-search-bar_focusing');
                $searchText.show();
            }

            $searchText.on('click', function(){
                $searchBar.addClass('weui-search-bar_focusing');
                $searchInput.focus();
            });
            $searchInput.on('blur', function () {
                if(!this.value.length) cancelSearch();
            });
            $searchClear.on('click', function() {
                $searchInput.focus();
            });
            $searchCancel.on('click', function() {
                cancelSearch();
                $searchInput.blur();
            });
        }
    ]);