pipeline {
    agent any

    stages {
        stage ('Compile Stage') {

            steps {
                withMaven(maven : 'maven_3_6_0') {
                    sh 'mvn clean compile'
                }
            }
        }

        stage ('Unit Testing Stage') {

            steps {
                withMaven(maven : 'maven_3_6_0') {
                    sh 'mvn test'
                }
            }
        }

        stage ('Integration Testing Stage') {

            steps {
                withMaven(maven : 'maven_3_6_0') {
                    sh 'mvn clean verify sonar:sonar -Ptestes-integracao -Dwebdriver.chrome.driver=/home/tsu/Downloads/chromedriver_linux64/chromedriver'
                }
            }
        }
        stage ('Deployment Stage') {
            steps {
                    sh 'cp /home/tsu/.jenkins/workspace/argentum-pipeline/target/argentum-web.war /home/tsu/Downloads/apache-tomcat-7.0.92/webapps'
                }
            }
        
    }
}
