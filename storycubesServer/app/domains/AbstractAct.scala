package domains

trait InvariantActDiceFace {

  val usedDiceCount: Int
  val usingDiceCount: Int
  val unusedDiceCount: Int
  def validDiceCount(
      used: List[Dice.Face],
      using: List[Dice.Face],
      unused: List[Dice.Face]
  ) =
    used.length == usedDiceCount && using.length == usingDiceCount && unused.length == unusedDiceCount

  def validDiceNotDuplicated(
      used: List[Dice.Face],
      using: List[Dice.Face],
      unused: List[Dice.Face]
  ): Boolean = {
    val diceIds = (using ++ used ++ unused).map(face => face.diceId)
    diceIds.length == diceIds.distinct.length
  }
}

case class ActTitle(title: String) {
  assert(ActTitle.validWordCount(title))
}

object ActTitle {
  val minSize: Int = 1
  val maxSize: Int = 20

  def validWordCount(title: String): Boolean =
    minSize <= title.length && title.length <= maxSize
}

case class ActSentence(sentence: String) {
  assert(ActSentence.validWordCount(sentence))
}

object ActSentence {
  val minSize: Int = 1
  val maxSize: Int = 20

  def validWordCount(sentence: String): Boolean =
    minSize <= sentence.length && sentence.length <= maxSize
}
