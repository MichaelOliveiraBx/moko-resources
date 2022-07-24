/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.gradle.generator

import java.util.Locale as JvmLocale

sealed interface LanguageType {

    val androidResourcesDir: String
    val appleResourcesDir: String
    val jsResourcesSuffix: String
    val jvmResourcesSuffix: String

    object Base : LanguageType {
        override val androidResourcesDir: String = "values"
        override val appleResourcesDir: String = "Base.lproj"
        override val jsResourcesSuffix: String = ""
        override val jvmResourcesSuffix: String = ""
    }

    class Locale(languageTag: String) : LanguageType {

        private val locale: JvmLocale = JvmLocale.forLanguageTag(languageTag)

        fun toBcpString(): String = locale.toLanguageTag()
        private fun toLocaleString(): String = locale.toString()

        override val androidResourcesDir: String = buildString {
            append("values")
            append("-")
            append(locale.language)
            if (locale.country.isNotBlank()) {
                append("-r")
                append(locale.country)
            }
        }

        override val appleResourcesDir: String = "${toBcpString()}.lproj"
        override val jsResourcesSuffix: String = "_${toBcpString()}"

        // JVM ResourceBundle uses locale format, eg `en_US`, instead of BCP format
        // like `en-US`.
        override val jvmResourcesSuffix: String = "_${toLocaleString()}"

        /**
         * Throw an error here so that we safeguard ourselves from implcitly calling `Local.toString`.
         * You should always use the more explicit methods defined above.
         */
        override fun toString(): String = TODO("Use toLocaleString or toBcpString instead!")

        override fun hashCode(): Int = locale.hashCode()
        override fun equals(other: Any?): Boolean {
            return other is Locale && other.locale == locale
        }
    }

    companion object {

        private const val BASE = "base"

        fun fromFileName(fileName: String): LanguageType = when (fileName) {
            BASE -> Base
            else -> Locale(fileName.replace("-r", "-"))
        }
    }
}
