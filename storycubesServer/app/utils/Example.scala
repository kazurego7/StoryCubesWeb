package utils

// 例
object Example {

  // EntityFactory がIdのvalueの型を決定して、EntityとそのIdのファクトリを管理する
  // Idファクトリは、Idを作る権限を持つ（implicit に定義されたIdCreatorを探す）
  object A extends EntityFactory[Long]
  case class A(id: A.Id, value: Int) extends Entity[A.Id]

  // Repository のインターフェイスに、Idを作る能力をもたせる
  trait ARepository extends IdCreator[A.Id]

  object B extends EntityFactory[Long]
  case class B(id: B.Id, value: Int) extends Entity[B.Id]
  trait BRepository extends IdCreator[B.Id]

  object Usecase {
    def usecase1(
        value: Int
    )(implicit aIdCreator: ARepository, bIdCreator: BRepository) = {
      var id = A.Id.create()
      var a = A(id, value)

      var id2 = B.Id.create()
      var b = B(id2, value)
    }
  }
}
