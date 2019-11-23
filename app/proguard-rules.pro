# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/instagram/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#-dontwarn retrofit2.**
#-keep class retrofit2.** { *; }
#-keepattributes Signature
#-keepattributes Exceptions
#-keepclasseswithmembers class * {
#    @retrofit2.http.* <methods>;
#}
#-keep class robot.Main { *; }
#-keepclassmembers class robot.Main {
#public ();
#}
-keep class me.instagram.robot.Main
-keepclasseswithmembers class me.instagram.robot.retrofit.** { *; }
#-keepclasseswithmembers class org.apache.commons.lang3.** { *; }

-keepclasseswithmembers class com.birbit.android.jobqueue.** {*;}

-keep class de.greenrobot.event.** {*;}
-keepclassmembers class me.instagram.robot.Main {
    public void onEvent*(**);
    void onEvent*(**);
}
