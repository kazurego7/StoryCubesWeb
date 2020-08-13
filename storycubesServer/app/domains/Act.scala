package domains

import domains.ActSentence.Error.UnderMinSize

case class FirstAct private (
    actId: ActId,
    useSymbolIllusts: List[SymbolIllust],
    unusedSymbolIllusts: List[SymbolIllust],
    actTitle: ActTitle,
    actSentence: ActSentence
)

object FirstAct {
  def apply(
      actId: ActId,
      allSymbols: FirstActSymbols,
      actTitle: ActTitle,
      actSentence: ActSentence
  ): FirstAct = {
    val useSymbolIllusts =
      allSymbols.useSymbols.symbols.map(symbol => symbol.illust)
    val unusedSymbolIllusts =
      allSymbols.unusedSymbols.symbols.map(symbol => symbol.illust)
    new FirstAct(
      actId,
      useSymbolIllusts,
      unusedSymbolIllusts,
      actTitle,
      actSentence
    )
  }
}

case class ActId(value: Long)

case class FirstActSymbols private (
    useSymbols: FirstActUsesSymbols,
    unusedSymbols: FirstActUnusedSymbols
)
case class FirstActUsesSymbols(symbols: List[Symbol])
case class FirstActUnusedSymbols(symbols: List[Symbol])

object FirstActSymbols {
  def apply(
      useSymbols: FirstActUsesSymbols,
      unusedSymbols: FirstActUnusedSymbols
  ): Either[Error, FirstActSymbols] = {
    val useDiceId = useSymbols.symbols.map(symbol => symbol.diceId)
    val unusedDiceId = unusedSymbols.symbols.map(symbol => symbol.diceId)
    val diceId = useDiceId ++ unusedDiceId
    if (diceId.length != diceId.distinct.length) {
      Left(Error.DiceDuplicated)
    } else {
      Right(new FirstActSymbols(useSymbols, unusedSymbols))
    }
  }

  sealed trait Error
  object Error {
    case object DiceDuplicated extends Error
  }
}

object FirstActUsesSymbols extends ActSymbolsGroup[FirstActUsesSymbols] {
  val diceCount: Int = 3
  def apply(symbols: List[Symbol]): Either[Error, FirstActUsesSymbols] =
    validate((_) => new FirstActUsesSymbols(symbols), symbols)
}

object FirstActUnusedSymbols extends ActSymbolsGroup[FirstActUnusedSymbols] {
  val diceCount: Int = 6
  def apply(symbols: List[Symbol]): Either[Error, FirstActUnusedSymbols] =
    validate((_) => new FirstActUnusedSymbols(symbols), symbols)
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
