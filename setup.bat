@echo off
@echo Installing Java...
REM Java installation (update the URL to the latest version)
curl -L -o jdk.zip https://download.oracle.com/java/17/archive/jdk-17_windows-x64_bin.zip
mkdir C:\Java
tar -xf jdk.zip -C C:\Java --strip-components=1
setx JAVA_HOME "C:\Java"
setx PATH "%JAVA_HOME%\bin;%PATH%"

echo Installing Maven...
REM Maven installation (update the URL to the latest version)
curl -L -o maven.zip https://downloads.apache.org/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.zip
mkdir C:\Maven
tar -xf maven.zip -C C:\Maven --strip-components=1
setx MAVEN_HOME "C:\Maven"
setx PATH "%MAVEN_HOME%\bin;%PATH%"

echo Installing Chrome...
REM Install Google Chrome
curl -L -o chrome_installer.exe https://dl.google.com/chrome/install/latest/chrome_installer.exe
start /wait chrome_installer.exe /silent /install

echo Installing ChromeDriver...
REM ChromeDriver installation (update the version according to Chrome)
curl -L -o chromedriver.zip https://chromedriver.storage.googleapis.com/113.0.5672.24/chromedriver_win32.zip
mkdir C:\ChromeDriver
tar -xf chromedriver.zip -C C:\ChromeDriver
setx PATH "C:\ChromeDriver;%PATH%"

echo Building the project...
cd path\to\your\jenkins-selenium-project
mvn clean install

echo Running tests...
mvn test

echo Configuring Jenkins...
REM Jenkins configuration commands go here (update according to your Jenkins setup)