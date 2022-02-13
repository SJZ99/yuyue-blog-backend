package com.api.yuyue.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.isReadable

@Service
class ServiceImage {

    companion object {
//        @Value("multipartFile.image.root")
        private val path : String = "C://Users//9987j//Desktop//yuyue-upload//"
        private val root : Path = Path.of(path)
    }

    private fun generateHashName(img : MultipartFile) : String {
        return img.hashCode().toString()
    }

    fun saveImg(subfolder : String, img : MultipartFile) : String? {
        if(img.isEmpty) { return null }
        val fileName = generateHashName(img)
        val originName = img.name
        val suffix = if(originName.contains("."))
                            originName.substring(originName.lastIndexOf(".") + 1)
                     else
                            "jpg"

        val completePath = "$subfolder//$fileName.$suffix"
        Files.write(root.resolve(completePath), img.bytes)
        return "$fileName.$suffix"
    }

    fun getImg(subfolder : String, fileName : String) : ByteArray {
        if(fileName.isEmpty()) {
            return byteArrayOf()
        }

        val completePath = root.resolve("$subfolder//$fileName")

        return if (completePath.exists() && completePath.isReadable()) {
            Files.readAllBytes(completePath)
        } else {
            byteArrayOf()
        }
    }
}