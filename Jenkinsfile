pipeline {
    agent any

    tools {
        maven 'apache-maven-3.9.9'
        jdk 'JDK 17'
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
                    echo "🚀 Starting Spring Boot app on port 8082..."

                    // Start the app in background using start /B and redirect output
                    bat 'start /B java -jar target\\jenkins_project-0.0.1-SNAPSHOT.jar > spring.log 2>&1'

                    echo "⏳ Waiting for app to become reachable at http://localhost:8082"

                    // Retry loop (max 10 attempts, 3 seconds apart = 30 seconds total)
                    def attempts = 0
                    def appUp = false
                    while (attempts < 10) {
                        def status = powershell(
                            returnStatus: true,
                            script: '''
                                try {
                                    $res = Invoke-WebRequest -Uri http://localhost:8082 -UseBasicParsing -TimeoutSec 2
                                    if ($res.StatusCode -eq 200) { exit 0 } else { exit 1 }
                                } catch { exit 1 }
                            '''
                        )
                        if (status == 0) {
                            echo "✅ App is UP on port 8082"
                            appUp = true
                            break
                        } else {
                            echo "🔄 App not ready, retrying in 3s..."
                            sleep time: 3, unit: 'SECONDS'
                        }
                        attempts++
                    }

                    if (!appUp) {
                        error("❌ App did not start in time on port 8082")
                    }
                }
            }
        }
    }
}
