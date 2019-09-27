def baseUrl = "https://github.com/testing-global"
def repositories = "repositories-${JENKINS_SERVER == 'repo-prod' ? 'repo-prod' : 'repo-test'}.txt"
repositories = readFileFromWorkspace(repositories).replaceAll('#.*', '').split()

repositories.each { repository ->
    job = pipelineJob("tt-devops/jenkins/repositories/$repository") {
        
        definition {
            cpsScmFlowDefinition {
                scm {
                    gitSCM {
                        userRemoteConfigs {
                            userRemoteConfig {
                                url "$baseUrl/${repository}.git"
                                credentialsId 'github-checkout'
                                name 'origin'
                                refspec ''
                            }
                        }
                        branches {
                            branchSpec {
                                name jenkinsBranch
                            }
                        }
                        doGenerateSubmoduleConfigurations false
                        browser {
                            githubWeb {
                                repoUrl "$baseUrl/$repository"
                            }
                        }
                        gitTool ''
                    }
                }
                scriptPath 'DevOps1.groovy'
            }
        }
        environmentVariables {
            env 'PHASE', 'seed'
        }
    }
    
}
