[![](https://jitpack.io/v/scalavision/io4s.svg)](https://jitpack.io/#scalavision/io4s)

# scalavision.io4s
Small scala library for easy IO. Perfect for small and simple scripts. PRs are welcome.

Add it in your build.sbt at the end of resolvers:

``` 
    resolvers += "jitpack" at "https://jitpack.io"
```        
    
Step 2. Add the dependency

```	
	libraryDependencies += "com.github.scalavision" % "io4s" % "0.1"	
```
Paths in the examples below are relative, but could also be absolute of course.

```scala
// Read all of the file into a list of Strings
val lines: List[String] = IO.readAll("path/to/my/file.txt")

// Open a file stream
val fileStream: FileStream = IO.readInputStream("path/to/my/file.txt")

// Delete a file if it exists, if not nothing happens ..
IO.deleteIfExists("path/to/my/file.txt")

// Appends UTF-8 text to a file, if it does not exist, it will be created!
IO.append("path/to/my/file.txt", "some text to append")

// List all files in the directory
val filesInDir: List[String] = IO.filesInDir("path/to/my/dir")
```
