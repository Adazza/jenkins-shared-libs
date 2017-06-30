
def call(name) {
  script {
    // Get latest tag version and chop the leading v off.
    def tagVersion = sh(script: 'git describe --abbrev=0 --tags', returnStdout: true).trim().substring(1)
    sh 'packer validate template.json'
    writeFile(file: "packer_args.json",
              text: "{\"name\": \"${name}\", \"version\": \"${tagVersion}\"}")
    sh """
       #!/bin/bash
       packer build -var-file packer_args.json template.json
       """
  }
}
