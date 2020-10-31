package domains

import utils._

case class MiddleAct private (
    id: MiddleAct.Id,
    firstActId: FirstAct.Id,
    diceFaces: FirstAct.DiceFaces,
    section: ActSection
) extends Entity[MiddleAct.Id]

object MiddleAct extends EntityFactory[Long] {

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

trait MiddleActRepository extends IdCreator[MiddleAct.Id]
