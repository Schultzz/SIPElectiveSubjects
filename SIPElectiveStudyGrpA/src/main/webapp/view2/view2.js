'use strict';

angular.module('myApp.view2', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view2', {
                    templateUrl: 'view2/view2.html',
                    controller: 'View2Ctrl'
                });
            }])

        .filter('color', function () {
            return function (input) {

                input += " test"


                return input;
            };
        })

        .controller('View2Ctrl', ['$scope', 'subjectsFactory', function ($scope, subjectsFactory) {

                // For sorting the Category

                $scope.sortType = 'category'; // set the default sort type
                $scope.sortReverse = true;  // set the default sort order
                $scope.searchFish = '';     // set the default search/filter term

                //--------- Calculate

                $scope.calculate = function () {

                    var payload = [];

                    $scope.models.lists.A.forEach(function (entry) {
                        var obj = {topic: entry.label, pool: "A"};
                        payload.push(obj);
                    });

                    $scope.models.lists.B.forEach(function (entry) {
                        var obj = {topic: entry.label, pool: "B"};
                        payload.push(obj);
                    });

                    console.log("payload " + payload.length);

                    subjectsFactory.calculateCategory(payload).
                            success(function (data, status, headers, config) {
                                $scope.calculateData = data;
                                $scope.countCategoryScore();
                            }).
                            error(function (data, status, headers, config) {
                                console.log("err: ", data);
                            });


                };


                //--------- END. Calculate


                //CountCategoryScore
                $scope.countCategoryScore = function () {

                    $scope.aCounter = 0;
                    $scope.bCounter = 0;
                    $scope.cCounter = 0;
                    $scope.dCounter = 0;


                    $scope.calculateData.forEach(function (data) {


                        if (data.category === "A") {
                            $scope.aCounter++;
                        }
                        else if (data.category === "B") {
                            $scope.bCounter++;
                        }
                        else if (data.category === "C") {
                            $scope.cCounter++;
                        }
                        else {
                            $scope.dCounter++;
                        }

                    })


                }

                //--------- Drag and drop logic
                $scope.showError = false;

                $scope.models = {
                    selected: null,
                    lists: {"A": [], "Poll": [], "B": []}
                };

                // Calls the API, and gets a list of subjects.
                subjectsFactory.getSubjects().
                        success(function (data, status, headers, config) {
                            data.forEach(function (entry) {
                                $scope.models.lists.Poll.push({label: entry.topic});
                            })
                        }).
                        error(function (data, status, headers, config) {
                            $scope.subjetsError = data;
                            $scope.showError = true;
                        });


                // TODO: Can be removed, to test if the list were filled up.
                $scope.test = function () {
                    console.log("A: " + $scope.models.lists.A.length);
                    console.log("Poll: " + $scope.models.lists.Poll.length);
                    console.log("B: " + $scope.models.lists.B.length);
                };

                // Called when a subject is moved,
                // TODO: Calculate logic here!!
                $scope.moved = function () {
                    $scope.calculate();
                };

                //--------- END. Drag and drop logic


            }])
        .factory('subjectsFactory', ['$http', function ($http) {

                var dataFactory = {};


                dataFactory.getSubjects = function () {
                    return $http.get("http://localhost:8080/SIPElectiveStudyGrpA/api/subject");
                };

                dataFactory.calculateCategory = function (payload) {
                    return $http.post("http://localhost:8080/SIPElectiveStudyGrpA/api/subject/studentCalc", payload);
                };

                return dataFactory;

            }]);


