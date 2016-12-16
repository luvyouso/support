
// Use Parse.Cloud.define to define as many cloud functions as you want.
// For example:
var notificationAllowedPerWeek = 20;


Parse.Cloud.define("hello", function(request, response) {
	
  	response.success("Hello world!");
	
});

Parse.Cloud.define("weekChanged", function(request, response) {
	//add record for the user to NotificationCount Class
	/*console.log("No record found in Notification Count for the User " + currentUserId);
	var user= Parse.Object.extend("User");
	var query = new Parse.Query(user);
	query.equalTo("objectId", currentUserId);
	query.find({
		success: function(results) {
			console.log("Inside notificationCount Record Add");
			notificationLeftWeek = 5;
			//	$('#notificationCountServer').html('<strong>'+notificationLeft+'</strong>');
			var NotificationCount = Parse.Object.extend("NotificationCount");
			var notificationCount = new NotificationCount();
								
			notificationCount.set("currentUserId", currentUserId);
			notificationCount.set("date", new Date());					
			notificationCount.set("count", 5);
			notificationCount.set("weekCount",0);
			notificationCount.save();
			response.success(5);
		},
		error: function(error) {
			response.error(error.code + " " + error.message);
			alert("Error: " + error.code + " " + error.message);
		}
	});*/
	
	var currentUserId = request.params.currentUserId;									   
	var notificationLeftWeek;	
	var userCount = 0;
   	var NotificationCount = Parse.Object.extend("NotificationCount");
	console.log("NotificationCount Query Find Starting");
	var query = new Parse.Query(NotificationCount);
	query.equalTo("currentUserId", currentUserId);
	
	query.count({
		success: function(count) {
			//alert("Successfully retrieved " + results.length + " scores.");
			// Do something with the returned Parse.Object values

			console.log("NotificationCount Query Count Successful " + count);
			userCount = count;
			
			if (userCount == 0){
				console.log("Inside notificationCount Record Add. Count: " + userCount);
		
				var NotificationCount = Parse.Object.extend("NotificationCount");
				var notificationCount = new NotificationCount();
									
				notificationCount.set("currentUserId", currentUserId);
				notificationCount.set("date", new Date() + "");					
				notificationCount.set("count", notificationAllowedPerWeek);
				notificationCount.set("weekCount",0);
				notificationCount.save(null,{
					success: function(notificationCount){
						response.success(notificationAllowedPerWeek);						   
					},
					error: function(notificationCount, error){
						response.error(error.code + " " + error.message);
					}		   
				});		
			}else{
				var NotificationCount = Parse.Object.extend("NotificationCount");
				console.log("NotificationCount Query Find Starting");
				var query = new Parse.Query(NotificationCount);
				query.equalTo("currentUserId", currentUserId);
				query.find({
					success: function(results){
						for (var i = 0; i < results.length; i++) { 
							var objectNotificationCount = results[i];
							notificationLeftWeek = objectNotificationCount.get('count');
							creationDate = objectNotificationCount.get('date');
							//alert("Date"+object.get('date'));
								  
							weekCount = objectNotificationCount.get('weekCount');
							//$('#notificationCountServer').html('<strong>'+notificationLeft+'</strong>');
							//alert(object.id + ' - ' + object.get('count'));
							//alert("Date 2" +creationDate );
							
							var date1 = new Date(creationDate);
							var date2 = new Date();
							
							//alert(date2);
							var one_day=1000*60*60*24;
									
							// Convert both dates to milliseconds
							var date1_ms = date1.getTime();
							var date2_ms = date2.getTime();
							
							// Calculate the difference in milliseconds
							var difference_ms = date2_ms - date1_ms;
								
							// Convert back to days and return
							var diff = parseInt(Math.round(difference_ms/one_day)/7);							
							
							//alert("diff "+diff);
							console.log("Creation time: " + date1);
							console.log("Current time: " + date2);
							console.log("difference min: " + diff);
							console.log("weekCount: " + weekCount);							
							console.log("notificationLeftForWeek: " + notificationLeftWeek);

							if(diff > weekCount && notificationLeftWeek <= notificationAllowedPerWeek){								
								notificationLeftWeek = notificationAllowedPerWeek;
								//	$('#notificationCountServer').html('<strong>'+notificationLeft+'</strong>');
								objectNotificationCount.set("count", notificationLeftWeek);
								objectNotificationCount.set("weekCount",diff);
								objectNotificationCount.save();
								response.success(notificationAllowedPerWeek);									
							}else{
								response.success(notificationLeftWeek);
							}
						}
					},
					error: function(){
						response.error(error.code + " " + error.message);
					}
				});								
			}
		},
		error: function() {			
			//alert("Error: " + error.code + " " + error.message);
			userCount =0;
		}
	});
});

