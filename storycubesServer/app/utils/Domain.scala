package utils

abstract class Id[ValueType](value: ValueType)

trait IdCreator[ValueType] {
  case class ConcreteId private (value: ValueType) extends Id(value: ValueType)
  def createId(): ConcreteId
}

trait EntityFactory[ConcreteIdCreator <: IdCreator[_]] {
  def askCreateId()(implicit
      idCreator: ConcreteIdCreator
  ): idCreator.ConcreteId = {
    idCreator.createId()
  }
}

trait Entity {
  val id: Id[_]
}
