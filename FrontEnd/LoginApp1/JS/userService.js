(function () {
    'use strict';
    
    angular.module("app")
       .service("UserService" , UserService);
    UserService.$inject = ['$timeout','$http', '$filter', '$q'];
 
	function UserService($timeout,$http, $filter, $q){
	  
	  var service = {};
	  
	  service.get = get;
	  service.getByUsername = getByUsername;
	  service.create = create;
    var token=window.localStorage.getItem("Token");
	  
	  return service;
	  
	function get(){
	  var deferred = $q.defer();
    getUsers().then(data=>{
    deferred.resolve(data);
    
    })
  return deferred.promise;
	}
	
	function getByUsername(username) {
    var deferred = $q.defer();
    getUsers()?.then(
      data=>{
    var filtered = $filter('filter')(data, { username: username });
    var user = filtered.length ? filtered[0] : null;
    deferred.resolve(user);
      }
    )
    return deferred.promise;
  }
  
  function create(user){
     var deferred = $q.defer();

    $timeout(function () {
    getByUsername(user.username)
       .then(function (duplicateUser) {
    
    if (duplicateUser !== null) {
        deferred.resolve({ success: false, message: 'Username "' + user.username + '" is already exists' });
    } else {

        createUser(user);
        console.log("User created");
        
        deferred.resolve({ success: true });
      }
    });
    }, 1000);

    return deferred.promise;
   }
  
	function getUsers(){
    console.log("token...."+token);
    var user= $http.get("http://localhost:8080/user", 
    {mode:'cors', headers:{'Access-Control-Allow-Origin':'*','Authorization':'Bearer '+token}})
  .then(response => {
    console.log("Data in getUsers()"+response.data);
     return response.data })
  // .then(data => {
  //   console.log("Data in getUsers()"+data);
  //   return data})
  console.log(user);
  return user;
	}
	
	function createUser(user){
   $http.post("http://localhost:8080/user/register", 
  user,
  {
      "Content-type": "application/json; charset=UTF-8"
  }
)
.then(response => 
  {  
    console.log(" Response...."+ response);
    response.json()
})
.then(json => console.log(json));
	}

}
})();