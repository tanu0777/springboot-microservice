# Project Title

BankingApplication-microservices

As the microservices are the spring boot application, it can be normally run as Java application.

Steps to run the application :

Step-1 : Run the microservice EurekaServer for service registry, the service will run on port 8761.
         URL : http://localhost:8761/
         
Step-2 : Run the microservice ConfigServer for management of many microservices by centralizing their configuration in one location.
         URL : http://localhost:8085/application/default

Step-3 : Run the microservice API_Gateway to route all the requests through an API, the service will run on port 8084.

Step-4 : Run the microservice CustomerManagementService for features related to customer, the service will run on port 8082.
         
		 Method type and URLs for service endpoints : 
		 
		 a.	Add customer                         POST                  localhost:8082/customer      
         b.	Get all Customers                    GET                   localhost:8082/customer
         c.	Get single Customer Details          GET                   localhost:8082/customer/id/{customerId}
         d.	Update Customer Details              PUT                   localhost:8082/customer/id/{customerId}
         e.	Delete Customer                      DELETE                localhost:8082/customer/id/{customerId}


Step-5 : Run the microservice AccountManagementService for features related to account, the service will run on port 8082.

         Method type and URLs for service endpoints : 
		 
		 a.	Add Money to account                POST                 localhost:8083/account/deposit  
         b.	Withdraw money from account         POST                 localhost:8083/account/withdraw
         c.	Get all Account details             GET                  localhost:8083/account
		 d. Get single Account details          GET                  localhost:8083/account/accountnumber/{accountNumber}
         e.	Delete Account                      DELETE               localhost:8083/account/accountnumber/{accountumber}
