angular
    .module('amigoSecreto', [])
    .controller('AmigoSecretoController', ['$scope', '$http', function($scope, $http) {

        $scope.path = getContextPath();

        $scope.get_persons = function() {
            $http.get( getContextPath() + '/person/' ).success( function( persons ) {
                $scope.persons = persons;
            });
        }

        $scope.get_persons();
    }]
);
