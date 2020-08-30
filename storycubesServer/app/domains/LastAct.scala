package domains

import utils._

case class LastAct private (
    id: Id[_],
    firstActId: Id[_],
    middleActId: Id[_],
    diceFaces: FirstAct.DiceFaces,
    sentence: ActSentence
) extends Entity

object LastAct extends EntityFactory[LastActRepository[_]] {

  case class DiceFaces private (
      used: List[Dice.Face],
      using: List[Dice.Face],
      unused: List[Dice.Face]
  ) {
    assert(DiceFaces.validDiceCount(used, using, unused))
    assert(DiceFaces.validDiceNotDuplicated(used, using, unused))
  }

  object DiceFaces extends InvariantActDiceFace {
    val usedDiceCount: Int = 6
    val usingDiceCount: Int = 3
    val unusedDiceCount: Int = 0
  }
}

trait LastActRepository[X] extends IdCreator[X]
