gradle clean build
sudo docker build -t tipocambio:latest .
sudo docker stop tipocambio
sudo docker rm tipocambio
sudo docker run -d --name tipocambio -p 8080:8080 tipocambio:latest