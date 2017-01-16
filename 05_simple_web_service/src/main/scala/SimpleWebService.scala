import scala.scalajs.js.JSApp
import scalatags.JsDom.all._
import org.scalajs.dom
import dom.document
import scalajs.js

package tutorial {

  import scala.scalajs.js

  object SimpleWebService extends JSApp {
		def main(): Unit = {
      import dom.ext._
      import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue // для использования асинхронных запросов

      val url =
        "https://jsonplaceholder.typicode.com" +
          "/posts/1"

      Ajax.get(url).onSuccess{ case xhr =>
        /*dom.document.getElementById("app").appendChild(
          pre(
            js.JSON.stringify(
              js.JSON.parse(xhr.responseText),
              space=4
            )
          ).render
        )*/
        if (xhr.status == 200) {
          val json = js.JSON.parse(xhr.responseText)
          val title = json.title.toString
          val body = json.body.toString
          dom.document.getElementById("app").appendChild(
              div(
                h1(title).render,
                div(body).render,
                pre(js.JSON.stringify( json, space=4)).render
              ).render
          ).render
        }
      }
		}
	}
}
