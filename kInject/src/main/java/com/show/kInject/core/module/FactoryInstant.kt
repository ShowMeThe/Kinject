package com.show.kInject.core.module

import kotlin.reflect.KClass

data class FactoryInstant<R>(val secondaryTypes: List<KClass<*>?>,val resultType: KClass<*>)