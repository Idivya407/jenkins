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
        powershell '''
        Start-Process -FilePath "java" -ArgumentList "-jar", "target/jenkins_project-0.0.1-SNAPSHOT.jar" -RedirectStandardOutput "app.log" -RedirectStandardError "error.log" -NoNewWindow
        Start-Sleep -Seconds 30

        try {
            $response = Invoke-WebRequest -Uri http://localhost:8082 -UseBasicParsing -TimeoutSec 10
            if ($response.StatusCode -eq 200) {
                Write-Host "✅ Spring Boot app is UP on port 8082"
            } else {
                Write-Error "❌ App returned status: $($response.StatusCode)"
                exit 1
            }
        } catch {
            Write-Error "❌ Spring Boot app is not reachable on http://localhost:8082"
            exit 1
        }
        '''
    }
}

        
        
    }
}
