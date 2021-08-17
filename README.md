## What is KInject?
a lightweight injection framework for Android developer using Kotlin 

This framework written in Kotlin 

The Lastest Version <img src="https://img.shields.io/badge/1.5.1--alpha-Versioin-green"/>

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
api("com.github.ShowMeThe:Kinject:${lastest version}")
```
# Quickstart
A easy Example as following:
If we have a Repository like this :  
MainRepository:
```
class MainRepository(owner: LifecycleOwner?) : BaseRepository(owner)

```
MainViewModel:  

```
class MainViewModel(application: Application) : AndroidViewModel(application) {


    val repository by lazy { MainRepository(getLifeOwner(this)) }


}

```
MainActivity:  
```
 initScope {
            module(viewModel, lifeModule {
                scopeLifeOwner(viewModel)
            })
        }

```

We can set the Activity LifeOwner into ViewModel，So we can get the Activity's LifeOwner in Viewmodel using method getLifeOwner.For more example see the example project



