workflow "Maven Build" {
  resolves = ["mvn test"]
  on = "push"
}

action "mvn clean install" {
  uses = "LucaFeger/action-maven-cli@765e218a50f02a12a7596dc9e7321fc385888a27"
  runs = "mvn clean install -DskipTests"
}

action "mvn test" {
  uses = "LucaFeger/action-maven-cli@765e218a50f02a12a7596dc9e7321fc385888a27"
  runs = "mvn test"
  needs = ["mvn clean install"]
}
