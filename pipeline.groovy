pipeline {
    agent {
        node {
            label 'master'
        }
    }
    tools{

        maven 'Apache Maven'
        jdk 'Java8'
    }

    stages {
        stage('Retrieving code from SCM') {
            steps {
                echo 'Connecting to github...'
                git branch: 'master', url: 'https://github.com/MayankChowdhary/EMSDemo.git'
            }
        }
        
        stage('Start Compiling') {
            steps {
                echo 'Maven Compile lifcycle Starting...'
                bat """
                mvn compile
                """
            }
        }
        stage('Starting Selected Lifecycle...') {
            steps {
                bat """
                echo 'Maven ${ goal } lifcycle Starting...'
                mvn ${ goal }
                """
            }
        }
    }
}
