package utils

// 例
object Example {

  // Domain では、Idに何が入るかわかっていない
  trait ARepository[X] extends IdCreator[X]

  // Entity となるクラスと、そのファクトリーの作成
  case class A(id: Id[_], value: Int) extends Entity
  object A extends EntityFactory[ARepository[_]]

  // Id内部の型と生成の実装を IdCreatorにもたせる
  case class ARepositoryConcrete() extends ARepository[Long] {
    def createId(): ConcreteId = ConcreteId(1)
  }

  trait BRepository[X] extends IdCreator[X]

  case class B(id: Id[_], value: Int) extends Entity
  object B extends EntityFactory[ARepository[_]]
  case class BRepositoryConcrete() extends BRepository[Long] {
    def createId(): ConcreteId = ConcreteId(1)
  }

  object Usecase {
    def usecase1(
        value: Int
    )(implicit aIdCreator: ARepository[_], bIdCreator: BRepository[_]) = {
      var id = A.askCreateId()
      var a = A(id, value)

      var id2 = B.askCreateId()
      var b = B(id, value)
    }
  }
}
