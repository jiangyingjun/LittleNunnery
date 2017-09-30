# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/jiangyingjun/Library/Android/sdk/tools/proguard/proguard-android.txt
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

-dontwarn
# 指定代码的压缩级别
-optimizationpasses 5
# 包明不混合大小写
-dontusemixedcaseclassnames
# 不去忽略非公共的库类
-dontskipnonpubliclibraryclasses
 # 优化不优化输入的类文件
-dontoptimize
 # 预校验
-dontpreverify
 # 混淆时是否记录日志
-verbose
 # 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
# 保护注解
-keepattributes *Annotation*

# 保持哪些类不被混淆
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

# 如果有引用v4包可以添加下面这行
-keep public class * extends android.support.v4.app.Fragment
-ignorewarnings

#greenDao
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties

#// If you do not use SQLCipher:
-dontwarn org.greenrobot.greendao.database.**
#// If you do not use Rx:
-dontwarn org.greenrobot.greendao.rx.**

#retrofit2  start
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
#retrofit2  end

#okhttp3.x start
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**
#okhttp3.x end

#Rxjava RxAndroid start
-dontwarn rx.*
-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
#Rxjava RxAndroid end

#beam start
#所有extends Presenter的类都不进行混淆
-keep public class * extends com.jude.beam.bijection.Presenter
#beam  end
#####混淆保护自己项目的部分代码以及引用的第三方jar包library  ---end

#####代码的特殊处理 start
#如果不想混淆 keep 掉
-keep class org.jsoup.**
-keep class org.apache.**{*; }
#所有实体类所在包
-keep class com.uzai.app.domain.**{ *; }
-keep class com.uzai.app.mvp.model.**{*; }
#所有自定义类所在包
-keep class com.uzai.app.view.**{ *; }
#####代码的特殊处理 end

#如果引用了v4或者v7包
-dontwarn android.support.**

#保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

#保持自定义控件类不被混淆
-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

#保持自定义控件类不被混淆
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

#保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

#保持 Serializable 不被混淆
-keepnames class * implements java.io.Serializable

#保持 Serializable 不被混淆并且enum 类也不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

#野狗身份认证
-keep class com.wilddog.client.**{*;}
-keep class com.wilddog.**{*;}
-keep class com.fasterxml.jackson.**{*;}
-keep class com.fasterxml.jackson.databind.**{*;}
-keep class com.fasterxml.jackson.core.**{*;}

