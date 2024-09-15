package io.github.magonxesp.pixivscrapper

import java.io.File
import java.lang.Exception

class InvalidFileNameException(override val message: String?) : Exception(message) {
	companion object {
		fun forFile(file: File) = InvalidFileNameException("the file ${file.name} is not a pixiv image")
	}
}

class InvalidUrlException(override val message: String?) : Exception(message) {
	companion object {
		fun forUrl(url: String) = InvalidUrlException("The url $url is an invalid pixiv url")
	}
}

class ContentNotFoundException(override val message: String?) : Exception(message) {
	companion object {
		fun forUrl(url: String) = ContentNotFoundException("The pixiv illustrations data is not available for $url")
	}
}
