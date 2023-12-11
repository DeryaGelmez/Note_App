plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
}

android {
    namespace = "com.example.thinkpad"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.thinkpad"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

   /* implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")*/
    //implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")


    /*Room, Android uygulamalarında yerel veritabanları ile çalışmayı kolaylaştıran bir kütüphanedir.
    SQLite veritabanını kullanır ve bu veritabanına erişim ve sorgu işlemlerini basitleştiren bir
    ORM (Object-Relational Mapping) çerçevesi sunar. Bu kütüphane, veritabanı işlemleri için daha
    güvenli ve daha etkili bir yol sağlar. */

    var room_version = "2.4.2"

    implementation ("androidx.room:room-ktx:$room_version")
    //>room-ktx bağımlılığı Room kütüphanesinin temel bileşenlerini içerir.
    implementation ("androidx.room:room-runtime:$room_version")
    //>room-runtime bağımlılığı Room kütüphanesinin temel bileşenlerini içerir.
    kapt ("androidx.room:room-compiler:$room_version")
    //room-compiler Room'un derleme sürecinde kullanılan bir bileşendir.

    /*Aşağıdaki iki bileşen viewModel ve diğer bileşenleri kullanırken kotlin dilinde daha temiz
    ve kısa kod yazmamızı sağlar.*/

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")

    /*Kotlin Coroutines, asenkron ve eşzamansız programlama için kullanılan bir dil özelliğidir.
    Android uygulamalarında genellikle ağ çağrıları veya veritabanı işlemleri gibi uzun süreli
    operasyonları işlemek için kullanılır. */
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
}