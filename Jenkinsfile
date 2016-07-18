echo 'XChange Jenkinsfile...'

node {
  git url: 'https://github.com/jeremy-prater/XChange.git'
  def mvnHome = tool 'M3'
  sh "${mvnHome}/bin/mvn clean test"
}