package domains

import utils.Entity

case class LastAct private (
    firstActId: FirstAct.Id,
    middleActId: MiddleAct.Id,
    lastActId: LastAct.Id,
    diceFaces: FirstAct.DiceFaces,
    sentence: ActSentence
)

object LastAct extends Entity {

  case class DiceFaces private (
      used: List[Dice.Face],
      using: List[Dice.Face],
      unused: List[Dice.Face]
  )

  object DiceFaces extends InvariantActDiceFace {
    val usedDiceCount: Int = 6
    val usingDiceCount: Int = 3
    val unusedDiceCount: Int = 0
    def apply(
        used: List[Dice.Face],
        using: List[Dice.Face],
        unused: List[Dice.Face]
    ): DiceFaces = {
      assert(validDiceCount(used))
      assert(validDiceNotDuplicated(using, used, unused))
      new DiceFaces(used, using, unused)
    }
  }
}
