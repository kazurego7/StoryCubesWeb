package domains

import utils._

case class MiddleAct private (
    id: Id[_],
    firstActId: Id[_],
    diceFaces: FirstAct.DiceFaces,
    sentence: ActSentence
) extends Entity

object MiddleAct extends EntityFactory[MiddleActRepository[_]] {

  case class DiceFaces private (
      used: List[Dice.Face],
      using: List[Dice.Face],
      unused: List[Dice.Face]
  ) {
    assert(DiceFaces.validDiceCount(used, using, unused))
    assert(DiceFaces.validDiceNotDuplicated(used, using, unused))
  }

  object DiceFaces extends InvariantActDiceFace {
    val usedDiceCount: Int = 3
    val usingDiceCount: Int = 3
    val unusedDiceCount: Int = 3
  }
}

trait MiddleActRepository[X] extends IdCreator[X]
