# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# prevent models from being obfuscated
-keep class com.codemockup.ecommercecommunity.data.models**{ *; }
-keepclassmembers class com.codemockup.ecommercecommunity.data.model.** {
    public static <fields>;
}

# Sentry
-keep class io.sentry.** { *; }
-keep class io.sentry.android.** { *; }
-keep class io.sentry.android.core.** { *; }

# Talsec
-keep class com.aheaditec.** { *; }
-keepclassmembers class com.aheaditec.** { *; }