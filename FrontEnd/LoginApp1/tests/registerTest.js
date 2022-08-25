// describe('Register',function(){
//     beforeEach(module('app'));
//     describe('registration',function(){
//        var flag=true;
//        it('Should reach to correct location',function(){
//         expect(flag).toBe(true);
//      } );
       
//         });
//     });

//  import sinon from 'sinon';
//  import 'jasmine-sinon';

describe(' ', function(){
	beforeEach(function(){
		module('app');
		inject(function($rootScope, $controller,$q,UserService){
		  testScope = $rootScope.$new();
		  //mockUserService=sinon.stub(UserService);
		  //console.log("mock Service.."+mockUserService);
		  myTestController = $controller('RegisterController', {
			$scope: testScope,
			UserService: {
			  create: function(){
				console.log("Create called......");
				var deferred = $q.defer();
				deferred.resolve({ success: true });
				return deferred.promise;
			  }
			}
	
		  });
	
		});
	
	  });
	it(' ', inject(function($httpBackend){
		$httpBackend
			.whenPOST('http://localhost:8080/user/register')
			.respond({ name:'Ritika' });
			$httpBackend
			.whenGET('login.html')
			.respond(200,'');

			var tempUser={
            "email":'abc@gmail.com',
            "password":'abcd',
            "firstName":'Fname',
            "lastName":'Lname', 
            "username":'abc1',
            "number":1234123412,
            }
		// var myController = $controller('RegisterController', {
		// 	$scope: scope,
		// 	userService: userService
		//   });
	  
	  
		myTestController.user=tempUser;

	 myTestController.register();
    console.log(myTestController);
	$httpBackend.flush();
	console.log("Flush done");
	expect(myTestController.dataLoading).toBe(true);
	

	
	}));
});