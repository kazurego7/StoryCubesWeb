package domains

import utils._

case class Dice private (
    id: Dice.Id,
    symbols: List[Symbol]
) extends Entity[Dice.Id] {
  assert(Dice.validFaceCount(symbols))
  implicit val self = this

  def getFace(symbolIdInDice: Dice.Id): Dice.Face = {
    val symbol =
      symbols.find(symbol => symbol.id == symbolIdInDice).get;
    Dice.Face(id, symbol)
  }
}

object Dice extends EntityFactory[Long] {
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

trait DiceRepository extends IdCreator[Dice.Id]
