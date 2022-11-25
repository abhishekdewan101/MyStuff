pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "MyStuff"
include(":app")

include(":common:ux")

include(":core:models")
include(":core:data")
include(":core:di")
include(":core:datastore")
include(":core:navigation")
include(":core:network")
include(":core:utils")

include(":feature:explore")
include(":feature:landing")
include(":feature:di")
include(":feature:games")
