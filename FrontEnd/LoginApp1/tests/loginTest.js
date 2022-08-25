// describe('TestLoginController', function() {
//     beforeEach(module('app'));

//     var $controller;
//     var  $rootScope;
//     var $compile;
//     beforeEach(inject(function(_$compile_,_$controller_,_$rootScope_,_$httpBackend_){
//               $controller = _$controller_;
//               $rootScope = _$rootScope_;
//               $compile=_$compile_;
//               $httpBackend =_$httpBackend_;
//               $httpBackend.whenGET('http://localhost:8080/user/register').respond(200, '<div></div>');
//     }));



//    describe('description', function() {
//         it('Check the scope object', function() {
//             var $scope = $rootScope.$new();
//             var controller = $controller('TestLoginController', { $scope: $scope});
//             $scope.process();
//             $httpBackend.flush();
//             //console.log($scope.ID);
//             $scope.obj={'id':'1'};
            
//             var element = $compile("<user obj='obj'></user>")($scope);
//             $scope.$digest();
//             expect().toBe();
        
//         });

//         // it('Should reach to correct location',function(){
//         //     expect(loc('/')).toBe('/');
//         // });

//     });
// });