pipeline {
    agent any

    stages {
        stage('Build frontend') {
            steps {
                sh "echo Building frontend CI/CD test"
                sh "cd frontend && cd inventory-management && npm install && npm run build"
            }
        }
        stage('Deploy Frontend'){
            steps {
                script{ 
                    try{
                        withAWS(region:'us-east-1', credentials: 'AWS_CREDENTIALS'){
                            sh "aws s3 sync frontend/inventory-management/dist s3://team-6-frontend-jenkins"
                        }
                    }catch(Exception e){
                        echo "${e}"
                        throw e
                    }
                }
            }
        }
        stage('Building backend'){
            steps{
                sh "cd inventory-management && mvn clean install && ls target/"
            }
        } 
    }
}