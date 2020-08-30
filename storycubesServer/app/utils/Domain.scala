package utils

abstract class AbstractId[ValueType](value: ValueType)

trait IdCreator[Id <: AbstractId[_]] {
  def createId(): Id
}

trait EntityFactory[ValueType] {
  case class Id(value: ValueType) extends AbstractId(value: ValueType)
  def askCreateId()(implicit
      idCreator: IdCreator[Id]
  ): Id = {
    idCreator.createId()
  }
}

trait Entity[Id <: AbstractId[_]] {
  val id: Id
}
