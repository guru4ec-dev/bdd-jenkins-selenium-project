pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                echo 'Building project...'
                bat 'mvn clean install'
            }
        }
        
        stage('Run Selenium Tests') {
            steps {
                echo 'Running Selenium tests...'
                bat 'mvn test'
            }
        }
        
        stage('Publish Results') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }
    }
    
    post {
        always {
            echo 'Cleaning up...'
            archiveArtifacts artifacts: 'target/**', allowEmptyArchive: true
        }
        failure {
            echo 'Tests failed!'
        }
        success {
            echo 'Tests passed successfully!'
        }
    }
}