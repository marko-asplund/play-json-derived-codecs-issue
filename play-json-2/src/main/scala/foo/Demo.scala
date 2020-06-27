package foo

import play.api.libs.json.Json
import julienrf.json.derived

object Demo1 {
  sealed trait Foo1
  case class Bar1(s: String, i: Int) extends Foo1
  case class Baz1(msg: String) extends Foo1

  object Bar1 {
    implicit val barFormat = Json.format[Bar1]
  }

  object Baz1 {
    implicit val bazFormat = derived.oformat[Baz1]()
  }

  implicit val format1 = derived.oformat[Foo1]()
}

object Demo2 {
  sealed trait Foo2
  case class Bar2(s: String, i: Int) extends Foo2
  case class Baz2(msg: String) extends Foo2

  implicit val format2 = derived.oformat[Foo2]()
}

object DemoMain1 {
  import Demo1._

  def main(args: Array[String]): Unit = {
    val b1 = Json.toJson[Foo1](Bar1("hello", 55))
    println(b1)
  }
}

object DemoMain2 {
  import Demo2._

  def main(args: Array[String]): Unit = {
    val b2 = Json.toJson[Foo2](Bar2("hello", 55))
    println(b2)
  }
}
