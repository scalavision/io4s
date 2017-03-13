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
    val text = Files.readAllLines(path, StandardCharsets.UTF_8).asScala.toList
    path.getFileSystem.close()
    text
  }
  
  /**
    * Remember to close the stream after use!
    * @param pathAndFile
    * @return
    */
  def readInputStream(
    pathAndFile:String
  ): FileInputStream =
    new FileInputStream(pathAndFile)

  case class PathAndFile(path: String, file: String)
  
  private def splitPathAndFile(pathAndFile: String): PathAndFile = {
    val paths = pathAndFile.split(separator).toVector
    val path = paths.take(paths.size - 1)
    PathAndFile(path.mkString(separator), paths.last)
  }
  
  def write(
    pathAndFile: String,
    contents: String
  ): Unit = {
    
    mkDirs(splitPathAndFile(pathAndFile).path)
    
    val path: Path = Files.write(
      Paths.get(
        pathAndFile
      ),
      contents.getBytes(
        StandardCharsets.UTF_8
      ),
      StandardOpenOption.CREATE
    )
    
    path.getFileSystem.close()
    
  }

  def deleteIfExists(
    pathAndFile: String
  ): Unit = {
  
    val paths = pathAndFile.split(separator).toVector
    val path = paths.take(paths.size - 2)
  
    val directory = new java.io.File(path.mkString(splitPathAndFile(pathAndFile).path))
  
    if (directory.exists()) {
      Files.deleteIfExists(
        Paths.get(
          pathAndFile)
      )
    }
  }

  def append(
    pathAndFile: String,
    contents: String
  ) : Unit = {
  
    val path: Path = Files.write(
      Paths.get(
        pathAndFile
      ),
      contents.getBytes(
        StandardCharsets.UTF_8
      ),
      StandardOpenOption.APPEND
    )
    
    path.getFileSystem.close()
    
  }
  
  def mkDirs(path: String): Unit = {
    val directory = new java.io.File(path)
    if (!directory.exists()) {
      directory.mkdirs()
    }
    
  }

  def write(
    filePath: String,
    fileName: String,
    contents: String
  ): Unit = {
    val path = Files.write(
      Paths.get(
        filePath + separator + fileName),
      contents.getBytes(
        StandardCharsets.UTF_8
      ),
      StandardOpenOption.CREATE
    )
    
    path.getFileSystem.close()
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