Parse.Cloud.define("notification", function(request, response) {
	var triggerValue = request.params.triggerValue;
  	var settingValue1 = request.params.settingKey1;
  	var settingValue2 = request.params.settingKey2;
  	var settingValue3 = request.params.settingKey3;
  	var settingValue3 = request.params.settingKey3;
   	var currentUserId = request.params.currentUserId;
   	var notificationLeft;
	var objectNotificationCount;
   
   	/*Parse.Cloud.run('checkNotificationCount', {currentUserId: currentUserId}, {
		success: function(success) {
			console.log("notificationLeft "+notificationLeft);
			notificationLeft = success;
		},
		error: function(error) {
			alert("Error Find Trigger: " + error + " " + error.message);
		}
	});*/
   
   	console.log("Send Notification Begin");
    var NotificationInitialCount = Parse.Object.extend("NotificationCount");
	var queryInitial = new Parse.Query(NotificationInitialCount);
	queryInitial.equalTo("currentUserId", currentUserId);
	console.log("Send Notification UserId: " + currentUserId);
	try{		
		queryInitial.find({
			success: function(results) {
				//alert("Successfully retrieved " + results.length + " scores.");
				// Do something with the returned Parse.Object values
				console.log("success");
				// response.success(results[0].get('count')); 
				for (var i = 0; i < results.length; i++) { 
					objectNotificationCount = results[i];
					notificationLeft = objectNotificationCount.get('count');
				}
				if(notificationLeft > 0){
		  			var query = new Parse.Query(Parse.Installation);
		  			query.equalTo("installationId", request.params.installationId);											
		  			Parse.Push.send({where: query, // Set our Installation query
							data: {					  
								triggerKey:triggerValue, 
								settingKey1:settingValue1,
								settingKey2:settingValue2,
								settingKey3:settingValue3,
								objectType:"android",
								action:"com.mangobird.library.findmyphone.MESSAGE"
							}
						},{success: function() {
							// Push was successful
							objectNotificationCount.set("count", notificationLeft-1);
							objectNotificationCount.save();
							response.success(notificationLeft-1);								
						},
						error: function(error) {
							// Handle error
							response.error("Error " + error.code + " " + error.message);
						}
					});
				}else{
					response.error("Only " +  notificationAllowedPerWeek + " notifications per week allowed");
				}
			},
			error: function(error) {
				console.log("Error: " + error.code + " " + error.message);
				response.error("Error: " + error.code + " " + error.message);
				alert("Error: " + error.code + " " + error.message);
			}
		});
	}catch(e){
		console.log(e);
	}
   	console.log("notificationLeft "+notificationLeft);
});

Parse.Cloud.define("checkNotificationCount", function(request, response) {
	var notificationLeft;
   	console.log("checkNotificationCount begin");
	var currentUserId = request.params.currentUserId;
	var NotificationInitialCount = Parse.Object.extend("NotificationCount");
	var queryInitial = new Parse.Query(NotificationInitialCount);
	queryInitial.equalTo("currentUserId", currentUserId);
	console.log("checkNotificationCount UserId: " + currentUserId);
	try{
		queryInitial.find({
		  success: function(results) {
			//alert("Successfully retrieved " + results.length + " scores.");
			// Do something with the returned Parse.Object values
			 console.log("success");
			for (var i = 0; i < results.length; i++) { 
			  var object = results[i];
			  notificationLeft = object.get('count');
			 }
			response.success(notificationLeft);
			
		  },
		  error: function(error) {
			  console.log("Error: " + error.code + " " + error.message);
			  response.error("Error: " + error.code + " " + error.message);
			  alert("Error: " + error.code + " " + error.message);
		  }
		});
	}catch(e){
		console.log(e);
	}
});

