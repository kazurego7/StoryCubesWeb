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
      assert(validDiceCount(used, using, unused))
      assert(validDiceNotDuplicated(used, using, unused))
      new DiceFaces(used, using, unused)
    }
  }
}
