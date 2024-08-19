plugins {
    alias(libs.plugins.runique.android.feature.ui)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.alexallafi.analytics.presentation"
}

dependencies {
    implementation(projects.analytics.domain)
}