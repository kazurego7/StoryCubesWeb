module StoryCube.Symbol.Symbol exposing (Symbol, getName)

import StoryCube.Symbol.Explanation exposing (..)
import StoryCube.Symbol.Picture exposing (..)


type alias Name =
    String


{-| ダイスの側面の記号
-}
type Symbol
    = Symbol Name Picture ExplanationList


getName : Symbol -> Name
getName (Symbol name _ _) =
    name
