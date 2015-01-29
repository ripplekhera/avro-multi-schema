import java.io.File

import org.apache.avro.file.DataFileWriter
import org.apache.avro.specific.SpecificDatumWriter
import some.genericMap.namespace.TwitterSchema

import scala.collection.immutable.StringOps
import scala.collection.JavaConversions._

object GenerateAvroFile extends App {

  genFile()

  def genFile() = {

    val datumWriter = new SpecificDatumWriter[TwitterSchema](classOf[TwitterSchema])
    val dataFileWriter = new DataFileWriter[TwitterSchema](datumWriter)
    dataFileWriter.create(TwitterSchema.getClassSchema, new File("/tmp/map_test.avro"))

    for (i <- 1 to 20) {
      val twitterSchemaBuilder = TwitterSchema.newBuilder()
      twitterSchemaBuilder.setUsername(s"user$i")
      twitterSchemaBuilder.setTweet(s"Hello here is my tweet $i")
      val map: java.util.Map[CharSequence, CharSequence] = Map(
        "1".asInstanceOf[CharSequence] -> s"Data 1 - $i",
        "2".asInstanceOf[CharSequence] -> s"Data 2 - $i"
      )
      twitterSchemaBuilder.setMapData(map)
      val twitterSchema = twitterSchemaBuilder.build()
      dataFileWriter.append(twitterSchema)
    }

    dataFileWriter.close()
  }

}
