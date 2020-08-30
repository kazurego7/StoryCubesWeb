package domains

import utils._

case class FirstAct private (
    id: Id[_],
    diceFaces: FirstAct.DiceFaces,
    title: ActTitle,
    sentence: ActSentence
) extends Entity

object FirstAct extends EntityFactory[FirstActRepository[_]] {

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

trait FirstActRepository[X] extends IdCreator[X]
