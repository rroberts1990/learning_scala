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