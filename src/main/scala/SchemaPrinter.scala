

object SchemaPrinter extends App {

  printSchema()

  def printSchema() = {
    println(some.generic.namespace.TwitterSchema.SCHEMA$.toString(true))
  }

}
