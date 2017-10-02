# Survey Statistics service [![Build Status](https://travis-ci.org/comtihon/survey_statistics.svg?branch=master)](https://travis-ci.org/comtihon/survey_statistics)
Survey service manager backend. Works with surveys, questions and answers. For more info see #Protocol.

## Run
Ensure that [MongoDb](https://www.mongodb.com/) is accessible before running the service.  
Access urls are specified in application.properties for `spring.data.mongodb.uri` for mongodb.

### In docker

    sudo ./gradlew build buildDocker
    sudo docker run -p 8080:8080 -t com.surveyor.statistics

### In OS

    ./gradlew bootRun

## Protocol
GET __/question/{id}__ where `{id}` is of the question you would like to get statistics on.  
  
Response:

    {
        "result" : true,
        "response" :
        {
            "question_id" : "id from request",
            "answer_id1" : N
            "answer_id2" : M
        }
    }
Where `N` and `M` are integers.