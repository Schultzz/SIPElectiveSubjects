'use strict';

angular.module('myApp.view2', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view2', {
            templateUrl: 'view2/view2.html',
            controller: 'View2Ctrl'
        });
    }])

    .controller('View2Ctrl', ['$scope', 'subjectsFactory', function ($scope, subjectsFactory) {


        //--------- Drag and drop logic

        $scope.models = {
            selected: null,
            lists: {"A": [], "Poll": [], "B": []}
        };

        // TODO: move this to the HTTP request, instead of "dummy" data.
        for (var i = 1; i <= 5; ++i) {
            //$scope.models.lists.A.push({label: "Item A" + i});
            //$scope.models.lists.B.push({label: "Item B" + i});
            $scope.models.lists.Poll.push({label: "Item B" + i, name: "test " + i});
        }

        subjectsFactory.getData().
            success(function(data, status, headers, config) {
                // this callback will be called asynchronously
                // when the response is available
            }).
            error(function(data, status, headers, config) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.

            });


        // Can be removed, to test if the list were filled up.
        $scope.test = function () {
            console.log("A: " + $scope.models.lists.A.length);
            console.log("Poll: " + $scope.models.lists.Poll.length);
            console.log("B: " + $scope.models.lists.B.length);
        };

        // Called when a subject is moved,
        // TODO: Calculate logic here!!
        $scope.moved = function () {
            console.log("MOVED!");
        };

        //--------- END. Drag and drop logic


        //--------- HTTP request to get subjects

        subjectsFactory.getData().success(function (data) {
            $scope.subjets = data;
        }).error(function (data) {
            $scope.subjetsError = data;
        });

        //--------- END. HTTP request to get subjects

    }])
    .factory('subjectsFactory', ['$http', function ($http) {

        var dataFactory = {};


        dataFactory.getData = function () {
            return $http.get("/api/getAllSubjects");
        };
        return dataFactory;

    }]);


