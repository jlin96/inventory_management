pipeline {
    agent any

    stages {
        stage('Build frontend') {
            steps {
                sh "echo Building frontend CI/CD test"
                sh "cd frontend && cd inventory-management && npm install && npm run build"
            }
        }
        stage('Sonar Cloud test'){
            steps{
                withSonarQubeEnv('SonarCloud') {
...
	npx sonar-scanner \
      -Dsonar.projectKey=jlin96_inventory-management-frontend \
      -Dsonar.projectName=inventory_management_frontend \
      -Dsonar.sources=src \
      -Dsonar.exclusions=**/__tests__/**,src/test/** \
      -Dsonar.javascript.lcov.reportPaths=coverage/lcov.info
...
}
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
        stage('Build backend'){
            steps{
                sh "echo this is the backend build"
                sh "cd inventory-management && mvn install && ls target/"
            }
        }
        stage('Test Backend'){
            steps{
                sh "cd inventory-management && mvn test"
            }
        }
        stage('Cloud backend test'){
            steps{
                withSonarQubeEnv('SonarCloud') {
                        sh '''
                            mvn sonar:sonar \
                            -Dsonar.projectKey=jlin96_inventory_management \
                            -Dsonar.projectName=inventory_management \
                            -Dsonar.java.binaries=target/classes \
                            -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                        '''
            }
            }
        }
        stage('Deploy Backend'){
            steps{
                script{
                  withAWS(region: 'us-east-1', credentials: 'AWS_CREDENTIALS'){
                        sh 'pwd'
                        sh "aws s3 cp inventory-management/target/inventory-management-0.0.1-SNAPSHOT.jar s3://team-6-backend-jenkins"
                        sh "echo 'aws elasticbeanstalk create-application-version --application-name team-6-app --version-label 0.0.3 --source-bundle S3Bucket=\"team-6-backend-jenkins\",S3Key=\"inventory-management-0.0.1-SNAPSHOT.jar\"'"
                        sh "echo 'aws elasticbeanstalk update-environment --environment-name Team-6-app-env-3 --version-label 0.0.3'"
                    }  
                }   
            }
        }
    }
}