package foo

import play.api.libs.json.Json

object Demo {

  import julienrf.json.derived

  sealed trait Foo
  case class Bar(s: String, i: Int) extends Foo
  case class Baz(msg: String) extends Foo

  object Bar {
    implicit val barFormat = Json.format[Bar]
  }

  object Baz {
    implicit val bazFormat = derived.oformat[Baz]()
  }

  implicit val format = derived.oformat[Foo]()
}

object DemoMain {

  import Demo._

  def main(args: Array[String]): Unit = {
    val x = Json.toJson(Bar("hello", 55))
    println(x)
  }
}