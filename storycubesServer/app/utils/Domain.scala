package utils

trait Entity {
  case class Id(value: Long)
}

trait IdCreateAskable[Id] {
  def createId()(implicit
      idCreator: IdCreator[Id]
  ): Id = idCreator.createId()
}

trait IdCreator[Id] {
  def createId(): Id
}
