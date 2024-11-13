pipeline {
    agent any
    stages {
        stage("cloning") {
            steps {
                echo "========cloning with git========"
                git url: "git@github.com:skaandernasri/Devops-Project.git",
                    branch: "SayariMohamed-BI3-G2",
                    credentialsId:"github"
            }
        }
        stage("compiling") {
            steps {
                echo "========compiling with maven========"
                sh "mvn clean compile"
            }
        }
        stage("Testing") {
            steps {
                echo "========Testing with maven========"
                sh "mvn clean test"
            }
        }
        stage("Packaging") {
            steps {
                echo "========Packaging with maven========"
                sh "mvn clean package"
            }
        }
        stage("Scan"){
            steps{
                echo "========Analyzing with Sonarqube========"
                withSonarQubeEnv(installationName: 'sonarqube-server'){
                    sh 'mvn sonar:sonar'
                }
            }
        }
        stage("Deploying nexus") {
            steps {
                echo "========Deploying to Nexus========"
                sh 'mvn clean deploy -DskipTests'
            }
        }
        stage("Building image"){
            steps{
                sh "docker build -t sayari076/sayari076_kaddem . "
            }
        }
        stage("Pushing to DockerHub") {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh "echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin"
                    sh "docker push sayari076/sayari076_kaddem"
                }
            }
        }
        stage("Stoping containers"){
            steps{
                sh "docker-compose down"
            }
        }
        stage("Running containers"){
            steps{
                sh "docker-compose up -d"
            }
        }
    }
    post {
        always {
            echo "========always========"
        }
        success {
            echo "========pipeline executed successfully========"
        }
        failure {
            echo "========pipeline execution failed========"
        }
    }
}