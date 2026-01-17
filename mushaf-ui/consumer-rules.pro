# Consumer ProGuard rules for mushaf-ui
# These rules will be applied to consumers of this library

# Compose
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# Coil
-keep class coil.** { *; }
-dontwarn coil.**

# Keep UI classes for reflection
-keep class com.mushafimad.ui.** { *; }

# Keep Composable functions
-keep @androidx.compose.runtime.Composable class * {
    public <methods>;
}
