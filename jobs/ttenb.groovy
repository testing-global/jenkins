stage('Create the initial tt-devops jenkins jobs') {
    jobDsl(
        targets: 'jobs/ttenbdsl.groovy',
        lookupStrategy: 'JENKINS_ROOT',
        removedJobAction: 'DISABLE',
        removedViewAction: 'DELETE',
        sandbox: true,
        )
        
}
