# Getting Started for Survey API

### Build and Run
* Build : ``mvn install``
* Run : ``./mvnw spring-boot:run``

### Default REST API
* [OpenAPI URL](http://localhost:8080/api/v3/api-docs)
* [Swagger-UI URL](http://localhost:8080/api/swagger-ui.html)

### Sample Data for /survey/decide
```
{
        "surveySelection": {
            "surveyCode": "TS-002",
            "surveySource": "SMH",
            "selectedChoice": 2
        }
}
```
### Sample Data for /admin/survey/create
```javascript
{
        "surveyQuestionInfo": {
            "surveyCode": "TS-003",
            "surveyType": "K",
            "surveyName": "favorute_location",
            "surveyDesc": "Survey for living location",
            "questionText": "Favourite Location",
            "options": {
                "choices": [
                    {
                        "choiceKey": 1,
                        "choiceName": "Castle Hill"
                    },
                    {
                        "choiceKey": 2,
                        "choiceName": "Epping"
                    }
                ]
            }        
        }
}
```