## What is Kinit?
a lightweight injection framework for Android developer using Kotlin 

This framework written in Kotlin

# Dependencies
Project build.gradle
```
allprojects {
    repositories {
       ........
        maven {
            setUrl("https://jitpack.io")
        }
      
    }
}

```
App build.gradle
```
//Core
api("com.github.ShowMeThe.kinit:kinit_core:v0.03")
//Lifecycle-ktx
api("com.github.ShowMeThe.kinit:kinit_lifecycle:v0.03")
```
# Quickstart
A easy Example as following:
We create a Main.kt
```
data class Main(var data:String)

```
Applciation.kt
```
 override fun onCreate() {
        super.onCreate()
        startInit {
            enableLog()
            appContext(this@MyApp)
            single { 
               // initialize something
               Main("123") 
            }
        }
    }
        
```
Or just write it in an Activity
```

 val module = lifeModule {
            single(MainViewModel::class.java.name){ this@MainActivity }
            single(Main::class.java.name) { Main("4444") }
        }

        //module add Key and module value
        startInit {
            module(viewModel,module)
        }


```
:collision::collision: **If single contains an Activity or a Fragment,suggest using lifeModule, otherwise,it will cause Memory Leak.**

MainViewModel.kt
```
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository by lazy {  MainRepository(lifeOwnerOrNull(this))}

    private val main :Main by inject(this)

    fun get() = repository

    fun getMain() = repository

}


```
MainRepository.kt
```
class MainRepository(owner: LifecycleOwner?) : BaseRepository(owner) {
}

```
Then,it inject the LifecycleOwner in MainRepository constructor according to different `ViewModel`

