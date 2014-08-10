package LinkChecker

import scala.concurrent.Future
import com.ning.http.client.AsyncHttpClient
import scala.concurrent.Promise
import java.util.concurrent.Executor

/**
 * Created by alex on 09/08/14.
 */

//given a URL, get method returns a future of the URL body.
object WebClient {

  private val client = new AsyncHttpClient

  case class BadStatus(status: Int) extends RuntimeException

  //get url which will return a future
  def get(url: String)(implicit exec: Executor): Future[String] = {

    //Returns a future but not a scala future so must construct a promise
    val f = client.prepareGet(url).execute()
    val p = Promise[String]()

    //Adding a listener will make getting URL non-blocking
    //In general, wrap synchronous things in futures to make them asynchronous
    f.addListener(new Runnable {
      def run = {
        val response = f.get
        if (response.getStatusCode / 100 < 4)
          p.success(response.getResponseBodyExcerpt(131072))
        else p.failure(BadStatus(response.getStatusCode))
      }
    }, exec)

    //return future of promise
    p.future
  }

  def shutdown(): Unit = client.close()

}

object WebClientTest extends App {

  import scala.concurrent.ExecutionContext.Implicits.global

  WebClient get "http://www.google.com/" map println foreach (_ => WebClient.shutdown())
}