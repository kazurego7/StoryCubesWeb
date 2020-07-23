module StoryCube.Symbol.Explanation exposing (ExplanationList, explanationList, getExplaination, next, shuffle)

import List.Nonempty exposing (..)
import Random
import Random.List


{-| シンボルの解釈例
-}
type alias Explanation =
    String


dammyExplanation : Explanation
dammyExplanation =
    "hogehoge"


{-| シンボルの解釈例のあつまり
-}
type ExplanationList
    = ExplanationList (Nonempty Explanation)


{-| explanationList のコンストラクタ
-}
explanationList : Explanation -> List Explanation -> ExplanationList
explanationList one others =
    ExplanationList (Nonempty one others)


{-| 解釈例の順番をシャッフルする
-}
shuffle : ExplanationList -> (ExplanationList -> msg) -> Cmd msg
shuffle (ExplanationList nonempty) msgConstructor =
    let
        listToExplanationList : List Explanation -> ExplanationList
        listToExplanationList list =
            case List.head list of
                Nothing ->
                    ExplanationList (Nonempty dammyExplanation [])

                Just first ->
                    ExplanationList (Nonempty first (List.drop 1 list))
    in
    toList nonempty
        |> Random.List.shuffle
        |> Random.generate (listToExplanationList >> msgConstructor)


{-| 解釈例を次のものにする
-}
next : ExplanationList -> ExplanationList
next ((ExplanationList (Nonempty oneExp otherExps)) as orgList) =
    case otherExps of
        [] ->
            orgList

        firstExp :: restExp ->
            ExplanationList (Nonempty firstExp (List.append restExp [ oneExp ]))


{-| 解釈例から１つ得る
-}
getExplaination : ExplanationList -> Explanation
getExplaination (ExplanationList (Nonempty exp _)) =
    exp
