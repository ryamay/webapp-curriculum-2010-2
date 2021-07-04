import scala.reflect.runtime.{universe => ru}

class Issue(private val title: String) {
  private def printTitle(): Unit = println(title)
}

object ReflectionChallenge extends App {
  val issue = new Issue("不具合1")

  val mirror = ru.runtimeMirror(issue.getClass.getClassLoader)
  val instanceMirror = mirror.reflect(issue)
  val printTitleMirror = instanceMirror.reflectMethod(
    ru.typeOf[Issue].decl(ru.TermName("printTitle")).asMethod
  )

  printTitleMirror()
}
