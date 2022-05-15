package com.github.edasich.test.common.data.remote

import com.google.gson.Gson
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

fun readJsonFileWithNoNewLineOrWhiteSpace(
    classLoader: ClassLoader,
    fileName: String
): String? {
    return readJsonFile(classLoader = classLoader, fileName = fileName)
        ?.replace("\n", "")
        ?.replace(" ", "")
}

fun readJsonFile(
    classLoader: ClassLoader,
    fileName: String
): String? {
    val inputStream =
        classLoader.getResourceAsStream("api-response/$fileName")
    val source =
        inputStream?.let {
            inputStream.source().buffer()
        }
    return source
        ?.readString(charset = StandardCharsets.UTF_8)
}

fun <T> readJsonFile(
    classLoader: ClassLoader,
    fileName: String,
    classModel: Class<T>
): T? {
    return readJsonFile(
        classLoader = classLoader,
        fileName = fileName
    )?.let {
        Gson().fromJson(it,classModel)
    }
}