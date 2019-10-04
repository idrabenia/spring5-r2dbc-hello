To run the project it is require to run next commands
```bash
docker run -p 5432:5432 postgres:latest
./gradlew clean build
java -jar ./build/libs/spring5-hello-0.0.1-SNAPSHOT.jar
```
