# Tic Tac Toe Kata
A sample Tic-Tac-Toe application developed using **Test Driven Development** approach. 

As one of the requirement was to use Java/Spring, this application is developed as a Spring Boot REST API. 
No client has been developed (as it was not required). But it is easy to interract with the game using simple CURL commands (see below)

## Running the project

#### Download from Git

To download the project, simply open a terminal window and execute the following command :

`git clone https://github.com/krieklambic/2021-DEV1-035.git`
#### Execute the tests
cd to the project directory and execute :

`./gradlew :cleanTest :test -i --tests "net.comexis.kata.tictactoe.TictactoeApplicationTests"`

When all test are executed, an HTML test report will be available in the `/build/reports/tests/test` repository.

#### Launch the Tic Tac Toe API
In a terminal window, cd to the project directory and execute :

`./gradlew bootRun -x test`

Once started, the API is available at the http://localhost:8080/tic-tac-toe/api/ url

## Interacting with the game API

Interacting with the game can be as simple as sending CURL commands.
The two principal commands are for:

- Creating a new game
- Playing moves

#### Create a new game
To create a new game, simply POST an empty body to https://localhost/tic-tac-toe/api/games

`curl --header "Content-Type: application/json"  --request POST --data '{}' http://localhost:8080/tic-tac-toe/api/games`

The API will respond with the created Game information :

`{
     "id": 2,
     "moves": [],
     "gameStatus": {
         "statusType": "RUNNING",
         "winType": null,
         "winTypeNumber": null,
         "playerType": null
     },
     "active": true,
     "board": {
         "cells": [
             {
                 "number": 1,
                 "playerType": null,
                 "empty": true
             },
             {
                 "number": 2,
                 "playerType": null,
                 "empty": true
             },
             {
                 "number": 3,
                 "playerType": null,
                 "empty": true
             },
             {
                 "number": 4,
                 "playerType": null,
                 "empty": true
             },
             {
                 "number": 5,
                 "playerType": null,
                 "empty": true
             },
             {
                 "number": 6,
                 "playerType": null,
                 "empty": true
             },
             {
                 "number": 7,
                 "playerType": null,
                 "empty": true
             },
             {
                 "number": 8,
                 "playerType": null,
                 "empty": true
             },
             {
                 "number": 9,
                 "playerType": null,
                 "empty": true
             }
         ],
         "full": false
     }
 }`
 
 It mainly contains :
 
 - The id of the game (later used to place moves)
 - The Game status (Running / Game Over / Draw)
 - The board information with each cell status
 
 
#### Place a move

To place a move, POST to the http://localhost:8080/tic-tac-toe/api/move with the move information in the body :

`curl --header "Content-Type: application/json"  --request POST --data '{"game" : {"id" : 1}, "cellNumber" : 1, "playerType" : "X"}' http://localhost:8080/tic-tac-toe/api/moves`

The API will respond with the confirmed move information :

'{"id":2,"cellNumber":1,"playerType":"X","active":true}'

#### Get the new status of the game with updated move

After playing a move it can be interesting to get the new game status with updated board :

`curl --header "Content-Type: application/json"  --request GET http://localhost:8080/tic-tac-toe/api/games/1`

The API will respond with the new game status :

`{"id":1,"moves":[{"id":2,"cellNumber":1,"playerType":"X","active":true}],"gameStatus":{"statusType":"RUNNING","winType":null,"winTypeNumber":null,"playerType":null},"active":true,"board":{"cells":[{"number":1,"playerType":"X","empty":false},{"number":2,"playerType":null,"empty":true},{"number":3,"playerType":null,"empty":true},{"number":4,"playerType":null,"empty":true},{"number":5,"playerType":null,"empty":true},{"number":6,"playerType":null,"empty":true},{"number":7,"playerType":null,"empty":true},{"number":8,"playerType":null,"empty":true},{"number":9,"playerType":null,"empty":true}],"full":true}}`

_Note that _



