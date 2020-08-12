package domains

case class SymbolId(value: Long)

case class Symbol(
    id: SymbolId,
    illust: SymbolIllust
)

case class SymbolIllust(value: Array[Byte])
