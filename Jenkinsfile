pipeline {
    agent any
    tools {
        maven 'apache-maven-3.9.9'
        jdk 'JDK 17'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url : 'https://github.com/vimandi/jenkins.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
        stage('Run') {
            steps {
                sh 'nohup java -jar target/jenkins_project-0.0.1-SNAPSHOT.jar &'
            }
        }
    }
}
