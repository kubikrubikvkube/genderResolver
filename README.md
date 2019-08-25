# genderResolver

Microservice used for resolve gender of a russian name.

**POST http://localhost:8080/name-resolver**
```
{
  "name": "Василий"
}

```
**200 OK**
```
{
    "gender": "MALE"
}
```
