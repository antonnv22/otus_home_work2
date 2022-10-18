.PHONY: build

build:
	docker build  -f Dockerfile . -t antonnv22/crud:0.0.56
push:
	docker push antonnv22/crud:0.0.56

docker-start:
	docker-compose up -d

k8s-deploy:
	kubectl create ns crud
	helm upgrade --install -n crud crud .
