import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

// The main application
object Queries extends App {
  implicit val ec = scala.concurrent.ExecutionContext.global
  def getAndShowResults(q: Query[_, _, Seq]) = {
    val result = Await.result(db.run(q.result), Duration.Inf)
    showResults(result)
  }
  def showResults[T](s: Seq[T]) = println(Console.MAGENTA + s"results (${s.size} hits):\n${s.mkString("\n")}" + Console.RESET)

  import Orders.orders
  import Products.products
  import Users.users

  val db: PostgresProfile.backend.Database = Database.forConfig("postgres")
  try {

    val q1: Query[((Users, Orders), Products), ((User, Order), Product), Seq] = users join orders on (_.id === _.userId) join products on (_._2.productId === _.id)
    val q2 = users join orders on (_.id === _.userId) join (products filter (_.category === "Electronics")) on (_._2.productId === _.id)
    val q3 = q2.groupBy { case ((user, _), _) => user }.map { case (user, group) => (user, group.map { case ((_, order), product) => order.quantity * product.price}.sum, group.size) }
    val q4 = q3.filter { case (_, _, amount) => amount >= 3 }

    getAndShowResults(q1)
    getAndShowResults(q2)
    getAndShowResults(q3)
    val result5 = db.run(q4.result).map(_.filter {
      case (_, Some(total: Int), _) => total >= 1000
      case _ => false
    })

    showResults(Await.result(result5, Duration.Inf))
  } finally db.close
}


