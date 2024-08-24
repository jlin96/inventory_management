pipeline {
    agent any

    stages {
        stage('Build frontend') {
            steps {
                sh "echo Building frontend CI/CD test"
                sh "cd frontend && cd inventory-management && npm install"
            }
        }
        
    }
}