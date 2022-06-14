package com.show.kInject.core.module

data class Definition<T>(val definition : (ParametersHolder)->T)
