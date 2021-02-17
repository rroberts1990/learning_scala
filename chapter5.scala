//Exercise 1
//Write a function literal that takes 2 integers and returns the larger
//Write a higher order function that takes this and a tuple3

val max = (x: Int, y: Int) => if (x > y) x else y

def pickOne(x: (Int, Int, Int), y: (Int, Int) => Int):Int = {
  y(x._1, y(x._2, x._3))
}

pickOne((4, 109, 12), max)

//Exercise 2

def nextInt = util.Random.nextInt

val t2 = (nextInt, nextInt)
val t3 = (nextInt, nextInt, nextInt)

val min = (x: Int, y: Int) => if (x < y) x else y

pickOne(t3, max)
pickOne(t3, min)

//Exercise 3

def multi(x: Int): Int => Int = (y: Int) => x * y

val quadrupler = multi(4)

quadrupler(5)

//Exercise 4

def fzero[A](x: A)(f: A => Unit): A = {f(x); x}

//This function takes an argument of type A and a function which takes an argument of type A and returns nothing,
// and returns a value of type A. It applys the function parameter to x then returns x.

//Exercise 5
//This questions gives the following code to assign the funciton square to a function value but it is broken

def square(m: Double) = m*m
//val sq = square

//This is incorrect. We can do it one of 2 ways. with the placeholder syntax:
val sqPlaceholder = square _
//or
val sqFunctionType: Double => Double = square

//Exercise 6

def conditional[A](x: A, p: A => Boolean, f: A => A): A = {
  if (p(x)) f(x) else x
}

val a = conditional[Int](10, _.equals(11), _ * 10)


//Exercise 7

for (i <- 1 to 100) {
  val a = conditional[Int](i, _ % 3 == 0, x => {println("type"); 0})
  val b = conditional[Int](i, _ % 5 == 0, y => {println("safe"); 0})
  if (a != 0 && b != 0) println(i)
}
