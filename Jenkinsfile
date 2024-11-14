pipeline {
    agent any

    tools {
        // Specify the Maven installation in Jenkins
        maven 'M2_HOME'  // Change to your Maven installation name
    }

    environment {
        GIT_REPO = 'https://github.com/skaandernasri/Devops-Project.git'
        BRANCH = 'master'
        SONAR_TOKEN = credentials('sonar_token')
    }

    stages {
        stage('Checkout') {
            steps {
                
                git branch: "${BRANCH}", url: "${GIT_REPO}"
            }
        }


        stage('Maven compile and test') {
            steps {
                script {
                    sh 'mvn compile'
                     sh 'mvn test'  
                }
            }
        }
        stage('Maven SonarQube') {
            steps {
          
                script {
                     sh 'mvn sonar:sonar -Dsonar.token=$SONAR_TOKEN'
                }
            }
        }
         stage('Nexus') {
            steps {
               
                script {
                     sh 'mvn deploy -DskipTests '
                }
            }
        }
        stage('Building image') {
            steps {
                     sh 'docker build -t kaddem/devopsproject:1.0.0 .'
            }
        }
         stage('Deploy image') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dock_credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh "docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD}"
                    }
                    sh 'docker push skandernasri1/devopsproject:1.0.0 '
                }
            }
        }
        stage('Docker compose') {
            steps {
                script {
                     sh 'docker compose down'
                    sh 'docker compose up -d'
                   
                }
            }
        }

        stage('Grafana') {
            steps {
                script {
                    // sh 'docker compose down'
                    sh 'docker start grafana '
                   
                }
            }
        }

      

     
    }

   post {
        success {
            echo 'Build, test, and SonarQube analysis completed successfully.'
            emailext(
                to: 'skan.nasri@gmail.com',
                subject: "Pipeline Success - DevOps Project",
                body: """
                <html>
                    <body style="font-family: Arial, sans-serif;">
                        <h2 style="color: green;">Pipeline Succeeded!</h2>
                        <p>Good news! The pipeline for the DevOps Project completed <strong>successfully</strong>.</p>
                        <p>All stages passed without errors. The project is running.</p>
                    </body>
                </html>
                """,
                mimeType: 'text/html'
            )
        }
        
        failure {
            echo 'Build, test, or analysis failed.'
            emailext(
                to: 'skan.nasri@gmail.com',
                subject: "Pipeline Failure - DevOps Project",
                body: """
                <html>
                    <body style="font-family: Arial, sans-serif;">
                        <h2 style="color: red;">Pipeline Failed</h2>
                        <p>Unfortunately, the pipeline encountered an error during the build process.</p>
                        <p>Please check the Jenkins console output for more details and troubleshoot the issue.</p>
                    </body>
                </html>
                """,
                mimeType: 'text/html'
            )
        }
    }
}
