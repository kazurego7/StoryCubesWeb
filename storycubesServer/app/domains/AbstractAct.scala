package domains

trait InvariantActDiceFace {

  val usedDiceCount: Int
  val usingDiceCount: Int
  val unusedDiceCount: Int
  def validDiceCount(faces: List[Dice.Face]) =
    faces.length == usedDiceCount && faces.length == usingDiceCount && faces.length == unusedDiceCount

  def validDiceNotDuplicated(
      used: List[Dice.Face],
      using: List[Dice.Face],
      unused: List[Dice.Face]
  ): Boolean = {
    val diceIds = (using ++ used ++ unused).map(face => face.id)
    diceIds.length == diceIds.distinct.length
  }
}

case class ActTitle(title: String)

object ActTitle {
  val minSize: Int = 1
  val maxSize: Int = 20

  def validWordCount(title: String): Boolean =
    minSize <= title.length && title.length <= maxSize

  def apply(
      title: String
  ): ActTitle = {
    assert(validWordCount(title))
    new ActTitle(title)
  }
}

case class ActSentence(sentence: String)

object ActSentence {
  val minSize: Int = 1
  val maxSize: Int = 20

  def validWordCount(sentence: String): Boolean =
    minSize <= sentence.length && sentence.length <= maxSize

  def apply(
      title: String
  ): ActTitle = {
    assert(validWordCount(title))
    new ActTitle(title)
  }
}
