def baseUrl = "https://github.com/testing-global"

any () {

    stage('Checkout git repo') {
        steps {
			checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, userRemoteConfigs: [[url: 'https://github.com/testing-global/jenkins.git']]])        	
        }
    }

	stage('Create the initial tt-devops jenkins jobs') {
    	jobDsl(
        	targets: 'jobs/ttenbdsl.groovy',
        	lookupStrategy: 'JENKINS_ROOT',
        	removedJobAction: 'DISABLE',
        	removedViewAction: 'DELETE',
        	sandbox: true,
        )
	}
}