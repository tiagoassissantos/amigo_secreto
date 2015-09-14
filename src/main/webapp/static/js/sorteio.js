angular
    .module('sorteio', [])
    .controller('SorteioController', ['$scope', '$http', function($scope, $http) {

        $scope.path = getContextPath();
        $scope.persons = [{}];

        $scope.sortear = function() {
            $('#messages').removeClass('alert alert-success').empty();

            $http.get( getContextPath() + '/sortition/' ).success( function( persons ) {
                console.log(persons);
                $scope.persons = persons;
            });
        };

        $scope.sortear();

    }]);
