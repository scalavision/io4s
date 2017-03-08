package scalavision.io4s

import java.io.FileInputStream
import java.nio.charset.StandardCharsets
import java.nio.file._
import scala.collection.JavaConverters._

object IO {

  val fileSystem: FileSystem = FileSystems.getDefault
  val separator: String = fileSystem.getSeparator

  def readAll(
    pathAndFile: String
  ): List[String] = {
    val path = Paths.get(pathAndFile)
    Files.readAllLines(path, StandardCharsets.UTF_8).asScala.toList
  }

  def readInputStream(
    pathAndFile:String
  ): FileInputStream =
    new FileInputStream(pathAndFile)

  def write(
    pathAndFile: String,
    contents: String
  ): Unit = {
    Files.write(
      Paths.get(
        pathAndFile),
      contents.getBytes(
        StandardCharsets.UTF_8
      ), StandardOpenOption.CREATE
    )
  }

  def deleteIfExists(
    pathAndFile: String
  ): Unit = {
    Files.deleteIfExists(
      Paths.get(
        pathAndFile)
    )
  }

  def append(
    pathAndFile: String,
    contents: String
  ) : Unit = {
    Files.write(
      Paths.get(
        pathAndFile),
      contents.getBytes(
        StandardCharsets.UTF_8
      ), StandardOpenOption.APPEND
    )
  }

  def write(
    filePath: String,
    fileName: String,
    contents: String
  ): Unit = {
    Files.write(
      Paths.get(
        filePath + separator + fileName),
      contents.getBytes(
        StandardCharsets.UTF_8
      ), StandardOpenOption.CREATE
    )
  }

  def filesInDir(path:String): List[String] = {
    val dir = new java.io.File(path)
    if(dir.exists && dir.isDirectory) {
      dir.listFiles().take(1).filter(_.isFile).map {
        f => f.getAbsolutePath
      }.toList
    } else {
      List[String]()
    }
  }

}
