pipeline {
    agent any

     tools {
            jdk 'JDK 17'
            maven 'Maven_Home'
        }

    triggers {
        cron('H H * * 0')   // Every Sunday weekly
    }

    parameters {
        choice(
            name: 'ENV',
            choices: ['qa', 'stage', 'prod'],
            description: 'Select environment'
        )
        choice(
            name: 'PROFILE',
            choices: ['None', 'Smoke', 'Regression', 'API'],
            description: 'Select Maven profile to run (manual runs only)'
        )
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }
        stage('Maven Install') {
            steps {
                bat "mvn clean install -DskipTests"
            }
        }
        stage('PR Smoke Tests') {
            when { changeRequest() }
            steps {
                echo "Pull Request detected → Running Smoke"
                bat "mvn clean test -PSmoke -Denv=qa"
            }
        }

        stage('Scheduled Regression Run') {
            when { triggeredBy 'TimerTrigger' }
            steps {
                echo "Scheduled weekly regression → Running Regression on ENV=qa"
                bat "mvn clean test -PRegression -Denv=qa"
            }
        }

        stage('Manual Run Based on Parameters') {
            when {
                allOf {
                    triggeredBy 'UserIdCause'
                    expression { params.PROFILE != 'None' }
                }
            }
            steps {
                echo "Manual run → ENV=${params.ENV}, PROFILE=${params.PROFILE}"
                bat "mvn clean test -P${params.PROFILE} -Denv=${params.ENV}"
            }
        }
        }
       post {
         always {
             script {
                 echo "Archiving Extent Report & Screenshots"

                 def filesToArchive = []
                 if (fileExists('reports/automation.html')) {
                     filesToArchive.add('reports/automation.html')
                 } else {
                     echo "Report not found: reports/automation.html"
                 }

                 if (fileExists('test-output/screenshots')) {
                     filesToArchive.add('test-output/screenshots/*.png')
                 }
                 if (fileExists('logs')) {
                     filesToArchive.add('logs/*.log')
                 }

                 if (!filesToArchive.isEmpty()) {
                     archiveArtifacts artifacts: filesToArchive.join(','), fingerprint: true
                 } else {
                     echo "No artifacts found to archive."
                 }
             }
             if (fileExists('reports/automation.html')) {
                             publishHTML([
                                 allowMissing: false,
                                 alwaysLinkToLastBuild: true,
                                 keepAll: true,
                                 reportDir: 'reports',
                                 reportFiles: 'automation.html',
                                 reportName: 'Extent Report'
                             ])
                         } else {
                             echo "Cannot publish HTML report, file not found."
                         }
                     }

             cleanWs()
         }
     }

}
