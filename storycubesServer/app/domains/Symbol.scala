package domains

import utils._

case class Symbol(
    id: Id[_],
    illust: Symbol.Illust
) extends Entity

object Symbol extends EntityFactory[SymbolRepository[_]] {
  case class Illust(value: Array[Byte])
}

trait SymbolRepository[X] extends IdCreator[X]
