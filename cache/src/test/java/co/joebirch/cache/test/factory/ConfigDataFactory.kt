package co.joebirch.cache.test.factory

import co.joebirch.cache.model.Config

object ConfigDataFactory {

    fun makeCachedConfig(): Config {
        return Config(DataFactory.randomInt(), DataFactory.randomLong())
    }

}