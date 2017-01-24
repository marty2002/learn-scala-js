package tutorial

import scala.scalajs.js.JSApp
import org.scalajs.dom
import dom.document
import japgolly.scalajs.react.{BackendScope, ReactComponentB, ReactEventI, Callback, ReactDOM}
import japgolly.scalajs.react.vdom.prefix_<^._

object SearchReactApp extends JSApp {

  case class State (key: String)

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

  class FruitsBackend($: BackendScope[Unit, State]) {

    def onKeySearchChange(e: ReactEventI): Callback = {
      val newValue = e.target.value
      $.modState(_.copy(key = newValue))
    }

    def render(s: State) =
      <.div(
        <.input(
          ^.`type`      := "text",
          ^.placeholder := "Введите ваш любимый фрукт...",
          ^.onKeyUp     ==> onKeySearchChange
        ),
        <.ul(
          for {
            fruit <- listing if (fruit.toLowerCase().startsWith(s.key.toLowerCase()))
          } yield  {
            val (first, last) = fruit.splitAt(
              s.key.length
            )
            <.li(
              <.span(
                ^.backgroundColor := "yellow",
                first
              ),
              last
            )
          }
        )
      )
  }

  val SearchFriuts = ReactComponentB[Unit]("SearchFriuts")
    .initialState(State(key = ""))
    .renderBackend[FruitsBackend]
    .build

  def main(): Unit = {
    ReactDOM.render(SearchFriuts(), document.getElementById("app"))
  }
}
