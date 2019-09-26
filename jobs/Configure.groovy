node('master') {

    
    stage('Configure testing-global organization') {
        jobDsl(
            targets: 'jobs/ConfigureJobDsl.groovy',
            lookupStrategy: 'JENKINS_ROOT',
            removedJobAction: 'DELETE',
            removedViewAction: 'DELETE',
            sandbox: true,
        )
    }

}
