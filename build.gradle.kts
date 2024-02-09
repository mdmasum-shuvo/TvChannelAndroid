buildscript {
    val version_moshi  by rootProject.extra {"1.14.0"}
    val version_retrofit  by rootProject.extra { "2.9.0"}
    val version_hilt_viewmodel by rootProject.extra {"1.0.0-alpha03"}
    val version_retrofit_coroutines_adapt by rootProject.extra { "0.9.2"}
    val version_hilt_navigation by rootProject.extra { "1.1.0"}
    val version_room  by rootProject.extra { "2.6.1"}
    val coroutines_version  by rootProject.extra { "1.5.1"}
    //OR
    //extra.set("ktorVersion", "1.2.3")

}
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("com.android.library") version "8.1.0" apply false
}