
def call() {
  // evaluate the body block, and collect configuration into the object
  sh """
     #!/bin/bash
     ./gradlew -Dorg.ajoberstar.grgit.auth.username='ben-adazza'\
               -Dorg.ajoberstar.grgit.auth.password='${env.GITHUB_TOKEN}'\
               -PscalaVersion='2.12'\
                final build publish publishDeb
     """

  sh """
     #!/bin/bash
     ./gradlew -Dorg.ajoberstar.grgit.auth.username='ben-adazza'\
               -Dorg.ajoberstar.grgit.auth.password='${env.GITHUB_TOKEN}'\
               -PscalaVersion='2.11'\
                final build publish -Prelease.useLastTag=true
     """
}
