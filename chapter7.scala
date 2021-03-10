import collection.mutable.Buffer
//Exercise 1
//The Fibonacci series starts with "1, 1" and then computes each successive element as the sum of the 2 previous.

//a)
def fibonacci(x: Int): List[Int] = {
  val fib = Buffer(1, 1)
  while (fib.size < x) {
    fib += fib.takeRight(2).sum
  }
  fib.toList
}

fibonacci(10)


//b)
def fibonacciExtender(l: List[Int], x: Int): List[Int] = {
  val b = l.toBuffer
  val finalSize = b.size + x
  while (b.size < finalSize) {
    b += b.takeRight(2).sum
  }
  b.toList
}

fibonacciExtender(List(1, 1, 2, 3, 5, 8, 13, 21, 34, 55), 10)


//c)
def fibStream(a: Long, b: Long): LazyList[Long] = LazyList.cons(a, fibStream(b, a + b))

val x = fibStream(1, 1).take(20).toList
x

//d)
def nextFib(x: Long): Option[Long] = {
  val fib = fibStream(1, 1)
  val prevFib = fib.takeWhile(_ <= x).toList
  if (prevFib.last == x) Some(prevFib.takeRight(2).sum)
  else None
}

nextFib(9)

//2)

def listDir(dir: String): List[String] = {
  val filesRaw = new java.io.File(dir).listFiles
  val files = filesRaw.map(_.getName).filterNot(_.startsWith("."))
  files.toList
}

val files = listDir("/Users/RossRoberts/")

//3)
val filesOrder = files.groupBy(_.head.toLower).toList.sortBy(_._1)


//4)

def toDouble(x: String): Option[Double] = util.Try(x.toDouble).toOption

def prod(x: String, y: String): Option[Double] = {
  (toDouble(x), toDouble(y)) match {
    case (Some(a), Some(b)) => Some(a*b)
    case _ => None
  }
}

prod("10", "9.1")

//5)
def safeGetProperty(s: String): Option[String] = {
  util.Try( System.getProperty(s)) match {
    case util.Success(x) => Option(x)
    case util.Failure(ex) => None
  }
}

safeGetProperty(null)
safeGetProperty("os.arch")
safeGetProperty("blah")

//6)
def xmlParse(xml: String) = xml.split("</?entry>").filterNot(_.isEmpty).toList

def getChild(xml: String, name: String): Option[String] = {
  val p = s".*<$name>(.*)</$name>.*".r
  xml match {
    case p(result) => Option(result)
    case _ => None
  }
}

def getGithubRss(user: String, repo: String, branch: String): String = {
  val url = s"https://github.com/$user/$repo/commits/$branch.atom"
  val s = io.Source.fromURL(url)
  val text = s.getLines.map(_.im).mkString("")
  text
}

val xml = getGithubRss("scala", "scala", "2.11.x")

val firstTitle = getChild(xml, "title")