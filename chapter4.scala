//Exercise 1

def circleArea(radius: Double): Double = {
  Math.PI * math.pow(radius, 2)
}

val circle = circleArea(5)

println(s"The area of the circle is $circle")

//Exercise 2


def circleArea(radius: String): Double = {
  val r = radius.toInt
  Math.PI * math.pow(r, 2)
}
val circle2 = circleArea("10")

println(s"The area of the circle is $circle2")


//Exercise 3
@annotation.tailrec
def recursionFunction(n: Int = 5, max: Int = 50, increment: Int = 5): Unit = {
  if (n <= max) {
    println(n)
    recursionFunction(n + increment, max, increment)
  }
}

recursionFunction()

//Exercise 4

def timeParser(mil: Long): String = {
  val seconds = mil/1000
  val minutes = seconds/60
  val hours = minutes/60
  val days = hours/24
  s"$mil milliseconds is equal to $days days, $hours hours, $minutes minutes or $seconds seconds"
}

timeParser(888999323849349l)

//Exericse 5

def exponent(x: Int, y: Int): Double = {
  math.pow(x, y)
}
exponent(5, 6)

def customExponent(x: Int, y: Int): Double = {
  var p = 1
  for (i <- 1 to y) {
    p *= x
  }
  p
}

customExponent(5, 6)

//Exercise 6

def pointDiff(x: (Int, Int), y: (Int, Int)): (Int, Int) = {
  (x._1 - y._1, x._2 - y._2)
}

pointDiff((5, 6), (8, 10))

//Exercise 7

def tupleIndex[A, B](x: (A, B)): Any = {
  if (x._1.isInstanceOf[Int]) x._1
  else 0
}

tupleIndex(("ross", "tigger"))

//Exercise 8

def stringify[A, B, C](t: (A, B, C)) : (A, String, B, String, C, String) = {
  (t._1, t._1.toString, t._2, t._2.toString, t._3, t._3.toString)
}