# Introduction
The Twitter CRUD app allows the user to post, read, and delete Tweets from the command line. The program is written in Java
and uses the Maven build tool. CRUD operations are done using Twitter API and the MVC pattern. The app has been dockerized 
and can also be executed using the jar file. 

# Quick Start
- how to package your app using mvn?
- how to run your app with docker?

# Design
## UML diagram
## explain each component(app/main, controller, service, DAO) (30-50 words each)
As stated in the intro, the project follows the MVC(minus the view) or Model Control View design pattern. Built using the bottom up 
approach, the first component made was the Dao.

The Dao is made up of 2 classes. HttpHelper and TwitterDao. TwitterDao is used to create a URI that we can pass to the
Twitter API and HttpHelper will either post or get the URI depending on the user's choice of command.




## Models
The Tweet model used in the project is a simplified version of the full Tweet object. It has 10 attributes, including:
id, text, timestamp, coordinates, etc. However, only 2 are required for creating a Tweet(text and coordinates) and only
1 is required for searching and deleting Tweets(id). 


## Spring
- How you managed the dependencies using Spring?

# Test
How did you test you app using Junit and mockito?

## Deployment
How did you dockerize your app.

# Improvements
- Imporvement 1
- Imporvement 2
- Imporvement 3