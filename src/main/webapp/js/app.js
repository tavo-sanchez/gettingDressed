var app = angular.module("gettingDressed", [])
    .controller("gettingDressedController", function($http, $scope){
        $scope.temperature = "";
        $scope.commands = "";
        $scope.description = "";
        $scope.submitForm = function(event){
            var dataObject = {
                temperature : $scope.temperature,
                commands  : $scope.commands
            };
            $http.post("gettingdressed/", dataObject)
                .success(function(data, status){
                    $scope.temperature = "";
                    $scope.commands = "";
                    $scope.description = data;
                })
                .error(function(err, status){
                    return err;
                })
        }
    });
