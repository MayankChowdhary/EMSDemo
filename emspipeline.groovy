pipeline {
    agent {
        node {
            label 'master'
        }
    }

    stages {
        stage('This is first pipeline stage') {
            steps {
                echo 'Hello World'
            }
        }
        
        stage('This is second pipeline stage') {
            steps {
                echo 'Hello Second stage'
            }
        }
    }
}