var isid;

Parse.Cloud.define("pingNotification", function(request, response) {
	var pingValue = request.params.pingValue;
	var responseValue; 
	var currentUserId = request.params.currentUserId;									   
	isid = request.params.installationId;
	console.log("pingNotification starting");
	
	var notificationLeft;
	var objectNotificationCount;
   	var NotificationInitialCount = Parse.Object.extend("NotificationCount");
	var queryInitial = new Parse.Query(NotificationInitialCount);
	queryInitial.equalTo("currentUserId", currentUserId);
	console.log("pingNotification UserId "+currentUserId);
	try{		
		queryInitial.find({
			success: function(results) {
				//alert("Successfully retrieved " + results.length + " scores.");
				// Do something with the returned Parse.Object values
				console.log("notification count extract : success");
				// response.success(results[0].get('count')); 
				for (var i = 0; i < results.length; i++) { 
					objectNotificationCount = results[i];
					notificationLeft = objectNotificationCount.get('count');
				}
				if(notificationLeft > 0){
					var query = new Parse.Query(Parse.Installation);
					query.equalTo("installationId", request.params.installationId);											
					Parse.Push.send({where: query, // Set our Installation query
						data: {	
							alert:"Connectivity Ping",
							pingTrigger:pingValue, 
							objectType:"android",
							action:"com.mangobird.library.findmyphone.MESSAGE"
						}
						},{
						success: function(objectInstallationId) {
							// Push was successful
							console.log("ping sent");
													
							var proApp = Parse.Object.extend("proApp");
							var queryPing = new Parse.Query(proApp);
							queryPing.equalTo("installationId", request.params.installationId);
							queryPing.first({
								success: function(results) {					
									results.set("lastDevicePingTime", new Date());
									results.unset("lastDevicePingResponseTime");				
									results.save();
																		
									console.log("Notification Count: " + notificationLeft);
									objectNotificationCount.set("count", notificationLeft-1);
									objectNotificationCount.save();
									console.log("Notification Count Updated to: " + notificationLeft-1);
									
									var devicePingTime = results.get("lastDevicePingTime");
							
									response.success(devicePingTime);
									console.log("Update Ping send Time");
								},
								error: function(error) {
									response.error("0");										
								}
							});			
						},
						error: function(error) {
							// Handle error
							response.error("0");
						}
					});
				}else{
					response.error("Only " +  notificationAllowedPerWeek + " notifications per week allowed");
				}
			},
			error: function(error){
				console.log(error);	
				response.error(error);
			}
			});
		}catch(e){
			console.log(e);
		}
});

Parse.Cloud.define("devicePingResponse", function(request, response) {
	console.log("devicePingResponse");
	var proApp1 = Parse.Object.extend("proApp");
	var queryPing1 = new Parse.Query(proApp1);
	queryPing1.equalTo("installationId", request.params.installationId);
	queryPing1.equalTo("currentUserID", request.params.currentUserID);
	queryPing1.first({
		success: function(result) {
			result.set("lastDevicePingResponseTime", new Date());
			result.save();
			console.log("devicePingResponse Success");
			response.success("0");
		},
		error: function(error) {
			console.log("devicePingResponse Error: " + error.code + " " + error.message);
			response.error("1");
			//alert("Error: " + error.code + " " + error.message);
		}
	});	

});

Parse.Cloud.define("devicePingResponseStatusCheck", function(request, response) {
	//called from mobile device to check if ping response correctly updated
	console.log("devicePingResponseStatusCheck");
	var proApp1 = Parse.Object.extend("proApp");
	var queryPing1 = new Parse.Query(proApp1);
	queryPing1.equalTo("installationId", request.params.installationId);
	queryPing1.equalTo("currentUserID", request.params.currentUserID);
	queryPing1.first({
		success: function(result) {
			
			var lastDeviceResponseTime = result.get("lastDevicePingResponseTime");
			
			if (lastDeviceResponseTime != null){		
				response.success("0");
				console.log("devicePingResponseStatusCheck Success");
			}else{
				response.error("1");	
				console.log("devicePingResponseStatusCheck Error ");
			}
		},
		error: function(error) {
			console.log("devicePingResponseStatusCheck Error: " + error.code + " " + error.message);
			response.error("1");
			//alert("Error: " + error.code + " " + error.message);
		}
	});	

});


