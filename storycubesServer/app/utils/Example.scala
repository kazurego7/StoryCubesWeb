package utils

// 例
object Example {

  // Domain では、Idに何が入るかわかっていない
  trait ARepository extends IdCreator[A.Id]

  // Entity となるクラスと、そのファクトリーの作成
  case class A(id: A.Id, value: Int) extends Entity[A.Id]
  object A extends EntityFactory[Long]

  // Id内部の型と生成の実装を IdCreatorにもたせる
  case class ARepositoryConcrete() extends ARepository {
    def createId(): A.Id = A.Id(1)
  }

  trait BRepository extends IdCreator[B.Id]

  case class B(id: B.Id, value: Int) extends Entity[B.Id]
  object B extends EntityFactory[Long]
  case class BRepositoryConcrete() extends BRepository {
    def createId(): B.Id = B.Id(1)
  }

  object Usecase {
    def usecase1(
        value: Int
    )(implicit aIdCreator: ARepository, bIdCreator: BRepository) = {
      var id = A.askCreateId()
      var a = A(id, value)

      var id2 = B.askCreateId()
      var b = B(id2, value)
    }
  }
}
