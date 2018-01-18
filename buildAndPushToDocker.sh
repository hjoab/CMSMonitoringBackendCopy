sudo ./psapApi/gradlew ./psapApi build
sudo ./psapApi/gradlew build docker
sudo docker tag reemo/psap-api reemohealth.azurecr.io/reemo/psap-api
sudo docker push reemohealth.azurecr.io/reemo/psap-api