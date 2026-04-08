pipeline {
    agent any
    
    environment {
        JAVA_HOME = 'C:\\Java'
        MAVEN_HOME = 'C:\\Maven'
        PATH = "${MAVEN_HOME}\\bin;${JAVA_HOME}\\bin;${PATH}"
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                echo 'Building project...'
                bat 'C:\\Maven\\bin\\mvn clean install'
            }
        }
        
        stage('Run Selenium Tests') {
            steps {
                echo 'Running Selenium tests...'
                bat 'C:\\Maven\\bin\\mvn test'
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