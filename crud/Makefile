.PHONY: build


build:
	docker build  -f Dockerfile . -t antonnv22/crud:0.0.59

push:
	docker push antonnv22/crud:0.0.59

docker-start:
	docker-compose up -d

k8s-deploy:
#	kubectl create ns crud
	cd .. && helm upgrade --install -n crud crud .\crud-chart

newman:
	cd .. && newman run crud-chart/postman/collection.json