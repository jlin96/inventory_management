pipeline {
    agent any

    stages {
        stage('Build frontend') {
            steps {
                sh "echo Building frontend test"
                sh "cd inventory_management && cd frontend && cd inventory-management && npm install && npm run build"
            }
        }
        
    }
}