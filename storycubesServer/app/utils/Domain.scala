package utils

abstract class AbstractId[ValueType](value: ValueType)

trait EntityFactory[ValueType] {
  case class Id(value: ValueType) extends AbstractId(value: ValueType)
  object Id extends IdCreatable[Id]
}

trait Entity[Id <: AbstractId[_]] {
  val id: Id
}

trait IdCreatable[Id <: AbstractId[_]] {
  def create()(implicit
      idCreator: IdCreator[Id]
  ): Id = {
    idCreator.create()
  }
}

trait IdCreator[Id <: AbstractId[_]] {
  def create(): Id
}
