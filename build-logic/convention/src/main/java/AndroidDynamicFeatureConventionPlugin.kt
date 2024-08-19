import com.alexallafi.convention.ExtensionType
import com.alexallafi.convention.addUILayerDependencies
import com.alexallafi.convention.configureAndroidCompose
import com.alexallafi.convention.configureBuildTypes
import com.alexallafi.convention.configureKotlinAndroid
import com.android.build.api.dsl.DynamicFeatureExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidDynamicFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.dynamic-feature")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<DynamicFeatureExtension>() {

                configureKotlinAndroid(this)
                configureAndroidCompose(this)

                configureBuildTypes(
                    commonExtension = this,
                    extensionType = ExtensionType.DYNAMIC_FEATURE
                )
            }

            dependencies {
                addUILayerDependencies(target)
                "testImplementation"(kotlin("test"))
            }
        }
    }
}