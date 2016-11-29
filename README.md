# Hsystem 

A sample ranking highscore system that that can be used by any game or other stuff. 

Used Technologies: 
- Java 
- Spring 
- Jersey framework
- Maven
- MySQL
- jBoss EAP 6.2



## REST INTERFACE

### ACTORS

#### Get actor by publicId  
**Description** Get Actor info by giving the publicId  
**URL** http://127.0.0.1:8080/web/rest/actors?publicId=1   
**Method** GET  
**HTTP response** 200  
**HTTP response body (JSON)**  
'{  
  "public_id": "1",  
  "cryptedPassword": "dasdas",  
  "nationality": "Portuguese",  
  "creationDate": "2016-11-28",  
  "isDeleted": true  
}'  


#### Do login  
**Description** Login user by giving his data  
**URL** http://127.0.0.1:8080/web/rest/actors/login?publicId=1&password=dasdas  
**Method** GET  
**HTTP response** 200  
**HTTP response body (JSON)**  
'false' or 'true'  

#### Remove Actor by his publicId  
**Description** Remove actor by giving his publicId  
**URL** http://127.0.0.1:8080/web/rest/actors?publicId=1  
**Method** DELETE  
**HTTP response** 200 (OK) or 430 (NOK)  
**HTTP response body (JSON)**  
null  


#### Insert actor  
**Description** Insert actor in database  
**URL** http://127.0.0.1:8080/web/rest/actors   
**Method** POST  
**HTTP request body**  
'{ "public_id":"andremfaria",  
   "cryptedPassword":"faria",  
   "nationality":"Brazil",  
   "creationDate":null,  
   "isDeleted":false  
}'  
**HTTP response** 200 (OK) or 430 (NOK)  
**HTTP response body (JSON)**  
null  

#### Update actor  
**Description** Update actor in database  
**URL** http://127.0.0.1:8080/web/rest/actors   
**Method** PUT  
**HTTP request body**  
'{ "public_id":"andremfaria",  
   "cryptedPassword":"newpassword",  
   "nationality":"Brazil",  
   "creationDate":null,  
   "isDeleted":false  
}'  
**HTTP response** 200 (OK) or 430 (NOK)  
**HTTP response body (JSON)**  
null  

### HIGHSCORE

#### Get highscore by actorId  
**Description** Get Highscore of an actor  
**URL** http://127.0.0.1:8080/web/rest/highscores?userId=1   
**Method** GET  
**HTTP response** 200  
**HTTP response body (JSON)**  
'{
  "identifier": 5,
  "points": 12341212,
  "creationDate": "2016-11-03",
  "actorId": "1"
}'  

#### Insert highscore  
**Description** Insert highscore in database  
**URL** http://127.0.0.1:8080/web/rest/highscores   
**Method** POST  
**HTTP request body**  
'{
  "identifier": 8,  
  "points": 31232112,  
  "creationDate": "2016-11-28",  
  "actorId": "cr7"  
}'  
**HTTP response** 200 (OK) or 430 (NOK)  
**HTTP response body (JSON)**  
null  

#### Update highscore  
**Description** Update an existent highscore in Database.  
**URL** http://127.0.0.1:8080/web/rest/highscores   
**Method** PUT  
**HTTP request body**  
'{
  "identifier": 8,  
  "points": 12,  
  "creationDate": "2016-11-28",  
  "actorId": "cr7"  
}'  
**HTTP response** 200 (OK) or 430 (NOK)  
**HTTP response body (JSON)**  
null  

#### Remove Highscore
**Description** Remove highscore by giving userId
**URL** http://127.0.0.1:8080/web/rest/highscores?userId=qu
**Method** DELETE  
**HTTP response** 200 (OK) or 430 (NOK)  
**HTTP response body (JSON)**  
null  

#### Get All highscores  
**Description** Get all highscores ordered by Points.  
**URL** http://127.0.0.1:8080/web/rest/highscores/getall   
**Method** GET  
**HTTP response** 200 (OK)  
**HTTP response body (JSON)**  
'''
[  
  {  
    "identifier": 4,  
    "points": 123212,  
    "creationDate": "2016-11-03",  
    "actorId": "player"  
  },  
  {  
    "identifier": 3,  
    "points": 12312,  
    "creationDate": "2016-11-03",  
    "actorId": "player1"  
  },  
  {  
    "identifier": 5,  
    "points": 12,  
    "creationDate": "2016-11-03",  
    "actorId": "player2"  
  }  
]  
'''


#### Get Slice of all highscores  
**Description** Get slice of all highscores ordered by Points.  
**URL** http://127.0.0.1:8080/web/rest/highscores/getbetween?first=1&last=2 
**Method** GET  
**HTTP response** 200 (OK)  
**HTTP response body (JSON)**  
'''
[  
  {  
    "identifier": 4,  
    "points": 123212,  
    "creationDate": "2016-11-03",  
    "actorId": "player"  
  },  
  {  
    "identifier": 3,  
    "points": 12312,  
    "creationDate": "2016-11-03",  
    "actorId": "player1"  
  }  
]  
'''

## INSTALLATION NOTES 
//TODO
