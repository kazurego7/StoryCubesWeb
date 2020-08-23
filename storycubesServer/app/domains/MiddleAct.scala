package domains

import utils.Entity

case class MiddleAct private (
    firstActId: FirstAct.Id,
    middleActId: MiddleAct.Id,
    diceFaces: FirstAct.DiceFaces,
    sentence: ActSentence
)

object MiddleAct extends Entity {

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
