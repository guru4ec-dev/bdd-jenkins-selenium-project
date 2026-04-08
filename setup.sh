#!/bin/bash

# Update package index
sudo apt-get update

# Install Java
sudo apt-get install -y openjdk-11-jdk

# Install Maven
sudo apt-get install -y maven

# Install Google Chrome
wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | sudo apt-key add -
sudo sh -c 'echo "deb [arch=amd64] https://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list'
sudo apt-get update
sudo apt-get install -y google-chrome-stable

# Install ChromeDriver
CHROME_DRIVER_VERSION=`curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE`
wget https://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip
unzip chromedriver_linux64.zip
sudo mv chromedriver /usr/local/bin/

# Build the project
mvn clean install

# Run tests
mvn test

# Configure Jenkins (this part will depend on your Jenkins setup)
# This example assumes Jenkins will be setup via CLI or using a script
# Add additional Jenkins configuration commands here

echo "Setup complete!"