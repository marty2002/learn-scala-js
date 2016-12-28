import scala.scalajs.js.JSApp
import org.scalajs.dom
import org.scalajs.dom.document

package hw {
	object HelloWorldApp extends JSApp {
		def main(): Unit = {
      val textNode = document.createTextNode("HelloWorld")
      document.body.appendChild(textNode)
		}
	}
}
