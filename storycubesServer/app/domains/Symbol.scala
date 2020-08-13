package domains

case class SymbolId(value: Long)

case class Symbol(
    symbolId: SymbolId,
    diceId: DiceId,
    illust: SymbolIllust
)

case class SymbolIllust(value: Array[Byte])
