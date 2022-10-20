otus_home_work2
1) В CRUD приложение и CRUD-CHART шаблоны helm

2) MAKEFILE для развертывания расположен в crud

.PHONY: build

build:
	docker build  -f Dockerfile . -t antonnv22/crud:0.0.60

push:
	docker push antonnv22/crud:0.0.60

docker-start:
	docker-compose up -d

k8s-deploy:
	kubectl create ns crud
	cd .. && helm upgrade --install -n crud crud .\crud-chart

newman:
	cd .. && newman run crud-chart/postman/collection.json

3) Развернутое приложение в миникуб

PS D:\otus\otus_homework2> kubectl get all --namespace crud

NAME                       READY   STATUS    RESTARTS   AGE
pod/crud-bbff7948c-tqtll   1/1     Running   0          5m27s
pod/postgresql-db-0        1/1     Running   0          5m27s

NAME                     TYPE           CLUSTER-IP       EXTERNAL-IP   PORT(S)          AGE
service/crud             NodePort       10.105.203.206   <none>        80:31882/TCP     5m27s
service/postgres-db-lb   LoadBalancer   10.96.249.134    <pending>     5432:30793/TCP   5m27s

NAME                   READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/crud   1/1     1            1           5m27s

NAME                             DESIRED   CURRENT   READY   AGE
replicaset.apps/crud-bbff7948c   1         1         1       5m27s

NAME                             READY   AGE
statefulset.apps/postgresql-db   1/1     5m27s

4) Проверка newman 
PS D:\otus\otus_homework2> newman run crud-chart/postman/collection.json

newman

New Collection

→ Get status
  GET arch.homework/probe/ready [200 OK, 99B, 105ms]

→ Get status 2
  GET arch.homework/probe/live [200 OK, 99B, 7ms]

→ Create user
  POST arch.homework/user [200 OK, 168B, 116ms]

→ Update user
  PUT arch.homework/user?userId=1 [200 OK, 249B, 28ms]

→ Get user
  GET arch.homework/user?userId=1 [200 OK, 249B, 13ms]

→ Delete user
  DELETE arch.homework/user?userId=1 [200 OK, 168B, 14ms]

┌─────────────────────────┬───────────────────┬──────────────────┐
│                         │          executed │           failed │
├─────────────────────────┼───────────────────┼──────────────────┤
│              iterations │                 1 │                0 │
├─────────────────────────┼───────────────────┼──────────────────┤
│                requests │                 6 │                0 │
├─────────────────────────┼───────────────────┼──────────────────┤
│            test-scripts │                 0 │                0 │
├─────────────────────────┼───────────────────┼──────────────────┤
│      prerequest-scripts │                 0 │                0 │
├─────────────────────────┼───────────────────┼──────────────────┤
│              assertions │                 0 │                0 │
├─────────────────────────┴───────────────────┴──────────────────┤
│ total run duration: 741ms                                      │
├────────────────────────────────────────────────────────────────┤
│ total data received: 274B (approx)                             │
├────────────────────────────────────────────────────────────────┤
│ average response time: 47ms [min: 7ms, max: 116ms, s.d.: 45ms] │
└────────────────────────────────────────────────────────────────┘

5) Креды в секретах 

apiVersion: v1
kind: Secret
type: Opaque
metadata:
  name: secrets
  labels:
    name: crud
data:
  postgres_user: cG9zdGdyZXM=
  postgres_password: cG9zdGdyZXM=
  
6) При необходимости добавить скрипты инициализации в конфигмап

apiVersion: v1
kind: ConfigMap
metadata:
  name: mysql-initdb-config
data:
  initdb.sql: |
    CREATE SCHEMA user_db;

монтируется в postgres_statefulset.yaml
  
