'use strict';
angular.module('myApp.view1', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view1', {
            templateUrl: 'view1/view1.html',
            controller: 'View1Ctrl'
        });
    }])

    .controller('View1Ctrl', ['$scope', 'studentFactory','$timeout', function ($scope, studentFactory, $timeout) {

        $scope.models = {
            selected: null,
            subjects: [
                {name: 'First priority', list: []},
                {name: 'Second priority', list: []},
                {name: "Pool A", list: [], type: 'A'},
                {name: "Pool B", list: [], type: 'B'}]
        };


        var arrayPrioOne = $scope.models.subjects[0];
        var arrayPrioTwo = $scope.models.subjects[1];
        var arrayPoolA = $scope.models.subjects[2];
        var arrayPoolB = $scope.models.subjects[3];

        // Logic for disabling of first and second prio list.

        $scope.prioOneBoolean = false;
        $scope.prioOneDisabler = function (arg) {
            $scope.prioOneBoolean = false;
            if (arrayPrioOne.list.length >= 2) {
                $scope.prioOneBoolean = true;
            }
            if (arrayPrioOne.list.length > 0) {
                if (arrayPrioOne.list[0].pool == arg) {
                    $scope.prioOneBoolean = true;
                }
            }
        };

        $scope.prioTwoBoolean = false;
        $scope.prioTwoDisabler = function (arg) {
            $scope.prioTwoBoolean = false;
            if (arrayPrioTwo.list.length >= 2) {
                $scope.prioTwoBoolean = true;
            }
            if (arrayPrioTwo.list.length > 0) {
                if (arrayPrioTwo.list[0].pool == arg) {
                    $scope.prioTwoBoolean = true;
                }
            }
        };

        // END. Logic for disabling of first and second prio list.

        studentFactory.getElected().
            success(function (data, status, headers, config) {
                data.forEach(function (entry) {
                    //console.log(entry);
                    //entry.prio = 0;
                    entry.pool === 'A' ? arrayPoolA.list.push(entry) : arrayPoolB.list.push(entry);
                });
            }).
            error(function (data, status, headers, config) {
                console.log('error', data);
            });


        $scope.dropCallback = function (event, index, item, external, type) {


            //item.prio = 1;
            //console.log(item);


        };

        $scope.validationVote = function () {
            if ($scope.name != undefined) {
                if ($scope.name.length > 0 && arrayPrioOne.list.length == 2 && arrayPrioTwo.list.length == 2) {
                    return false;
                }
            }
            return true;
        };

        //Submit your vote
        $scope.submitSubjects = function () {

            $scope.showError = false;
            $scope.showSuccess = false;
            $scope.doFade = false;

            var tempObject = {};
            tempObject.name = $scope.name;
            // HOOOOOOOHHHHHHHH josei-tachi senpai!

            //console.log($scope.models.subjects[0].list);

            // k�rer gennem de fag som er valgt i f�rste prio
            // og derefter tilf�jer dem til et objekt
            arrayPrioOne.list.forEach(function (element) {
                if (element.pool === 'A') {
                    tempObject.prioOnePoolA = element.topic;
                } else {
                    tempObject.prioOnePoolB = element.topic
                }
            });

            // k�rer gennem de fag som er valgt i anden prio
            // og derefter tilf�jer dem til et objekt
            arrayPrioTwo.list.forEach(function (element) {
                if (element.pool === 'A') {
                    tempObject.prioTwoPoolA = element.topic;
                } else {
                    tempObject.prioTwoPoolB = element.topic
                }
            });
            console.log(tempObject);

            studentFactory.submitVote(tempObject).
                success(function (data, status, headers, config) {
                    $scope.showSuccess = true;
                    $timeout(function () {
                        $scope.doFade = true;
                    }, 3000);
                    console.log("success", data);
                }).
                error(function (data, status, headers, config) {
                    $scope.errorMsg = "Done";
                    $scope.showError = true;
                    $timeout(function () {
                        $scope.doFade = true;
                    }, 3000);
                });

        };


    }]).factory('studentFactory', ['$http', function ($http) {

        var dataFactory = {};


        dataFactory.getElected = function () {
            return $http.get("http://localhost:8080/SIPElectiveStudyGrpA/api/subject/getElectedPools");
        };


        dataFactory.getSubjects = function () {
            return $http.get("http://localhost:8080/SIPElectiveStudyGrpA/api/subject");
        };

        dataFactory.submitVote = function (payload) {
            return $http.post("http://localhost:8080/SIPElectiveStudyGrpA/api/subject/saveStudentVote", payload);
        }


        return dataFactory;

    }]);