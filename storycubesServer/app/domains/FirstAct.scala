package domains

import utils._

case class FirstAct private (
    actId: FirstAct.Id,
    actDiceFaces: FirstAct.DiceFaces,
    actTitle: ActTitle,
    actSentence: ActSentence
)

object FirstAct extends Entity {

  case class DiceFaces private (
      used: List[Dice.Face],
      using: List[Dice.Face],
      unused: List[Dice.Face]
  )

  object DiceFaces extends InvariantActDiceFace {
    val usedDiceCount: Int = 0
    val usingDiceCount: Int = 3
    val unusedDiceCount: Int = 6
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
