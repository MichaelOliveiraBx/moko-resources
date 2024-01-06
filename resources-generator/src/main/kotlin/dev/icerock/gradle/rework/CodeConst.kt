/*
 * Copyright 2024 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.gradle.rework

import com.squareup.kotlinpoet.ClassName

internal object CodeConst {
    val resourceContainerName = ClassName("dev.icerock.moko.resources", "ResourceContainer")
    val stringResourceName = ClassName("dev.icerock.moko.resources", "StringResource")

    object Apple {
        val nsBundleName = ClassName("platform.Foundation", "NSBundle")
        val loadableBundleName = ClassName("dev.icerock.moko.resources.utils", "loadableBundle")
        const val resourcesBundlePropertyName = "bundle"
        const val containerBundlePropertyName = "nsBundle"
    }

    object Jvm {
        val classLoaderName = ClassName("java.lang", "ClassLoader")
        const val resourcesClassLoaderPropertyName = "resourcesClassLoader"
        const val localizationDir = "localization"
    }

    object Js {
        private const val internalPackage = "dev.icerock.moko.resources.internal"
        val supportedLocalesName = ClassName(internalPackage, "SupportedLocales")
        val supportedLocaleName = ClassName(internalPackage, "SupportedLocale")
        val loaderHolderName = ClassName(internalPackage, "RemoteJsStringLoaderHolder")

        val stringLoaderName = ClassName(
            "dev.icerock.moko.resources.provider", "RemoteJsStringLoader"
        )
        const val stringsLoaderPropertyName = "stringsLoader"

        const val fallbackFilePropertyName = "fallbackFileUrl"
        const val supportedLocalesPropertyName = "supportedLocales"
    }
}