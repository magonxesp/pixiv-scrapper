package io.github.magonxesp.pixivscrapper

fun isPixivUrl(url: String) = Regex("^.*\\.?pixiv\\.net.*$").matches(url)
