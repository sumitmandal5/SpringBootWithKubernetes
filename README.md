# SpringBootWithKubernetes
A simple springboot app using mysql db deployed on kubernetes

## CONTENTS OF THIS FILE

* Prerequisite
* Build and steps to run application on local
* Deployment steps on minikube
* Steps to undeploy
* Few Implemented basic functionalities
* Next steps

## Prerequisite

- Docker with kubernetes enabled
- Kubernetes command-line tool(kubectl)
- minikube
- Java 11
- Maven

## Build and steps to run application on local

Commands to build and run project on local.

1. Clone the project from the github.
2. Install MySql 5,run queries.sql file and add correct values of url,userName and password in application.yml.
3. Run mvn clean install command this will resolve all the dependencies issue.

## Deployment steps on minikube

1. cd to project directory SpringBootWithKubernetes.
2. Build the docker image.
   >docker build -t springboot-k8s-mysql:1.0 .
3. start minikube on local.
   >minikube start
4. push the image to minikube
   >minikube image load springboot-k8s-mysql:1.0
5. cd to deploy folder.
6. Run the below kubectl commands to deploy the services on minikube.
    >kubectl apply -f mysql-configmap.yml
   
    >kubectl apply -f mysqldb-root-credentials.yml
   
    >kubectl apply -f mysqldb-credentials.yml
   
    >kubectl apply -f mysql-deployment.yml
   
    >kubectl apply -f deployment.yml
7. Start the service
   >minikube service --url springboot-k8s-mysql

## Steps to undeploy
1. Run the below kubectl commands.
    >kubectl get deployments

    >kubectl delete deployment mysql

    >kubectl delete deployment springboot-k8s-mysql

    >kubectl get secrets

    >kubectl delete secret db-credentials

    >kubectl delete secret db-root-credentials

    >kubectl get services

    >kubectl delete service springboot-k8s-mysql

    >kubectl get configmaps

    >kubectl delete configmap db-conf
2. Delete the image from minikube cluster.
   >minikube image unload springboot-k8s-mysql:1.0
3. Delete the docker image from local docker repo.
    >docker images
    >docker image rm -f springboot-k8s-mysql:1.0

## Few Implemented basic functionalities of the service
1. Get all students
2. Get students based on id
3. Delete students based on Id
4. Get all streams
5. Get streams based on id
6. Delete streams based on Id
7. Get all sports
8. Get sports based on id
9. Delete sports based on Id
10. Add new student,stream,sports
11. Modify student,stream,sports
12. Get all students in science stream who play cricket.
13. Add exception handling
14. Add HAETEOS links
15. Add checks on data e.g student name can not be null
16. Implelemt soft delete

## Next steps
1. Add unit tests.
2. Add proper logs.
3. Fix Swagger(currently not working).
4. Add quality gate for coverage.