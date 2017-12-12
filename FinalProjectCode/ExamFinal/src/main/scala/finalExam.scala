trait Document[-K, +V] extends (Seq[K]=>V) {

  def get(x: Seq[K]): Option[V]

}

case class Leaf[V](value: V) extends Document[Any,V] {

  // This "default" apply has the same argument as the primary constructor of the case class
  def apply(v1 : Seq[Any]) = new
  def get(x: Seq[Any]): Option[V] = x match {

    case Nil => Some(value)

    case _ => None

  }

}

case class Clade[K,V](branches: Map[K,Document[K,V]]) extends Document[K,V] {

  def get(x: Seq[K]): Option[V] = x match {

    case h::t => branches.get(h) flatMap (_.get(t))

    case Nil => None

  }

}


