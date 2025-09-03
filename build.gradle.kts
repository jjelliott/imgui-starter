plugins {
    id("java-library")
    id("maven-publish")
}

version = "0.1"
group = "io.github.jjelliott.imgui"

repositories {
    mavenCentral()
}

dependencies {
    api("io.github.spair:imgui-java-app:1.89.0")

    implementation("org.lwjgl:lwjgl-stb")
    runtimeOnly("org.lwjgl:lwjgl-stb:3.3.4:natives-windows")
    runtimeOnly("org.lwjgl:lwjgl-stb:3.3.4:natives-macos")
    runtimeOnly("org.lwjgl:lwjgl-stb:3.3.4:natives-macos-arm64")
    runtimeOnly("org.lwjgl:lwjgl-stb:3.3.4:natives-linux")

    implementation("org.lwjgl:lwjgl-tinyfd")
    runtimeOnly("org.lwjgl:lwjgl-tinyfd:3.3.4:natives-windows")
    runtimeOnly("org.lwjgl:lwjgl-tinyfd:3.3.4:natives-macos")
    runtimeOnly("org.lwjgl:lwjgl-tinyfd:3.3.4:natives-macos-arm64")
    runtimeOnly("org.lwjgl:lwjgl-tinyfd:3.3.4:natives-linux")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("gpr") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set(project.name)
                description.set("ImGui Java Library")
                url.set("https://github.com/jjelliott/imgui-starter")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/jjelliott/imgui-starter")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}