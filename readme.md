This application demonstrates a deadlock on wildfly as reported in this issue: https://issues.redhat.com/browse/WFLY-13686

Steps to run:
1. `mvn clean install`
2. `cd deadlock.docker/etc`
3. `./build.sh`
4. `docker container run -p 8080:8080 local/deadlock:latest`
