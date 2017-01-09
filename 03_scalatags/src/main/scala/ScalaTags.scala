import scala.scalajs.js.JSApp
import scalatags.JsDom.all._
import org.scalajs.dom
import dom.document

package tutorial {
	object ScalaTagsApp extends JSApp {
    case class Person(name: String, age: Int, mission: Mission)

    trait Mission
    object Mission extends Mission {
      case object Doctor extends Mission {
        override def toString: String = "врачом"
      }
      case object Spaceman extends Mission {
        override def toString: String = "космонавтом"
      }
      case object Scientist extends Mission {
        override def toString: String = "ученым"
      }

      case object Artist extends Mission {
        override def toString: String = "художником"
      }
    }

    def agesToString(age: Int): String = {
      var postfix: String = "";
      val rest = age % 10;
      if ((age > 4 && age < 21) || rest > 4 || rest == 0) {
        postfix = " лет";
      }
      else {
        if (rest == 1) postfix = " год";
        else postfix = " года";
      }
      return age.toString + " " + postfix;
    }

		def main(): Unit = {
      val persons = scala.collection.mutable.Buffer[Person]()

      val textInput = input(`type`:="text", placeholder:="Введите имя...").render
      val numberInput = input(`type`:="number", placeholder:="Введите возраст...").render
      val selectInput = select(
        option(value:="DOCTOR",     "врачом"),
        option(value:="SPACEMAN",   "космонавтом"),
        option(value:="SCIENTIST",  "ученым"),
        option(value:="ARTIST",     "художником")
      ).render

      var personsDiv = div().render

      var addBtn = button("Отправить письмо дедушке морозу!", onclick:= { () => {
       val name = textInput.value
       val mission = selectInput.value match {
         case "DOCTOR" => Mission.Doctor
         case "SPACEMAN" => Mission.Spaceman
         case "SCIENTIST" => Mission.Scientist
         case "ARTIST" => Mission.Artist
       }
       val age = numberInput.value.toInt

       persons.append(Person(name, age, mission))

       personsDiv.innerHTML = "";
       personsDiv.appendChild(persons.map{
         person => p(
           "Здравствуй, дедушка Мороз! Меня зовут " + person.name + ", мне " + agesToString(person.age) + ". Я мечтаю стать " + person.mission.toString()
         )
       }.render)
      }}).render

      val form = div(
        div(label("Меня зовут ").render, textInput).render,
        div(label("Мне ").render, numberInput, label(style:="float: none;")("года/лет.").render).render,
        div(label("Я мечтаю стать").render, selectInput, addBtn).render,
        personsDiv
      ).render
      dom.document.getElementById("app").appendChild(form)
		}
	}
}
