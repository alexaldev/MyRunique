import com.alexallafi.convention.ExtensionType
import com.alexallafi.convention.configureBuildTypes
import com.alexallafi.convention.configureKotlinAndroid
import com.alexallafi.convention.libs
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension>() {
                defaultConfig {
                    applicationId = libs.findVersion("projectApplicationId").get().toString()
                    versionCode = libs.findVersion("projectVersionCode").get().toString().toInt()
                    versionName = libs.findVersion("projectVersionName").get().toString()
                    targetSdk = libs.findVersion("projectTargetSdkVersion").get().toString().toInt()
                }

                configureKotlinAndroid(this)

                configureBuildTypes(this, extensionType = ExtensionType.APPLICATION)
            }
        }
    }
}