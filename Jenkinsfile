pipeline {

    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh 'java -version'
                sh 'mvn -version'
                sh 'mvn clean test -DsuiteXmlFile=testng.xml -Dplatform=Android'
            }
        }
    }

    post {
        always {

            // Archive JSON Report
            archiveArtifacts artifacts: 'target/json-report.json', fingerprint: true

            // Publish Extent HTML Report
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'reports',
                reportFiles: 'ExtentReport.html',
                reportName: 'Deferred Link Automation Report'
            ])
        }
    }
}