node('master') {
	stage('Checkout git repo') {
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], userRemoteConfigs: [[url: 'https://github.com/testing-global/jenkins.git']]])
    }
    
    stage('Create the initial tt-devops jenkins jobs') {
        jobDsl(
            targets: 'jobs/ttenbdsl.groovy',
            lookupStrategy: 'JENKINS_ROOT',
            removedJobAction: 'DELETE',
            removedViewAction: 'DELETE',
            sandbox: true,
        )
    }

}