package chapter8

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
 val item: T = items.head

  val next: Option[LinkedList[T]] = {
    if (item.isDefined) Some(new LinkedList[T](items.tail: _*)) else None
  }

}