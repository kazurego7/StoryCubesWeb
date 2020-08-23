package domains

import utils._

case class Dice private (
    id: Dice.Id,
    symbols: List[Symbol]
) {
  assert(Dice.validFaceCount(symbols))
  implicit val self = this

  def getFace(symbolIdInDice: Symbol.Id): Dice.Face = {
    val symbol =
      symbols.find(symbol => symbol.id == symbolIdInDice).get;
    Dice.Face(id, symbol)
  }
}

object Dice extends Entity {
  val faceCount: Int = 6
  def validFaceCount(symbols: List[Symbol]): Boolean =
    symbols.length == faceCount

  case class Face private (
      diceId: Dice.Id,
      symbol: Symbol
  )(implicit dice: Dice) {
    assert(Dice.Face.validSymbolIdInDice(symbol.id))
  }
  object Face {
    def validSymbolIdInDice(
        symbolId: Symbol.Id
    )(implicit dice: Dice): Boolean =
      dice.symbols.map(symbol => symbol.id).contains(symbolId)
  }
}
