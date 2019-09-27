any {

    stage('Checkout git repo') 

    stage('Seed tt-devops repository jobs') {
        withFolderProperties {
            jobDsl(
                targets: 'jobs/sidrepodsl.groovy',
                additionalParameters: [
                    jenkinsBranch: JENKINS_BRANCH,
                ],
                lookupStrategy: 'JENKINS_ROOT',
                removedJobAction: 'DELETE',
                removedViewAction: 'DELETE',
                sandbox: true,
            )
        }
    }

