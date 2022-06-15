package com.show.kInject.core.module

data class Definition<T>(val parameter : Parameter<T>)


typealias Parameter<T> = (ParametersHolder) -> T