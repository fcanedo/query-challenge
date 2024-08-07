import slick.jdbc.PostgresProfile.api._

import java.sql.Date

case class User(id: Int, name: String, email: String)

class Users(tag: Tag) extends Table[User](tag, "users") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def email = column[String]("email")

  def * = (id, name, email).mapTo[User]
}

object Users {
  val users = TableQuery[Users]
}

case class Product(id: Int, name: String, price: Int, category: String)

class Products(tag: Tag) extends Table[Product](tag, "products") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def price = column[Int]("price")
  def category = column[String]("category")

  def * = (id, name, price, category).mapTo[Product]
}

object Products {
  val products = TableQuery[Products]
}

case class Order(id: Int, userId: Int, productId: Int, quantity: Int, createdAt: Date)

class Orders(tag: Tag) extends Table[Order](tag, "orders") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def userId = column[Int]("user_id")
  def productId = column[Int]("product_id")
  def quantity = column[Int]("quantity")
  def createdAt = column[Date]("created_at")

  def * = (id, userId, productId, quantity, createdAt).mapTo[Order]
}

object Orders {
  val orders = TableQuery[Orders]
}