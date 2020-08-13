package domains

case class ActId(value: Long)

trait ActSymbolsGroup[T] {
  val diceCount: Int
  def validate(
      constructor: Unit => T,
      symbols: List[Symbol]
  ): Either[Error, T] = {
    if (symbols.length != diceCount) {
      Left(Error.DiceCountOver)
    } else {
      Right(constructor(()))
    }
  }

  sealed trait Error
  object Error {
    case object DiceCountOver extends Error
  }
}

case class ActTitle(value: String)

object ActTitle {
  def apply(
      value: String
  ): Either[Error, ActTitle] = {
    if (value.length > 20) {
      Left(Error.OverMaxSize)
    } else if (value.length < 1) {
      Left(Error.UnderMinSize)
    } else {
      Right(new ActTitle(value))
    }
  }

  sealed trait Error
  object Error {
    case object OverMaxSize extends Error
    case object UnderMinSize extends Error
  }
}

case class ActSentence(value: String)

object ActSentence {
  def apply(
      value: String
  ): Either[Error, ActTitle] = {
    if (value.length > 400) {
      Left(Error.OverMaxSize)
    } else if (value.length < 1) {
      Left(Error.UnderMinSize)
    } else {
      Right(new ActTitle(value))
    }
  }

  sealed trait Error
  object Error {
    case object OverMaxSize extends Error
    case object UnderMinSize extends Error
  }
}
