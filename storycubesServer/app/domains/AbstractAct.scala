package domains

trait ActSymbolGroup[T] {
  val diceCount: Int
  def valid(
      constructor: Unit => T,
      symbols: List[(DiceId, Symbol)]
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
