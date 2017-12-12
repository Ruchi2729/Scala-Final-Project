object tests {

  def main(args: Array[String]): Unit = {
    val doc = Leaf(1)

    doc.get(Nil) match {

      case Some(1) =>

      case _ => println("error1")

    }

    doc.get(Seq("i")) match {

      case None =>

      case _ => println("error2")

    }

    val one = Leaf(1)

    val doc1 = Clade(Map("one" -> one))

    doc1.get(Nil) match {

      case None =>

      case _ => println("error3")

    }

    val doc2 = Clade(Map("a" -> doc1))

    doc2.get(Nil) match {

      case None =>

      case _ => println("error4")

    }

    doc2.get(Seq("a","one")) match {

      case Some(1) =>

      case _ => println("error5")

    }
  }

}
