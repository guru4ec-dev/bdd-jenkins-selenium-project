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
                bat "C:\\Maven\\bin\\mvn clean test -Dheadless=true -Dallure.results.directory=target/allure-results"

            }
        }
        parallel {
            stage('Chrome') {
                steps {
                    bat 'C:\\Maven\\bin\\mvn clean test -Dbrowser=chrome -Dheadless=true -Dallure.results.directory=target/allure-results'
                }
            }
            stage('Firefox') {
                steps {
                    bat 'C:\\Maven\\bin\\mvn clean test -Dbrowser=firefox -Dheadless=true -Dallure.results.directory=target/allure-results'
                }
            }
        }

        stage('Cucumber Report') {
            steps {
                    publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target',
                    reportFiles: 'cucumber-report.html',
                    reportName: 'Cucumber HTML Report'
                    ])
                }
        }

       stage('Allure Report') {
            steps {
                allure(
                    includeProperties: false,
                    results: [[path: 'target/allure-results']]
                )
            }
        }

        stage('Zip Allure Report') {
            steps {
                bat 'powershell Compress-Archive -Path allure-report -DestinationPath allure-report.zip'
            }
        }

        stage('Archive Report') {
            steps {
                archiveArtifacts artifacts: 'allure-report.zip', allowEmptyArchive: false
            }
        }

        stage('Check Allure CLI') {
                steps {
                    bat 'C:\\Allure\\allure-2.39.0\\bin\\allure.bat --version'
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
            emailext(
                subject: "Test Report [${currentBuild.result}] - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                    <html>
                    <body>
                        <h2>Jenkins Allure Test Report</h2>
                        <table border="1" cellpadding="5">
                            <tr><td><b>Job:</b></td><td>${env.JOB_NAME}</td></tr>
                            <tr><td><b>Build:</b></td><td>#${env.BUILD_NUMBER}</td></tr>
                            <tr><td><b>Status:</b></td><td>${currentBuild.result}</td></tr>
                            <tr><td><b>Duration:</b></td><td>${currentBuild.durationString}</td></tr>
                        </table>
                        <br/>
                        <p>Allure HTML report is attached as ZIP.</p>
                        <p>Extract and open <b>index.html</b> in a browser to view.</p>
                        <br/>
                        <a href="${env.BUILD_URL}allure">View Online Allure Report</a>
                    </body>
                    </html>
                """,
                mimeType: 'text/html',
                to: 'guru4ec@gmail.com',
                attachmentsPattern: 'allure-report.zip'
            )
        }
    }
}