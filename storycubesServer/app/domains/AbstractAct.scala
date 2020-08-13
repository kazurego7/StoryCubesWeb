package domains

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
