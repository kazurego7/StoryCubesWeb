package domains

import utils._

case class Symbol(
    symbolId: Symbol.Id,
    illust: Symbol.Illust
)

object Symbol extends Entity {
  case class Illust(value: Array[Byte])
}
