plugins {
    kotlin("multiplatform")
}

group = "dw"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

val kotlinxHtmlVersion = "0.9.1"
val vueBindingsVersion = "1.0-SNAPSHOT"

kotlin {
    jvmToolchain(11)
    js(IR) {
        browser()
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-html:${kotlinxHtmlVersion}")
                implementation("dw:kotlinx-html-vue-dsl:${vueBindingsVersion}")
                implementation("dw:kotlinx-html-vue-bindings:${vueBindingsVersion}")
            }
        }
    }
}
