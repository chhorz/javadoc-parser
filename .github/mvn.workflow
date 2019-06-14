workflow "Maven Build and Test" {
  resolves = ["Test"]
  on = "push"
}

action "Build" {
  uses = "LucaFeger/action-maven-cli@765e218a50f02a12a7596dc9e7321fc385888a27"
  runs = "mvn clean install -DskipTests"
}

action "Test" {
  uses = "LucaFeger/action-maven-cli@765e218a50f02a12a7596dc9e7321fc385888a27"
  needs = ["Build"]
  runs = "mvn clean test"
}
