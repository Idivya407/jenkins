pipeline {
    agent any

      tools {
        maven 'apache-maven-3.9.9'
        jdk 'JDK 17'
    }

    environment {
        PORT = '8082'
        JAR = 'target/jenkins_project-0.0.1-SNAPSHOT.jar'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/vimandi/jenkins.git'

            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package'
            }
        }

        stage('Run') {
            steps {
                script {
                    echo "🚀 Starting Spring Boot app on port ${PORT}..."
                    bat "start /B java -jar ${JAR} --server.port=${PORT} > spring.log 2>&1"

                    def maxRetries = 10
                    def delay = 3
                    def success = false

                    for (int i = 0; i < maxRetries; i++) {
                        def status = powershell(
                            script: "(Invoke-WebRequest -Uri http://localhost:${PORT}/hello -UseBasicParsing).StatusCode",
                            returnStdout: true
                        ).trim()

                        if (status == '200') {
                            echo "✅ App is running and reachable!"
                            success = true
                            break
                        }

                        echo "🔍 App not ready (attempt ${i + 1}/${maxRetries}), retrying in ${delay}s..."
                        sleep time: delay, unit: 'SECONDS'
                    }

                    if (!success) {
                        error("❌ App did not start in time on port ${PORT}")
                    }
                }
            }
        }
    }
}
