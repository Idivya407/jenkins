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
        bat 'start "" java -jar target/jenkins_project-0.0.1-SNAPSHOT.jar'
        powershell 'Start-Sleep -Seconds 30'
        powershell '''
        try {
            $response = Invoke-WebRequest -Uri http://localhost:9090 -UseBasicParsing -TimeoutSec 10
            if ($response.StatusCode -eq 200) {
                Write-Host "✅ Spring Boot app is UP on port 9090"
            } else {
                Write-Error "❌ Spring Boot app returned status: $($response.StatusCode)"
                exit 1
            }
        } catch {
            Write-Error "❌ Spring Boot app is not reachable on http://localhost:9090"
            exit 1
        }
        '''
    }
}

}

        
        
        
        
    }
}
