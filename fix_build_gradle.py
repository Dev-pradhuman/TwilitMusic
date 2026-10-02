with open("shared/build.gradle.kts", "r") as f:
    content = f.read()

if "desktopTest.dependencies" not in content:
    replacement = """
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.vlcj)
            implementation(libs.kotlinx.coroutines.swing)
        }
        val desktopTest by getting {
            dependencies {
                implementation(compose.desktop.uiTestJUnit4)
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutines.test)
                implementation(kotlin("test-junit"))
            }
        }
"""
    content = content.replace("""        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.vlcj)
            implementation(libs.kotlinx.coroutines.swing)
        }""", replacement)
    
    with open("shared/build.gradle.kts", "w") as f:
        f.write(content)
