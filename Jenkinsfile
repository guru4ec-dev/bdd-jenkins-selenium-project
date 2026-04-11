pipeline {
    agent any
    
    environment {
        JAVA_HOME = 'C:\\Java'
        MAVEN_HOME = 'C:\\Maven'
        PATH = "${MAVEN_HOME}\\bin;${JAVA_HOME}\\bin;${PATH}"
    }
    
    stages {

        stage('Setup Environment') {
            steps {
                echo 'Setting up Java and Maven...'
                bat '''
                    if not exist "C:\\Java\\bin\\java.exe" (
                        echo Installing Java...
                        curl -L -o jdk.zip https://download.oracle.com/java/17/archive/jdk-17_windows-x64_bin.zip
                        if exist "C:\\Java" rmdir /s /q C:\\Java
                        mkdir C:\\Java
                        tar -xf jdk.zip -C C:\\Java --strip-components=1
                    )
                    
                    if not exist "C:\\Maven\\bin\\mvn.cmd" (
                        echo Installing Maven...
                        curl -L -o maven.zip https://downloads.apache.org/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.zip
                        if exist "C:\\Maven" rmdir /s /q C:\\Maven
                        mkdir C:\\Maven
                        tar -xf maven.zip -C C:\\Maven --strip-components=1
                    )
                    
                    echo Verifying installations...
                    C:\\Java\\bin\\java -version
                    C:\\Maven\\bin\\mvn -version
                '''
            }
        }
        
        stage('Build') {
            steps {
                echo 'Building project...'
                bat 'C:\\Maven\\bin\\mvn clean install -DskipTests'
            }
        }
        
        stage('Run Selenium Tests') {
            steps {
                echo 'Running Selenium tests...'
                bat 'C:\\Maven\\bin\\mvn clean test -Dheadless=true'
            }
        }

        stage('Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
        
        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
                }
        }

    }  // ✅ THIS WAS MISSING (closing stages)

    post {
        always {
            echo 'Cleaning up...'
        }
        success {
            echo 'Tests Passed!'
        }
        failure {
            echo 'Tests Failed!'
        }
    }
}