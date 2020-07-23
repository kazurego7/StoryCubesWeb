module StoryCube.StoryCube exposing (Id, StoryCube(..), Symbols, equal, roll)

import Json.Decode as D exposing (..)
import List.Nonempty exposing (..)
import Random
import StoryCube.Symbol.Symbol exposing (..)


type alias Symbols =
    Nonempty Symbol


type alias Id =
    Int


{-| ストーリーキューブ
-}
type StoryCube
    = StoryCube Symbols Id


{-| キューブからシンボルを得て、Msgを飛ばすコマンドを得る
-}
roll : StoryCube -> (Symbol -> msg) -> Cmd msg
roll (StoryCube (Nonempty first others) _) msgConstructor =
    Random.uniform first others
        |> Random.generate msgConstructor


equal : StoryCube -> StoryCube -> Bool
equal (StoryCube _ id1) (StoryCube _ id2) =
    id1 == id2
