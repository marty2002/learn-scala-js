import scala.scalajs.js.JSApp
import scalatags.JsDom.all._
import org.scalajs.dom
import dom.document

package tutorial {
	object ScalaTagsApp extends JSApp {
		def main(): Unit = {
      val text = input(`type`:="text", placeholder:="Введите имя...").render
      val listing = Seq(
        "Абрикос", "Авокадо", "Айва", "Алыча", "Ананас", "Апельсин", "Арбуз",
        "Банан",
        "Виноград", "Вишня",
        "Гранат", "Грейпфрут", "Груша",
        "Дуриан", "Дыня",
        "Земляника",
        "Индир",
        "Какао", "Карамбола", "Киви", "Кокос", "Кумкват",
        "Лайм", "Лимон", "Личи",
        "Манго", "Мандарин", "Маракуйя", "Марула",
        "Папайя", "Персик", "Помело",
        "Рамбутан",
        "Свити", "Слива",
        "Фейхоа", "Физалис", "Финик",
        "Хурма",
        "Черешня",
        "Яблоко"
      )
      def renderListings = ul(
        for {
          fruit <- listing if (fruit.toLowerCase().startsWith(text.value.toLowerCase()))
        } yield li(fruit)
      ).render

      val output = div(renderListings).render
      text.onkeyup = (e: dom.Event) => {
        output.innerHTML = ""
        output.appendChild(renderListings)
      }

      val form = div(
        text,
        output
      ).render
      dom.document.getElementById("app").appendChild(form)
		}
	}
}
