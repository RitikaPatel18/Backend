describe(' Service Test', function(){
    var userService;
    var authService;
	beforeEach(function(){
		module('app');
		inject(function($rootScope,_UserService_,_AuthenticationService_){
            userService=_UserService_;
            authService=_AuthenticationService_;
		  testScope = $rootScope.$new();
		console.log("User service....."+userService);
        console.log("Auth service....."+authService);
	
		});
	
	  });
    
	it('Service Get User ', inject(function($httpBackend){
        var user;
		$httpBackend
			.whenGET('http://localhost:8080/user')
			.respond({ name:'Ritika' });
			$httpBackend
			.whenGET('login.html')
			.respond(200,'');

	 userService.get().then(function(response){
		  user=response;
        console.log("Response.....="+response.name);
        
     });

    
	$httpBackend.flush();
	console.log("Flush done");
	expect(user.name).toBe('Ritika');
	}));


	
	it('AuthService Login ', inject(function($httpBackend){
		var response;

		$httpBackend
			.whenGET('http://localhost:8080/user')
			.respond([{username:'ritika',password:'pass'}]);
			$httpBackend
			.whenGET('login.html')
			.respond(200,'');

	    authService.Login('ritika','pass',function(_response_){
		response=_response_;
		console.log("Login..............=="+response);
     });

    
	$httpBackend.flush();
	console.log("Flush done");
	expect(response.success).toBe(true);
	}));



});