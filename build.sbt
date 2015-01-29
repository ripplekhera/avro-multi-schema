val avroVersion = "1.7.6"

libraryDependencies ++= {
	val avro = "org.apache.avro" % "avro" % avroVersion
	Seq(
		avro
	)
}

Seq( sbtavro.SbtAvro.avroSettings : _*)

(sourceDirectory in sbtavro.SbtAvro.avroConfig) <<= (sourceDirectory in Compile)(_ / "resources")

(version in sbtavro.SbtAvro.avroConfig) := avroVersion

(javaSource in sbtavro.SbtAvro.avroConfig) <<= (sourceDirectory in Compile)(_ / "java")