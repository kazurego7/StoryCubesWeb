module StoryCube.Symbol.Picture exposing (Picture, view)

import Html exposing (..)
import Html.Attributes exposing (..)


type Picture
    = Src String


view : Picture -> Html msg
view (Src url) =
    img [ src url, width 100, height 100 ] []
