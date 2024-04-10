#### CoderHack Application
- It allows users to register, update their scores, and view the leaderboard.

## Features
 - CRUD operations for USER creation,deletion and updation of score for a contest
 - Updation of badges based upon the score
 - validation of score and badge updation

## Endpoints
- GET /users - returns a list of all users 
- GET /users/{userId} - fetch a user by his id
- POST /users - create a new user to the contest
- PUT /users/{userId}?score=10 - Update the score and assign badges to a user
- DELETE /users/{userId} - delete a user from the contest

## Postman Collection
- [Download Postman Collection](/CoderHack_Leaderboard.postman_collection.json)