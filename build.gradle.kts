import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("multiplatform") version "2.0.0"
	kotlin("plugin.serialization") version "2.0.0"
	id("com.vanniktech.maven.publish") version "0.29.0"
}

group = "io.github.magonxesp"
version = "0.0.0"

mavenPublishing {
	coordinates(group as String, "pixiv-scrapper", version as String)
	publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
	signAllPublications()

	pom {
		name = "Pixiv Scrapper"
		description = "Library for scrapping www.pixiv.net pages "
		url = "https://github.com/magonxesp/pixiv-scrapper"
		licenses {
			license {
				name = "The MIT License (MIT)"
				url = "https://mit-license.org/"
			}
		}
		developers {
			developer {
				id = "magonxesp"
				name = "MagonxESP"
				email = "magonxesp@gmail.com"
				url = "https://github.com/magonxesp"
			}
		}
		scm {
			connection = "scm:git:git://github.com/magonxesp/pixiv-scrapper.git"
			developerConnection = "scm:git:ssh://github.com/magonxesp/pixiv-scrapper.git"
			url = "https://github.com/magonxesp/pixiv-scrapper"
		}
	}
}

repositories {
	mavenCentral()
}

kotlin {
    jvm()

    sourceSets {
		val kotest_version: String by project
		val kotlin_serialization_version: String by project
		val kotlin_corroutines_version: String by project
		val kotlin_faker: String by project

        jvmMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlin_serialization_version")
				implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_corroutines_version")
            }
        }
		jvmTest {
			dependencies {
				implementation(kotlin("test"))
				implementation("io.kotest:kotest-runner-junit5:$kotest_version")
				implementation("io.github.serpro69:kotlin-faker:$kotlin_faker")
			}
		}
    }
}

tasks.withType<Test>().configureEach {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	compilerOptions {
		jvmTarget.set(JvmTarget.JVM_1_8)
	}
}
