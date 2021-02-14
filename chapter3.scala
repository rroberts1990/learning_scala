//Exercise 1
val name = ""
val x = name match {
  case "" => "n/a"
  case _ => name
}

println(s"The result of exercise 1 is $x")

//Exercise 2
val y: Double = -1


val resIf = {
  if (y > 0) "greater"
  else if (y == 0) "same"
  else if (y < 0) "less"
}


//Exercise 3

val colour = "blue"

val hexColour = colour match {
  case "magenta" => "00ff00"
  case "yellow" => "ffff00"
  case "cyan" => "00ffff"
  case x => {
    println(s"$x did not match a known colour")
    "fuck"
  }
}

//Exercise 4

for (i <- 1 to 100 by 5) {
  for (j <- i to (i+4)) {println(s"$j, ")}
  println("")
}

//Exercise 5

for (i <- 1 to 100) {
  i match {
    case x if x % 15 == 0 => println("typesafe")
    case x if x % 3 == 0 => println("type")
    case x if x % 5 == 0 => println("safe")
    case x => println(x)
  }
}

//Exercise 6

for (i <- 1 to 100) if (i % 15 == 0) println("typesafe") else if (i % 3 == 0) println("type") else if (i % 5 == 0) println("safe") else println(i)