package domains

case class DiceId(value: Long)

case class Dice(
    id: DiceId,
    symbols: List[Symbol]
)
