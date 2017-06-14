angular.module('oaApp', ['restangular','ui.router','apply.controller','applyList.controller','formatDate','infinite-scroll'])
    .config(function ($stateProvider, $urlRouterProvider,RestangularProvider,$httpProvider) {
        RestangularProvider.setBaseUrl('/fastweb');
        $httpProvider.defaults.withCredentials = true;
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';

        $stateProvider
            .state('apply', {
                url: '/apply',
                controller: 'ApplyCtrl',
                templateUrl: 'app/apply/apply.html'
            })
            .state('applyList', {
                url: '/applyList',
                controller: 'ApplyListCtrl',
                templateUrl: 'app/applyList/applyList.html'
            })
            .state('20170603', {
                url: '/20170603',
                controller: function($scope, $rootScope) {
                    $rootScope.mainTitle = "英雄帖"
                },
                templateUrl: 'app/articles/20170603.html'
            });
        //$urlRouterProvider.otherwise('/20170603');
        $urlRouterProvider.otherwise('/apply');
        //$urlRouterProvider.otherwise('/applyList');
    });