pipeline {
	agent any
	tools {
		maven 'maven'
	}
	stages {
		stage ('Compile Stage') <-- THIS IS A STAGE
			{
			steps{
				bat 'mvn clean compile'	
			}	
		}
		stage ('Sonarqube deployment Stage') <-- THIS IS A STAGE
			{
			steps{
				bat 'mvn sonar:sonar'	
			}	
		}
	}	
}