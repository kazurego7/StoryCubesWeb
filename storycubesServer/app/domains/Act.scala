package domains

case class FirstAct private (
    actId: ActId,
    useSymbolIllusts: List[SymbolIllust],
    unusedSymbolIllusts: List[SymbolIllust],
    actTitle: ActTitle,
    actSentence: ActSentence
)

object FirstAct {
  def create(
      actId: ActId,
      allSymbols: FirstActSymbols,
      actTitle: ActTitle,
      actSentence: ActSentence
  ): Either[Error, FirstAct] = {
    val useSymbolIllusts = allSymbols.useSymbols.symbols.map({
      case (_, symbol) => symbol.illust
    })
    val unusedSymbolIllusts = allSymbols.unusedSymbols.symbols.map({
      case (_, symbol) => symbol.illust
    })
    Right(
      FirstAct(
        actId,
        useSymbolIllusts,
        unusedSymbolIllusts,
        actTitle,
        actSentence
      )
    )
  }
}

case class ActId(value: Long)

case class FirstActSymbols private (
    useSymbols: UsesSymbols,
    unusedSymbols: UnusedSymbols
)
case class UsesSymbols(symbols: List[(DiceId, Symbol)])
case class UnusedSymbols(symbols: List[(DiceId, Symbol)])

object FirstActSymbols {
  def create(
      useSymbols: UsesSymbols,
      unusedSymbols: UnusedSymbols
  ): Either[Error, FirstActSymbols] = {
    val useDiceId = useSymbols.symbols.map({ case (id, _) => id })
    val unusedDiceId = unusedSymbols.symbols.map({ case (id, _) => id })
    val diceId = useDiceId ++ unusedDiceId
    if (diceId.length != diceId.distinct.length) {
      Left(Error.DiceDuplicated)
    }
    Right(FirstActSymbols(useSymbols, unusedSymbols))
  }

  sealed trait Error
  object Error {
    case object DiceDuplicated extends Error
  }
}

object UsesSymbols extends ActSymbolGroup[UsesSymbols] {
  val diceCount: Int = 3
  def create(symbols: List[(DiceId, Symbol)]): Either[Error, UsesSymbols] =
    valid((_) => UsesSymbols(symbols), symbols)
}

object UnusedSymbols extends ActSymbolGroup[UnusedSymbols] {
  val diceCount: Int = 6
  def create(symbols: List[(DiceId, Symbol)]): Either[Error, UnusedSymbols] =
    valid((_) => UnusedSymbols(symbols), symbols)
}

case class ActTitle(value: String)

object ActTitle {
  def create(
      value: String
  ): Either[Error, ActTitle] = {
    if (value.length > 20) {
      Left(Error.OverMaxSize)
    } else if (value.length < 1) {
      Left(Error.UnderMinSize)
    } else {
      Right(ActTitle(value))
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
  def create(
      value: String
  ): Either[Error, ActTitle] = {
    if (value.length > 400) {
      Left(Error.OverMaxSize)
    } else if (value.length < 1) {
      Left(Error.UnderMinSize)
    } else {
      Right(ActTitle(value))
    }
  }

  sealed trait Error
  object Error {
    case object OverMaxSize extends Error
    case object UnderMinSize extends Error
  }
}
