import com.alexallafi.convention.addUILayerDependencies
import com.alexallafi.convention.configureAndroidCompose
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureUiConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("runique.android.library.compose")
                pluginManager.apply("org.jetbrains.kotlin.plugin.compose")
            }

            dependencies {
                addUILayerDependencies(target)
            }
        }
    }
}