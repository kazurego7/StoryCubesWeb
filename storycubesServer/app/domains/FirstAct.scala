package domains

import utils._

case class FirstAct private (
    firstActId: FirstAct.Id,
    diceFaces: FirstAct.DiceFaces,
    title: ActTitle,
    sentence: ActSentence
)

object FirstAct extends Entity {

  case class DiceFaces private (
      used: List[Dice.Face],
      using: List[Dice.Face],
      unused: List[Dice.Face]
  ) {
    assert(DiceFaces.validDiceCount(used, using, unused))
    assert(DiceFaces.validDiceNotDuplicated(used, using, unused))
  }

  object DiceFaces extends InvariantActDiceFace {
    val usedDiceCount: Int = 0
    val usingDiceCount: Int = 3
    val unusedDiceCount: Int = 6
  }
}
