package domains

import utils._

case class Dice private (
    id: Id[_],
    symbols: List[Symbol]
) extends Entity {
  assert(Dice.validFaceCount(symbols))
  implicit val self = this

  def getFace(symbolIdInDice: Id[_]): Dice.Face = {
    val symbol =
      symbols.find(symbol => symbol.id == symbolIdInDice).get;
    Dice.Face(id, symbol)
  }
}

object Dice extends EntityFactory[DiceRepository[_]] {
  val faceCount: Int = 6
  def validFaceCount(symbols: List[Symbol]): Boolean =
    symbols.length == faceCount

  case class Face private (
      diceId: Id[_],
      symbol: Symbol
  )(implicit dice: Dice) {
    assert(Dice.Face.validSymbolIdInDice(symbol.id))
  }
  object Face {
    def validSymbolIdInDice(
        symbolId: Id[_]
    )(implicit dice: Dice): Boolean =
      dice.symbols.map(symbol => symbol.id).contains(symbolId)
  }
}

trait DiceRepository[X] extends IdCreator[X]
