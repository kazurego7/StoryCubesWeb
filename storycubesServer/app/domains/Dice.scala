package domains

import utils._

case class Dice private (
    id: Dice.Id,
    symbols: List[Symbol]
) {
  def getFace(symbolIdInDice: Symbol.Id): Dice.Face = {
    assert(Dice.Face.validSymbolIdInDice(this, symbolIdInDice))

    val symbol =
      symbols.find(symbol => symbol.symbolId == symbolIdInDice).get;
    Dice.Face(id, symbol)
  }
}

object Dice extends Entity {
  val faceCount: Int = 6
  def validFaceCount(symbols: List[Symbol]): Boolean =
    symbols.length == faceCount

  def apply(id: Dice.Id, symbols: List[Symbol]) = {
    assert(validFaceCount(symbols))

    new Dice(id, symbols)
  }

  case class Face private (id: Id, symbol: Symbol)

  object Face {
    def validSymbolIdInDice(dice: Dice, id: Symbol.Id): Boolean =
      dice.symbols.map(symbol => symbol.symbolId).contains(id)
  }
}
