package domains

import utils._

case class LastAct private (
    id: LastAct.Id,
    firstActId: FirstAct.Id,
    middleActId: MiddleAct.Id,
    diceFaces: FirstAct.DiceFaces,
    section: ActSection
) extends Entity[LastAct.Id]

object LastAct extends EntityFactory[Long] {

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

trait LastActRepository extends IdCreator[LastAct.Id]
