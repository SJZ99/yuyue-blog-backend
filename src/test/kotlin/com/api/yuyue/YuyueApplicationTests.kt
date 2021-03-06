package com.api.yuyue

import com.api.yuyue.model.entity.EntityLanguage
import com.api.yuyue.model.entity.EntityLiterature
import com.api.yuyue.service.ServiceProgram
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import java.net.http.HttpClient
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDate
import javax.net.ssl.SSLContext

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [YuyueApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class YuyueApplicationTests {

//	@Autowired
//	lateinit var testRestTemplate: TestRestTemplate
//
//	@Autowired
//	lateinit var programService : ServiceProgram
//
//	@Test
//	fun getImg() {
//		val result = testRestTemplate.getForEntity("/program/img/city.jpg", String::class.java)
//
//		println(result.body)
//		assertEquals(HttpStatus.OK, result.statusCode)
//		assertNotNull(result.body)
//	}

//	@Test
//	fun addImage() {
//		val img = Files.readAllBytes(Path.of("C://Users//9987j//Desktop//yuyue-blog//src//assets//cyberpunk//city.jpg"))
//
//		val header : HttpHeaders = HttpHeaders()
//		header.contentType = MediaType.MULTIPART_FORM_DATA
//
//
//		val request : HttpEntity<ByteArray>
//				= HttpEntity<ByteArray>(img, header)
//
//		val result = testRestTemplate.postForEntity("/program/add_img", request, ByteArray::class.java)
//
//		assertEquals(HttpStatus.OK, result.statusCode)
//		assertNotNull(result.body)
//	}

//	@Test
//	fun insertProgramEntityToDatabase() {
//		val entity : EntityLiterature = EntityLiterature(1, "Java", "???????????????",
//													"??????!?????????????????????", "????????????????????????:))))))))))))))))))?????????",
//													LocalDate.now(),
//													"city.jpg"
//													)
//		val request : HttpEntity<EntityLiterature>
//				= HttpEntity<EntityLiterature>(entity)
//
//		val result = testRestTemplate.postForEntity("/literature/add_article", request, EntityLiterature::class.java)
//
//		assertEquals(HttpStatus.OK, result.statusCode)
//	}

//	@Test
//	fun postLanguage() {
//		val request : HttpEntity<EntityLanguage>
//				= HttpEntity<EntityLanguage>(EntityLanguage("Java", "The father of Kotlin"))
//
//		val result = testRestTemplate.postForEntity("/add", request, EntityLanguage::class.java)
//
//		assertEquals(HttpStatus.OK, result.statusCode)
//	}
//
//	@Test
//	fun getAllLanguage() {
//		val result = testRestTemplate.getForEntity("/language", String::class.java)
//
//		assertEquals(HttpStatus.OK, result.statusCode)
//		assertNotNull(result?.body)
//	}
}
