angular
    .module('cadastro', [])
    .controller('CadastroController', ['$scope', '$http', function($scope, $http) {

        $scope.path = getContextPath();
        $scope.person = {};

        $scope.save = function() {
            $('#messages').removeClass('alert alert-success').empty();

            $http({
                method  : 'POST',
                url     : getContextPath() + '/person/',
                data    : $.param($scope.person),
                headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
            })
            .then(function(person) {
                console.log(person.statusText);

                if ( person.statusText == "OK" ) {
                    $('#messages').addClass('alert alert-success').append('<p>Cadastrado com sucesso!</p>');
                    window.location.href = getContextPath() + '/static/index.html';
                } else {
                    $('#messages').addClass('alert alert-error').append('<p>' + data.message + '</p>');
                }

            });
        };

    }]);
