REM @echo off
gradle build
bash -c "scp ./build/libs/psap-api-1.0-SNAPSHOT.jar reemo@52.165.156.9:./ && ssh reemo@52.165.156.9 'java -jar ./psap-api-1.0-SNAPSHOT.jar'"
