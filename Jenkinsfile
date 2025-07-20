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
                    chcp 65001

                    Write-Host "⏳ Starting Spring Boot app in background..."

                    # Kill any previous process running on port 8082
                    $portInUse = Get-NetTCPConnection -LocalPort 8082 -ErrorAction SilentlyContinue
                    if ($portInUse) {
                        Write-Host "⚠️ Port 8082 is in use. Trying to stop previous process..."
                        Stop-Process -Id ($portInUse.OwningProcess) -Force
                        Start-Sleep -Seconds 5
                    }

                    # Start the Spring Boot app in the background
                    Start-Process -FilePath "java" `
                        -ArgumentList "-jar", "target/jenkins_project-0.0.1-SNAPSHOT.jar" `
                        -RedirectStandardOutput "spring.log" `
                        -RedirectStandardError "spring-error.log" `
                        -NoNewWindow

                    # Retry health check until app responds or timeout
                    $retries = 10
                    $success = $false

                    for ($i = 1; $i -le $retries; $i++) {
                        try {
