package domains

import utils._

case class Symbol(
    id: Symbol.Id,
    illust: Symbol.Illust
) extends Entity[Symbol.Id]

object Symbol extends EntityFactory[Long] {
  case class Illust(value: Array[Byte])
}

trait SymbolRepository extends IdCreator[Symbol.Id]
