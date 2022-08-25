(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterController', RegisterController);

    RegisterController.$inject = ['UserService','$location'];
    function RegisterController(UserService,$location) {
        var vm = this;
    
        vm.dataLoading = false;
        vm.register = register;
        vm.test=test;

        function register() {
            vm.dataLoading = true;
            console.log(vm.user)
            var tempUser = {
            "email":vm.user.email,
            "password":vm.user.password,
            "firstName":vm.user.firstName,
            "lastName":vm.user.lastName, 
            "username":vm.user.username,
            "number":vm.user.number,
            }
            UserService.create(tempUser)
                .then(function (response) {
                    if (response.success) {
                        alert('Registration successful');
                        console.log('Registration done................');
                         $location.path('/');
                    } else {
                        alert(response.message);
                        vm.dataLoading = false;
                    }
            });
        }
        function test(){
            console.log("test called...")
        }
    }
})();