import hudson.util.Secret

def jenkinsUrl = "https://github.com/testing-global/jenkins"



pipelineJob('tt-devops/jenkins/configure') {
    description 'Configure tt-devops organization'
    properties {
        authorizeProjectProperty {
            strategy {
                triggeringUsersAuthorizationStrategy()
            }
        }
    }
    definition {
        cpsScmFlowDefinition {
            scm {
                gitSCM {
                    userRemoteConfigs {
                        userRemoteConfig {
                            url "${jenkinsUrl}.git"
                            credentialsId 'github-checkout'
                            name 'origin'
                            refspec ''
                        }
                    }
                    
                    doGenerateSubmoduleConfigurations false
                    browser {
                        githubWeb {
                            repoUrl jenkinsUrl
                        }
                    }
                    gitTool ''
                }
            }
            scriptPath 'jobs/Configure.groovy'
        }
    }
}

pipelineJob('tt-devops/jenkins/sidrepo') {
    description 'Sid tt-devops repo'
    properties {
        authorizeProjectProperty {
            strategy {
                triggeringUsersAuthorizationStrategy()
            }
        }
        pipelineTriggers {
            triggers {
                upstream {
                    upstreamProjects 'tt-devops/jenkins/configure'
                    threshold 'SUCCESS'
                }
            }
        }
    }
    definition {
        cpsScmFlowDefinition {
            scm {
                gitSCM {
                    userRemoteConfigs {
                        userRemoteConfig {
                            url "${jenkinsUrl}.git"
                            credentialsId 'github-checkout'
                            name 'origin'
                            refspec ''
                        }
                    }
                    
                    doGenerateSubmoduleConfigurations false
                    browser {
                        githubWeb {
                            repoUrl jenkinsUrl
                        }
                    }
                    gitTool ''
                }
            }
            scriptPath 'jobs/sidrepo.groovy'
        }
    }
}
