package com.minsub.test

import scala.io.Source

/**
  * Created by gray.ji on 2018. 1. 22..
  */
object TMSFileError extends App {

//  val lines = Source.fromFile("/Users/kakao/tms_error.csv", "utf-8").getLines
  val lines = Source.fromFile("/Users/kakao/list.csv", "utf-8").getLines
  
  var i = 0
  lines.foreach(line => {
    try {
      i += 1
      val a = line.toInt
//      println(s"${line.toInt},")  
    } catch {
//      case _ => println(s"${line},error")
      case _ => println(s"$i : ${line}")
    }
  })

}
