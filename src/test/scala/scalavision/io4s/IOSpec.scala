package scalavision.io4s

import org.specs2.mutable.Specification

class IOSpec extends Specification {
  
  "IO" should {
    
    "handle basic file io" in {
      
      val myPath = "test/output/HelloWorld.txt"
      
      val text = "Hello World"
      
      IO.write(myPath, text)
      
      val content = IO.readAll(myPath)
      
      content === List(text)
      
      IO.deleteIfExists(myPath)
      
      IO.readAll(myPath) should throwA[java.nio.file.NoSuchFileException]
      
    }
  }
  
}
