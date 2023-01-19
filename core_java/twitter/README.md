# Introduction
The Twitter app is a software that allows a user to interact with the Twitter API.
It allows you to post, retrieve and delete tweets.
The application follows a MVC architectural pattern and has been implemented with Java.
The communication with Twitter is assured by a HTTP client that communicates with Twitter REST API.
Maven is used to manage the project and the software has been deployed using Docker technology.

# Quick Start
To package the app, use the maven command: ``mvn clean package``

It will delete all the previous dependencies and package your application into a .jar file.
Make sure that you export those env variables:
- CONSUMER_KEY
- CONSUMER_SECRET
- ACCESS_TOKEN
- TOKEN_SECRET

Otherwise the command will fail because of the tests (you can also package the app and specify to not run tests).

you can generate these keys if you have a twitter developer account https://developer.twitter.com/en

The best way to run the app is to use Docker.
It supports 3 different modes:

  - 1: You can create a tweet: ``TwitterApp "post" "tweet_text" "latitude:longitude"``.
    - e.g.

      ``docker run --rm -e CONSUMER_KEY=YOUR_VALUE -e CONSUMER_SECRET=YOUR_VALUE -e ACCESS_TOKEN=YOUR_VALUE -e TOKEN_SECRET=YOUR_VALUE jrvs/twitter_app post "test post" "43:79"
      ``


  - 2: You can retrieve a tweet: ``TwitterApp show tweet_id``.
    - e.g.

      ``docker run --rm -e CONSUMER_KEY=YOUR_VALUE -e CONSUMER_SECRET=YOUR_VALUE -e ACCESS_TOKEN=YOUR_VALUE -e TOKEN_SECRET=YOUR_VALUE jrvs/twitter_app show 1276568976764686343
      ``


  - 3: You can delete a list of tweets: ``TwitterApp delete [id1,id2,..]``.
    - ex 

       ``docker run --rm -e CONSUMER_KEY=YOUR_VALUE -e CONSUMER_SECRET=YOUR_VALUE -e ACCESS_TOKEN=YOUR_VALUE -e TOKEN_SECRET=YOUR_VALUE jrvs/twitter_app delete 1200145224103841792``

# Architecture
## UML diagram
![architecture.png](assets%2Farchitecture.png)

## app/main
The main app is the starting point of our software, it creates and instantiates all the other layers.

## Controller
The Controller layer depends on Service, this layer is used to handle input arguments (tweet id, text, etc...).

## Service
The Service layer depends on DAO, we perform business logic in this layer, for example, to verify id and coordinates.

## DAO
The DAO is the only layer that interacts with the Twitter API to save, show and delete tweets. It handles models implemented with POJOs.

## Models
The Models are implemented using POJOs, a class containing private attributes and public getters and setters method.
When we communicate with the Twitter REST API, we need to encapsulate our data into different object models. Those objects are then serialized or deserialized into or from a JSON String.

## Spring
We used the Spring framework to set up and manage dependencies.
The IoC container create and manage the different beans: TwitterController, TwitterService, TwitterDao and TwitterHttpHelper.

# Test
To implement the integration and unit tests, we have used JUnit4 and Mockito.
You can run the test using the maven command
``mvn test``

# Deployment
To deploy the app, we have used docker containerization to build and push an image to the docker hub. Making it easy to be shareable.
You can pull it with:

``
docker pull terence971/twitter
``

# Improvements
- Add more functionalities and make tests for them.
- Add a CI pipeline to check syntax and run tests;
- Extend this project using AI, for example use chatGPT to generate text based on a subject and automate it with a cronjob. 