Parse.Cloud.define("checkDevicePingResponse", function(request, response) {

	
	//alert(deviceResponse);								
	console.log("checkDevicePingResponse");
	var proApp1 = Parse.Object.extend("proApp");
	var queryPing1 = new Parse.Query(proApp1);
	queryPing1.equalTo("installationId", request.params.installationId);
	//queryPing1.equalTo("currentUserID", request.params.currentUserID);
	queryPing1.first({
		success: function(result) {
			var devicePingTime = result.get("lastDevicePingTime");
			
			console.log("devicePingTime.length " + devicePingTime);
			console.log("devicePingTime.length " + (devicePingTime == null));
			console.log("devicePingTime.length " + (devicePingTime == "undefined"));
			console.log("devicePingTime.length " + (devicePingTime == ""));
			
			if (devicePingTime != null){
			
				var deviceResponseTime = result.get("lastDevicePingResponseTime");
				
				if (deviceResponseTime != null){
					console.log("checkDevicePingResponse Success");
					result.unset("lastDevicePingResponseTime");				
					result.unset("lastDevicePingTime");				
					result.save();
					response.success("Success");
				}			
			}else{
				console.log("checkDevicePingResponse Abort");
				response.success("Abort");
			}
			
			//response.success("success");
			
		},
		error: function(error) {
			//results.set("lastDevicePingResponseTime", new Date());
			//results.set("lastDevicePingTime", new Date());
			console.log("checkDevicePingResponse Error: " + error.code + " " + error.message);
			response.error("Abort");
		}
	});	
	
	//response.success(deviceResponse);
});

var getNotificationCount = function(request, response) {
	console.log("getNotification Count Begin");
	var  notificationLeft = 0;
	var currentUserId = request.params.currentUserId;									   
    var NotificationInitialCount = Parse.Object.extend("NotificationCount");
	var queryInitial = new Parse.Query(NotificationInitialCount);
	queryInitial.equalTo("currentUserId", currentUserId);
	console.log("getNotificationCount UserId: "+currentUserId);
	queryInitial.find({
		success: function(results) {
			//alert("Successfully retrieved " + results.length + " scores.");
			// Do something with the returned Parse.Object values
			console.log("getNotificationCount success: " + results[0].get('count'));
			// response.success(results[0].get('count')); 
			for (var i = 0; i < results.length; i++) { 
				var object = results[i];
				notificationLeft = object.get('count');
			}		
			
			response.success(notificationLeft);
		},
		error: function(error){
			response.success(0);		
		}
	});
}

var decrementNotificationCount = function(request, response) {
	console.log("decrementNotificationCount Begin");	
	var currentUserId = request.params.currentUserId;									   
    var NotificationInitialCount = Parse.Object.extend("NotificationCount");
	var queryInitial = new Parse.Query(NotificationInitialCount);
	queryInitial.equalTo("currentUserId", currentUserId);
	console.log("decrementNotificationCount UserId: " + currentUserId);

	queryInitial.first({
		success: function(results) {
			//alert("Successfully retrieved " + results.length + " scores.");
			// Do something with the returned Parse.Object values
			console.log("success");
			// response.success(results[0].get('count')); 
				var notificationCount = results;
				var count = notificationCount.get("count");
				notificationCount.set("count", --count );

				notificationCount.save(null,{
					success: function(notificationCount){
						response.success(0);						   
					},
					error: function(notificationCount, error){
						response.error(error.code + " " + error.message);
					}		   
				});		
			response.success(notificationLeft);
		},
		error: function(error){
			response.error(error.code + " " + error.message);		
		}
	});
}


//define function for login and signup and call them from android code...







