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
  )

  object DiceFaces extends InvariantActDiceFace {
    val usedDiceCount: Int = 3
    val usingDiceCount: Int = 3
    val unusedDiceCount: Int = 3
    def apply(
        used: List[Dice.Face],
        using: List[Dice.Face],
        unused: List[Dice.Face]
    ): DiceFaces = {
      assert(validDiceCount(used, using, unused))
      assert(validDiceNotDuplicated(used, using, unused))
      new DiceFaces(used, using, unused)
    }
  }
}
