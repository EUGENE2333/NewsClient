package com.example.newsapiclient.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPIServiceTest {
    private lateinit var service:NewsAPIService
    private lateinit var server : MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
           .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPIService::class.java)
    }

     fun enqueueMockResponse(
        fileName: String
    ){
         val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
         val mockResponse = MockResponse
             mockResponse.also{
             (source.readString(Charsets.UTF_8) )
         }
         //MockWebServer()
        server.enqueue(mockResponse)}


@Test
fun getTopHeadlines_sentRequests_receivedExpected(){
     runBlocking {
         enqueueMockResponse("newsresponse.json")
         val responseBody =  service.getTopHeadlines("us", page = 1).body()
         val requests = server.takeRequest()
         assertThat(responseBody).isNotNull()
         assertThat(requests.path).isEqualTo("/v2/top-headlines?country=us&page=1&apiKey=e5061d6eeb8245e7801b89fabf6ad14a")
     }
}
@Test
    fun getTopHeadlines_receivedResponse_correctPageSize(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody =  service.getTopHeadlines("us", page = 1).body()
            val articleList = responseBody!!.articles
            assertThat(articleList.size).isEqualTo(20)
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_currentContent(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody =  service.getTopHeadlines("us", page = 1).body()
            val articleList = responseBody!!.articles
            val article = articleList[0]
            assertThat(article.author).isEqualTo("shay Mulei")
            assertThat(article.url).isEqualTo("https://www.cbssports.com/nfl/news/stefon-diggs-outburst-bills-wr-sean-mcdermott-address-his-yelling-on-sideline-storming-out-of-locker-room/")
        }
    }




    @After
    fun tearDown() {
      server.shutdown()
    }




}

