import java.util.Date

//1)
//a. Create a console class that can track the male, model, debut date, wifi type, physical media formats supported and max video res.
//override default toString.

abstract class MediaFormat
class Dvd extends MediaFormat
class BD extends MediaFormat
class Digital extends MediaFormat
class Usb extends MediaFormat

abstract class Resolution(pixels: Int)
class HD extends VideoResolution(1280 * 720)
class FHD extends VideoResolution(1920 * 1080)
class QHD extends VideoResolution(2560 * 1440)
class UHD4K extends VideoResolution(3840 * 2160)

class GamingConsole(val make: String, val model: String, val debutDate: Date, val Wifi: Option[String],
                    val media: List[MediaFormat], val resolution: Resolution) {
  override def toString = {s"$mk: $mod \n Released in: $dd \n WiFi type: $w\n Supports the following: $med"}
}

class ConsoleGen {
  def strToDate(d: String) = Date.valueOf(d)

  val ps5 = new GamingConsole("Playstation", "5", strToDate("2021-01-01"), Some("a/b"),
    List(new Dvd, new Usb), new HD)

  val aquaTopia = new GameConsole("Dreamcast", "Whatever", strToDate("2012-01-01"), Some("a/b/g"),
    List(new DvdMediaFormat), new HD)

  val oonaSeven = new GameConsole("Xbox", "360", strToDate("2014-01-01"), Some("b/g/n"),
    List(new BluRayMediaFormat, new DvdMediaFormat), new FHD)

  val leoChallenge = new GameConsole("Sega", "Megadrive", strToDate("2015-01-01"), Some("g/n"),
    List(new USBMediaFormat), new UHD4K)
}

class Game(val name: String, val producer: String, val consoles: List[GamingConsole]) {
  def isSupported(x: GamingConsole) = consoles.contains(x)
  override def toString = s"Game($name, by $maker)"
}


//2)
//Create a Linked List

class LinkedList[T](items: T*) {
 val item: Option[T] = items.headOption

  val next: Option[LinkedList[T]] = {
    if (item.isDefined) Some(new LinkedList[T](items.tail: _*)) else None
  }
  def foreach(f: T => Unit): Unit = {
    for (i <- items) {
      f(i)
    }
  }
  def apply(index: Int): Option[T] = {
    if (index == 0) item
    else next.flatMap(_.apply(index - 1))
  }
}

//b)

abstract class LinkyListy[T]{
  def foreach(f: T => Unit): Unit
  def apply(i: Int) : Option[T]
  def headoption: Option[T] = apply(0)

  lazy val head: T = headoption.get

  def tail: LinkyListy[T]

  def filter(f: T => Boolean): LinkyListy[T] = {
    var result: LinkyListy[T] = new emptyNode[T]
    foreach {i =>
    if (f(i)) result = new Node[T](i, result)
    }
    result
  }

  def size: Int = {
    var count = 0
    foreach { _ => count += 1
    }
    count
  }

  def map[B](f: T => B): LinkyListy[B] = {
    var result = new emptyNode[B]
    foreach { i =>
      result = new Node(f(i), result)
    }
    result.reverse
  }
}

class emptyNode[T] extends LinkyListy[T] {
  override def foreach(f: T => Unit): Unit = {}
  override def apply(i: Int): Option[T] = None
  override def tail: LinkyListy[T] = null
}

class Node[T](val item: T, val tail: LinkyListy[T]) extends LinkyListy[T] {

  override def foreach(f: T => Unit): Unit = {
    f(item)
    tail.foreach(f)
  }
  override def apply(index: Int): Option[T] = {
    if (index < 1) Some(item) else tail.apply(index - 1)
  }

}

class ListCreator {
  def create[T](items: T*): LinkyListy[T] = {
    var result: Node[T] = new emptyNode[T]
    for (i <- items.reverse) {
      result = new Node[T](i, result)
    }
    result
  }
}

//3)


