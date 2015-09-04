'use strict';

angular.module('myApp.view2', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view2', {
                    templateUrl: 'view2/view2.html',
                    controller: 'View2Ctrl'
                });
            }])

        .controller('View2Ctrl', ['$scope', 'subjectsFactory', function ($scope, subjectsFactory) {

                //Drag and drop elemets
                $scope.models = {
                    selected: null,
                    lists: {"A": [], "Poll": [],"B": []}
                };

                // Generate initial model
                for (var i = 1; i <= 3; ++i) {
                    //$scope.models.lists.A.push({label: "Item A" + i});
                    //$scope.models.lists.B.push({label: "Item B" + i});
                    $scope.models.lists.Poll.push({label: "Item B" + i});
                }
                
                $scope.test = function(){
                    console.log("A: "+$scope.models.lists.A.length);
                    console.log("Poll: "+$scope.models.lists.Poll.length);
                    console.log("B: "+$scope.models.lists.B.length);
                } 

//                // Model to JSON for demo purpose
//                $scope.$watch('models', function (model) {
//                    $scope.modelAsJson = angular.toJson(model, true);
//                }, true);


                // HTTP request to get subjects
                
                subjectsFactory.getData().success(function (data) {
                    $scope.subjets = data;
                }).error(function (data) {
                    $scope.subjetsError = "Fejl" + data;
                });


            }])
        .factory('subjectsFactory', ['$http', function ($http)
            {
                var url = "/api/getAllSubjects";
                var dataFactory = {};
                dataFactory.getData = function ()
                {
                    return $http.get(url);
                }
                return dataFactory;

            }]);


;