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
                git branch 'master', url: 'git@github.com:MayankChowdhary/EMSDemo.git'
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
        stage('Start Installing...') {
            steps {
                echo 'Maven Install lifcycle Starting...'
                bat """
                mvn install
                """
            }
        }
    }
}
