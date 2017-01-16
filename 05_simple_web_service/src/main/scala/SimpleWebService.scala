import scala.scalajs.js.JSApp
import scalatags.JsDom.all._
import org.scalajs.dom
import dom.document

package tutorial {
	object SimpleWebService extends JSApp {
		def main(): Unit = {
      import dom.ext._
      import scala.scalajs
        .concurrent
        .JSExecutionContext
        .Implicits.queue

      val url =
        "https://jsonplaceholder.typicode.com" +
          "/posts/1"

      Ajax.get(url).onSuccess{ case xhr =>
        dom.document.getElementById("app").appendChild(
          pre(xhr.responseText).render
        )
      }
		}
	}
}
