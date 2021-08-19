pipeline {
    agent any {
        stage('Build') {
            steps {
                withMaven {
                    sh 'mvn package'
                }
            }
        stage('Deploy') {
            steps {
                sh 'cp -R target/*.war /opt/tomcat/webapps/'
            }
        }
    }
}