def baseUrl = "https://github.com/testing-global"
def environments = readFileFromWorkspace('environments.txt').replaceAll('#.*', '').split()

folder('tw-devops') {
    description 'for DevOps'
    properties {
        folderProperties {
            properties {
                stringProperty {
                    key 'Phase'
                    value 'value1'
                }
            }
        }
        
    }
    
}

folder('tw-devops/jenkins') {
    description 'Jenkins Configuration'
}

environments.each { environment ->
    listView("tw-devops/$environment") {
        recurse true
        jobs {
            regex "^[^/]*/$environment/apply\$"
        }
        columns {
            status()
            weather()
            name()
            lastSuccess()
            lastFailure()
            lastDuration()
        }
    }
}
