REM @echo off
bash -c "sudo scp -rp ../psapApi jake@172.16.7.141:./ "
bash -c "sudo ssh jake@172.16.7.141 'sudo ./psapApi/buildAndPushToDocker.sh'"