import scala.::
import scala.collection.convert.ImplicitConversions.`seq AsJavaList`

//Exercise 1
//Create a list of the first 20 odd Long numbers

for (i <- 0L to 20L; if i % 2 != 0) yield i

val oddNumbers = (0 to 20).filter(_ % 2 != 0).toList

(0L to 9L).map(_ * 2 + 1)

//Exercise 2
//Write a function called factors that takes a number and returns a list of its factors other than 1 and the number.

def factors(x: Int): List[Int] = {
  (2 until x).filter(x % _ == 0).toList
}

def factorsFromList(x: List[Int]): List[Int] = {
  x.flatMap(factors)
}

factorsFromList(oddNumbers)

//Exercise 3
//Write a function first[A](items: List[A], count: Int): List[A] that returns thhe first x number of items in a given list.

def firstEasy[A](items: List[A], count: Int): List[A] = {
  items.take(count)
}

def first[A](items: List[A], count: Int): List[A] = {
  val l = for (i <- 0 until count) yield items(i)
  l.toList
}

first(oddNumbers, 4)

def firstFold[A](items: List[A], count: Int): List[A] = {
  items.foldLeft[List[A]](Nil) { (a: List[A], i: A) => if (a.size >= count) a else i :: a
  }.reverse
}

def firstRecursive[A](items: List[A], count: Int): List[A] = {
  if (count > 0 && items.tail != Nil) items.head :: firstRecursive(items.tail, count-1)
  else Nil
  }

//Exercise 4
//Write a function that takes a list of strings and returns the longest string in the list

def longestStringFold(x: List[String]): String = {
  x.fold("") {(i: String, j: String) => if (j.size > i.size) j else i}
}

def longestStringReduce(x: List[String]): String = {
  x.reduce({(i: String, j: String) => if (j.size > i.size) j else i})
}

def reduceString(x: List[String])(f: (String, String) => String): String = {
  x.reduce((i, j) => f(i, j))
}

val Names = List("Ross", "Andy", "Ian", "Kersten", "Stuart")

reduceString(Names)((x, y) => if (x.size < y.size) x else y)


//Exercise 5
//Write a function that reverses a list.

def listReverser[A](src: List[A], dest: List[A] = Nil): List[A] = {
  if (src == Nil) dest else listReverser(src.tail, src.head :: dest)
}

listReverser(Names)

//Exercise 6
//Write a function that takes a LIst[String] and returns a (List[String], List[String])
def palindromePartition(x: List[String]): (List[String], List[String]) = {
  x.partition(s => s.reverse == s)
}

def palindrome(input: List[String]): (List[String], List[String]) = {
  input.foldLeft((List[String](), List[String]())) { (a, i) =>
    if (i == i.reverse) (i :: a._1, a._2) else (a._1, i :: a._2)
  }
}

val palindromes = List("hello", "racecar", "otto", "bum", "ross")

palindrome(palindromes)

//Exercise 7


//Read content from a URL
val weatherData: String = "http://api.openweathermap.org/data/2.5/forecast?mode=xml&lat=55&lon=0&appid=befeff08f86e51ef24ad38ee9dc2a8d6"

val l: List[String] = io.Source.fromURL(weatherData).getLines().toList
//(a)
l.take(10)

//(b)

def getValue(key: String, input: List[String]): String = {
  input
    .filter(_.contains(s"<$key>"))
    .mkString("")
    .replaceAll(s".*>(\\w+)</$key.*","$1")
}

val cityName = getValue("name", l)
val countryName = getValue("country", l)

//(c)
val segments = l(1).split("</time>").toList
segments.size

//(d)
def getAttribute(key: String, att: String, input: List[String]) = {
  input
    .filter(_.contains(s"<$key"))
    .filter(_.contains(s"$att="))
    .map(s => s.replaceAll(s""".*$att="([^"]+)".*""", "$1"))
}

val name = getAttribute("symbol", "name", segments)

val timestampedForecast = getAttribute("time", "from", segments) zip getAttribute("symbol", "name", segments)

println("12 Hour Forecast")
timestampedForecast
  .foreach({
    case (time, desc) =>
      val when = time.replaceAll("""T(\d+).*""",""" at $100""")
      println(s"$when | $desc")
  })

//(e)
val descriptions = getAttribute("symbol", "name", segments).distinct.sorted