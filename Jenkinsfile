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
            }
        }
    }
}
